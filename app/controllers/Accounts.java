package controllers;

import models.Member;
import models.Person;
import models.Trainer;
import play.Logger;
import play.mvc.Controller;

public class Accounts extends Controller
{
    public static void signup()
    {
        render("signup.html");
    }

    public static void login()
    {
        render("login.html");
    }

    public static void register(String name, String gender, String email, String password, String address, float height, float startingweight){
        try {
            Logger.info("Registering new user " + email);
            Member member = new Member(name, gender, email, password, address, height, startingweight);
            member.save();
            redirect("/login");
        } catch (Exception e){
            Logger.error("Registration failed");
            flash.put("message", "ERR_SIGNUP_FAILURE");
            redirect("/signup");
        }
    }

    public static void authenticate(String email, String password){
        Logger.info("Attempting to authenticate with " + email + ":" + password);

        // using instance of in order to distinguish Members and Trainer
        Person person = Member.find("email", email).first();
        person = (person == null) ? Trainer.find("email", email).first() : person;

        Logger.info("Authentication successful " + person);

        if (person != null && person.checkPassword(password)) {
            Logger.info("Authentication successful " + person);
            //session.put("logged_in_Personid", person.id);
            session.put("logged_in_Memberid", person.id);
            if (person instanceof Member)

                redirect("/dashboard");
            if (person instanceof Trainer) redirect("/admin");

        } else {
            Logger.info("Authentication failed");
            redirect("/login");
        }

    }

    public static void logout(){
        session.clear();
        redirect ("/");
    }


    public static Member getLoggedInMember(){
        Member member = null;
        Logger.info("Logger " + member);
        if (session.contains("logged_in_Memberid")) {
            //String memberId = session.get("logged_in_Memberid"); // that was my issue from previous commit
            String memberId = session.get("logged_in_Memberid");
            member = Member.findById(Long.parseLong(memberId));

        } else {
            login();
        }
        return member;
    }

    /**
     * Retrieves currently logged in gym member and renders the app/views/settings.html template;
     * passes the member details to the view so the form can be pre-populated
     */
    public static void settings() {
        Member member = (Member) Accounts.getLoggedInMember();
        Logger.info(" Member " + Accounts.getLoggedInMember());
        render("settings.html", member);
    }

    public static void update(
            String name,
            String password,
            String address,
            String gender,
            float height,
            float startingweight) {

        Member member = (Member) Accounts.getLoggedInMember();
        // validate fields that were updated
        if (member != null) {
            if (name != null && !name.isEmpty()) member.setName(name);
            if (password != null && !password.isEmpty()) member.setPassword(password);
            if (address != null && !address.isEmpty()) member.setAddress(address);
            if (gender != null && !gender.isEmpty()) member.setGender(gender);
            if (height != 0.0f) member.setHeight(height);
            if (startingweight != 0.0f) member.setStartingweight(startingweight);
            member.save();
        }
        render("dashboard.html", member);
    }
}