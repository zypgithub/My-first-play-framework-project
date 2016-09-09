package controllers;

import play.mvc.*;
import play.data.DynamicForm;
import play.data.Form;
import play.filters.csrf.*;

import models.*;

import views.html.*;

public class ManagePatterns extends Controller{
	
	@AddCSRFToken
	public static Result addForm()
	{
		Form<Patterns> form = Form.form(Patterns.class);
		return ok(addpattern.render(Patterns.findAll(), form));
	}
	
	@RequireCSRFCheck
	public static Result addPost()
	{
		Form<Patterns> form = Form.form(Patterns.class).bindFromRequest();
		Patterns p = form.get();
		DynamicForm dform = Form.form().bindFromRequest();
		p.URL = dform.get("URL");
		p.save();
		return ok(p.URL);
	}

}
