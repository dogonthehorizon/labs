package controllers;

import models.*;
import play.Routes;
import play.mvc.*;
import play.data.*;
import views.html.login;
import views.html.index;

public class Application extends Controller {

    @Security.Authenticated(Secured.class)
    public static Result index() {
        return ok(index.render(
                Project.findInvolving(request().username()),
                Task.findTodoInvolving(request().username()),
                User.find.byId(request().username())
        ));
    }

    public static Result login() {
        return ok(
                login.render(Form.form(Login.class))
        );
    }

    public static Result authenticate() {
        Form<Login> loginForm = Form.form(Login.class).bindFromRequest();

        if (loginForm.hasErrors()) {
            return badRequest(login.render(loginForm));

        } else {

            session().clear();
            session("email", loginForm.get().email);
            return redirect(
                    routes.Application.index()
            );
        }
    }

    public static Result logout() {
        session().clear();
        flash("success", "You've been logged out.");
        return redirect(
                routes.Application.login()
        );
    }

    // todo: debug why this action is causing errors
    public static Result javascriptRoutes() {

        response().setContentType("text/javascript");

        return ok(
                Routes.javascriptRouter("jsRoutes",
                        controllers.routes.javascript.Projects.add(),
                        controllers.routes.javascript.Projects.delete(),
                        controllers.routes.javascript.Projects.rename(),
                        controllers.routes.javascript.Projects.addGroup()
                )
        );
    }

    public static class Login {
        public String email;
        public String password;

        public String validate() {
            if (User.authenticate(email, password) == null) {
                return "Invalid user or password";
            }
            return null;
        }
    }

}
