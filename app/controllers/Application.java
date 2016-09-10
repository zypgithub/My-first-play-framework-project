package controllers;

import play.*;
import play.cache.Cache;
import play.mvc.*;

import views.html.*;
import views.html.common.*;

import java.util.List;

import models.Patterns;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Welcome, your application is running now", (List<Patterns>)Cache.get("patterns")));
    }

}
