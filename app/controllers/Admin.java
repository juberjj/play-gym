package controllers;


import models.Assessment;
import models.Todo;
import play.Logger;
import play.data.binding.As;
import play.mvc.Controller;

import java.util.List;

public class Admin extends Controller {

    public static void index() {
        Logger.info("Rendering admin");
        //List<Todo> todolist = Todo.findAll();
        List<Assessment> assessments = Assessment.findAll();

        render ("admin.html",assessments);
    }
}
