package controllers.userauth;

import play.*;
import play.cache.Cache;
import play.data.Form;
import play.mvc.*;
import play.filters.csrf.*;

import java.util.List;

import models.*;

import views.html.authtemplate.*;

public class UserAuth extends Controller{
	
	@AddCSRFToken
	public static Result loginGET()
	{
		Form<User> form = Form.form(User.class);
		return ok(loginpage.render((List<Patterns>)Cache.get("patterns"), form));
	}
	
	@RequireCSRFCheck
	public static Result login()
	{
		// do some stuff here
		return ok();
	}
	
	@AddCSRFToken
	public static Result regGET()
	{
		return ok(registerpage.render());
	}
	
	@RequireCSRFCheck
	public static Result register()
	{
		//do some save operations here
		return ok();
	}
}
