package controllers;

import models.*;
import play.*;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render(
                Project.find.all(),
                Task.find.all()
        ));
    }

}
