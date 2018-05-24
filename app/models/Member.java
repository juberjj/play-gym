package models;

import play.db.jpa.Model;
import utils.GymUtility;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.text.DecimalFormat;
import java.util.*;

@Entity
public class Member extends Person
{
    //public String name;
    //public String gender;
    //public String email;
    //public String password;
    //public String address;
    public float height;
    public float startingweight;


   @OneToMany(cascade = CascadeType.ALL)
    //public List<Todo> todolist = new ArrayList<Todo>();
    public List<Assessment> assessments;
   // private HashMap<String, Assessment> assessments;

    public Member(String name, String gender, String email, String password, String address, float height, float startingweight)
    {
        super(name, email, address,gender, password);
        //this.name = name;
        //this.gender = gender;
        //this.email = email;
        //this.password = password;
        //this.address = address;
        this.height = height;
        this.startingweight = startingweight;
        this.assessments = new ArrayList<>();
    }

    public static Member findByEmail(String email)
    {
        return find("email", email).first();
    }

    public boolean checkPassword(String password)
    {
        return this.password.equals(password);
    }

    // getters

    public float getHeight() {

        return height;
    }

    public String getGender() {

        return gender;
    }
    public String getName(){

        return name;
    }

    public float getStartingweight() {

        return startingweight;
    }

    public List<Assessment> getSortedAssessments() {
        ArrayList<Assessment> sortedAssessments = new ArrayList();
        sortedAssessments.addAll(assessments);
        Collections.sort(sortedAssessments, (arg0, arg1) -> arg1.getTimeStamp().compareTo(arg0.getTimeStamp()));
        return sortedAssessments;
    }



    public Assessment getLatestAssessment() {
        return (assessments == null || assessments.isEmpty()) ? null : getSortedAssessments().get(0);
    }

    public double getCurrentBmi() {
        if (assessments == null || assessments.isEmpty()) {
           // return 0.0d;
            DecimalFormat df = new DecimalFormat("#.#");
            return Double.valueOf(df.format(GymUtility.calculateBMI(this)));
        }
        DecimalFormat df = new DecimalFormat("#.#");
        return Double.valueOf(df.format(GymUtility.calculateBMI(this, getLatestAssessment())));
    }

    // setters

    /**
     * Updates the member's height field.
     *
     * @param height The member's height is measured in Meters. A minimum height of
     *               one meter is allowed and a maximum height of three meters.
     */

    public void setHeight(float height) {
        if (height >= 1.0f && height <= 3.0f) {
            this.height = height;
        }else{
            this.height = 0;
        }

    }

    public boolean isIdealBodyWeight() {
        return (assessments != null && !assessments.isEmpty()) && GymUtility
                .isIdealBodyWeight(this, getLatestAssessment());
    }

    public boolean initialIdealBodyWeight(){
        return GymUtility.isIdealBodyWeight(this);

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setStartingweight(float startingweight) {
        this.startingweight = startingweight;
    }

    /**
     * Updates the member starting weight field.
     *
     * @param startWeight The member's weight upon joining the gym (in kgs). A minimum
     *                       weight of 35kg and a max of 250kg.
     */

    public void setStartWeight(float startWeight) {
        if (startWeight >= 35.0f && startWeight <= 250.0f) {
            this.startingweight = startWeight;
        }else{

            this.startingweight = 0;
        }
    }

    public String getCurrentBmiCategory() {
        return GymUtility.determineBMICategory(getCurrentBmi());
    }

    public List<Assessment> getAssessments() {
        return assessments;
    }

    public void setAssessments(List<Assessment> assessments) {
        this.assessments = assessments;
    }

    /**
     * Returns assessments performed by trainers.
     *
     * @return the map key will be the date (as string), the map values will be an assessment details
     */
//    public HashMap<String, Assessment> getAssessments() {
//        return assessments;
//    }

    /**
     * @param packageChoice Abstract method for chosen package. Specs mistake
     */
    //public abstract void chosenPackage(String chosenPackage);
    public void chosenPackage(String packageChoice){}

    /**
     * @return User-friendly String representing the current Member
     */



//    @Override
//    public String toString() {
//        return super.toString() + "/ Height: " + height + "m" + "/ Starting Weight: " + startWeight + "kg"
//                + "/ Chosen Package: " + chosenPackage;
//    }

}

