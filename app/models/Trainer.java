package models;

import javax.persistence.Entity;

/**
 * This is the Trainer class which stores: - Trainers email - Trainers name -
 * Trainers address - Trainers gender (M/F) - Trainers speciality
 *
 *
 * @author Juber Nunes
 */
@Entity
public class Trainer extends Person {

    private String speciality;



    /**
     * Constructor for objects of the Trainer class
     *
     * @param email      Trainers email
     * @param name       Trainers Name
     * @param address    Trainers Address
     * @param gender     Trainers Gender (M/F)
     * @param speciality Trainers Speciality
     */
    public Trainer(String name, String email, String address, String gender, String password, String speciality) {

        super(email, name, password);
        this.address = address;
        this.gender = gender;
        this.speciality = speciality;
    }

    // ------
    // getter
    // ------


    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getGender() {
        return gender;
    }

    /**
     * @return Trainers speciality
     */
    public String getSpeciality() {
        return speciality;
    }

    // ------
    // setter
    // ------


    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Set Trainers speciality
     *
     * @param speciality Sets what the trainers speciality is
     */
    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    /**
     * @return User-friendly String representing the current Trainer
     */
    @Override
    public String toString() {
        return super.toString() + "/Speciality: " + speciality;
    }
}
