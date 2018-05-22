package models;

import play.db.jpa.Model;

public class Person extends Model{
    private String email;
    private String name;
    private String address;
    private String gender;


    public Person(String email, String name, String address, String gender) {

        this.email = email;
        setName(name);
        this.address = address;
        setGender(gender);

    }

    //-------
    //getters
    //-------

    /**
     * @return returns the persons email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return rturns the persons name
     */
    public String getName() {
        return name;
    }

    /**
     * @return returns the persons address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @return returns the persons gender
     */
    public String getGender() {
        return gender;
    }



    //-------
    //setters
    //-------

    /**
     * Updates the persons gender field.
     *
     * @param gender The persons gender i.e. can be either "M" or "F".
     *               If not entered it defaults to unspecified .
     */

    public void setGender(String gender) {
        if ((gender.toUpperCase().equals("M")) || (gender.toUpperCase().equals("F"))) {
            this.gender = gender.toUpperCase();
        } else {
            this.gender = "Unspecified";
        }
    }

    /**
     * Updates the persons address field
     *
     * @param address There is no validation on the persons address.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Name needs to be truncated to 30 chars
     *
     * @param name
     */

    public void setName(String name) {

        if (name.length() <= 30) {
            this.name = name;
        } else {
            this.name = name.substring(0, 30);
        }
    }

    /**
     * Updates the persons email field
     *
     * @param email There is no validation on the persons email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return User-friendly String representing the Person
     */
    @Override
    public String toString() {
        return "Email: " + email + "/ Name: " + name + "/ Address: " + address + "/ Gender: " + gender;
    }

}
