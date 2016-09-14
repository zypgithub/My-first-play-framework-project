package controllers.pattern;

import annotating.MyAuth;


import play.mvc.*;
import play.mvc.Security.Authenticated;
import play.cache.Cache;
import play.data.DynamicForm;
import play.data.Form;
import play.filters.csrf.*;

import java.util.List;

import controllers.routes;
import models.*;

import views.html.*;
import views.html.patterntemplate.*;
import views.html.authtemplate.*;

public class ManagePatterns extends Controller{
	
	@With(MyAuth.class)
	@AddCSRFToken
	public static Result addForm()
	{
		Form<Patterns> form = Form.form(Patterns.class);
		User user = (User) ctx().args.get("user");	
		if(user == null)
		{
			Form<User> userform = Form.form(User.class);
			return ok(loginpage.render((List<Patterns>)Cache.get("patterns"), userform, null, "Please login first"));
		}
		return ok(addpattern.render((List<Patterns>)Cache.get("patterns"), form, user));
	}
	
	@With(MyAuth.class)
	@RequireCSRFCheck
	public static Result addPost()
	{
		User user = (User) ctx().args.get("user");
		if(user == null)
		{
			Form<User> userform = Form.form(User.class);
			return ok(loginpage.render((List<Patterns>)Cache.get("patterns"), userform, null, "Please login first"));
		}
		Form<Patterns> form = Form.form(Patterns.class).bindFromRequest();
		Patterns p = form.get();
		//DynamicForm dform = Form.form().bindFromRequest();
		p.URL = "/" + p.name; 
		p.save();
		Cache.set("patterns", Patterns.findAll());
		return ok(index.render("You have added a new pattern", (List<Patterns>)Cache.get("patterns"), user));	
	}

}
