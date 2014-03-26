package controllers;

import play.*;
import play.mvc.*;
import play.mvc.Http.*;
import play.mvc.Security.*;

import models.*;

public class Secured extends Authenticator {

    @Override
    public String getUsername(Context ctx) {
        return ctx.session().get("email");
    }

    @Override
    public Result onUnauthorized(Context ctx) {
        return redirect(routes.Application.login());
    }
}
