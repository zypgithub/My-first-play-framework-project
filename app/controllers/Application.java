package controllers;

import annotating.MyAuth;
import play.*;
import play.cache.Cache;
import play.mvc.*;

import views.html.*;
import views.html.common.*;

import java.util.List;

import models.Patterns;
import models.User;

public class Application extends Controller {

	@With(MyAuth.class)
    public static Result index() {
		User user = (User) ctx().args.get("user");
    	/*
    	String id = session("ID");
    	User user;
		if(id == null)
			user = null;
		else
			user = (User)Cache.get(id);			
		*/
		//String hehe = (String)ctx().args.get("aa");
		//if(hehe != null)
		//	return ok(hehe);
		return ok(index.render("Welcome, your application is running now", (List<Patterns>)Cache.get("patterns"), user));
    }

}
