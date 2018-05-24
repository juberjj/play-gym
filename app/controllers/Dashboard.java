package controllers;

import models.Assessment;
import models.Member;
import play.Logger;
import play.mvc.Controller;
import java.util.List;

public class Dashboard extends Controller
{
  public static void index() {
    Logger.info("Rendering Dashboard index");

      Member member = Accounts.getLoggedInMember();

      List<Assessment> assessments = member.assessments;
      Logger.info("Rendering Dashboard " + member);
      render("dashboard.html", member, assessments);
  }


     public static void addAssessment(float weight, float chest, float thigh, float upperarm, float waist, float hips) {

        Member member = Accounts.getLoggedInMember();
        Assessment assessment = new Assessment(weight, chest, thigh, upperarm, waist, hips);
         member.assessments.add(assessment);
        member.save();
        Logger.info("Adding Assessment" + member);
        redirect("/dashboard");

    }

    public static void deleteAssessment(Long id, Long assessmentid) {

        Member member = Member.findById(id);
        Assessment assessment = Assessment.findById(assessmentid);
        member.assessments.remove(assessment);
        member.save();
        assessment.delete();
        Logger.info("Deleting " + member);
        redirect("/dashboard");

    }
}
