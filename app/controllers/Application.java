package controllers;

import play.*;

import play.mvc.*;

import views.html.*;

import models.Patterns;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Your new application is ready.", Patterns.findAll()));
    }

}
