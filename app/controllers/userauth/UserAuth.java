package controllers.userauth;

import play.*;
import play.cache.Cache;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.*;
import play.filters.csrf.*;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;

import annotating.MyAuth;
import models.*;
import views.html.index;
import views.html.authtemplate.*;

import org.mindrot.jbcrypt.BCrypt;

public class UserAuth extends Controller{
	
	@AddCSRFToken
	@With(MyAuth.class)
	public static Result loginGET()
	{
		Form<User> form = Form.form(User.class);
		User user = (User) ctx().args.get("user");
		if(user == null)
			return ok(loginpage.render((List<Patterns>)Cache.get("patterns"), form, null, null));
		else
			return ok(index.render("Welcome, you have logged in", (List<Patterns>)Cache.get("patterns"), user));	
	}
	
	@RequireCSRFCheck
	public static Result login()
	{
		// do some stuff here
		DynamicForm dform = Form.form().bindFromRequest();
		User user = User.authenticate(dform.get("username"), dform.get("password")); 
		if( user == null)
		{
			Form<User> form = Form.form(User.class);
			return ok(loginpage.render((List<Patterns>)Cache.get("patterns"), form, null, "Wrong username or password"));
		}
		session().clear();
		session("ID", String.valueOf(user.id));
		Cache.set(String.valueOf(user.id), user);
		return ok(index.render("Welcome," + user.username, (List<Patterns>)Cache.get("patterns"), user));	
	}
	
	@AddCSRFToken
	@With(MyAuth.class)
	public static Result regGET()
	{
		Form<User> form = Form.form(User.class);
		return ok(registerpage.render((List<Patterns>)Cache.get("patterns"), form, null, null));
	}
	
	@RequireCSRFCheck
	@With(MyAuth.class)
	public static Result register()
	{
		//do some save operations here
		Form<User> form = Form.form(User.class).bindFromRequest();
		User user;
		try{
			user = form.get();
		}catch (Exception e)
		{
			if(form.hasErrors())
			{
				return badRequest(registerpage.render((List<Patterns>)Cache.get("patterns"), form, null, form.errors().toString()));
			}
			return ok(registerpage.render((List<Patterns>)Cache.get("patterns"), form, null, form.errors().toString()));
		}
		user.password = BCrypt.hashpw(user.password, BCrypt.gensalt());
		try
		{
			user.save();
		}catch(PersistenceException e)
		{
			return badRequest(registerpage.render((List<Patterns>)Cache.get("patterns"), form, null, e.toString()));
		}
		//return ok(registerpage.render((List<Patterns>)Cache.get("patterns"), form, null, null));
        return ok(index.render("Congratulations! You have registered your account:" + user.username, (List<Patterns>)Cache.get("patterns"), null));
	}
	@With(MyAuth.class)
	public static Result logout()
	{
		String id = session("ID");
		Cache.remove(id);
		session().clear();
		return ok(index.render("You have logged out", (List<Patterns>)Cache.get("patterns"), null));	}
}
