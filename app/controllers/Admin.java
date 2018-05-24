package controllers;

import java.util.List;
import models.Assessment;
import models.Member;
import models.Trainer;
import play.Logger;
import play.mvc.Controller;

public class Admin extends Controller {

    /** Default action, renders the app/views/admin.html template */
    public static void index() {
        List<Member> members = Member.findAll();
        render("admin.html",  members);
    }


    public static void showMember(Long memberid) {
        Member member = Member.findById(memberid);
        render("assessments.html", member);
    }

    // As per delete todolist v2
    public static void deleteMember(Long memberid) {
        Member member = Member.findById(memberid);
        member.delete();
        Logger.info("Deleting member " + member.getName());
        index();
    }

}