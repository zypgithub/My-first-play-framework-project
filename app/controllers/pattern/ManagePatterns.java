package controllers.pattern;

import play.mvc.*;
import play.cache.Cache;
import play.data.DynamicForm;
import play.data.Form;
import play.filters.csrf.*;

import java.util.List;

import controllers.routes;
import models.*;

import views.html.*;
import views.html.patterntemplate.*;

public class ManagePatterns extends Controller{
	
	@AddCSRFToken
	public static Result addForm()
	{
		Form<Patterns> form = Form.form(Patterns.class);
		return ok(addpattern.render((List<Patterns>)Cache.get("patterns"), form));
	}
	
	@RequireCSRFCheck
	public static Result addPost()
	{
		Form<Patterns> form = Form.form(Patterns.class).bindFromRequest();
		Patterns p = form.get();
		//DynamicForm dform = Form.form().bindFromRequest();
		p.URL = "/" + p.name; 
		p.save();
		Cache.set("patterns", Patterns.findAll());
		return redirect(routes.Application.index());
	}

}
