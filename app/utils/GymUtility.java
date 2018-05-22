package utils;

import models.Assessment;
import models.Member;
import play.db.jpa.Model;

import java.text.DecimalFormat;

public class GymUtility extends Model{

    /**
     * This static method calculates the BMI value for the member.
     * <p>
     * The formula used for BMI is weight divided by the square of the height.
     *
     * @return the BMI value for the member.  The number returned is truncated to two decimal places.
     *
     * @author Juber Nunes
     **/
    public static double calculateBMI(Member member, Assessment assessment) {
        float weight = assessment.getWeight();
        float height = member.getHeight();
        double bmi = (double) weight / Math.pow(height, 2);
        return bmi;
    }

    /**
     * This static method determines the BMI category that the member belongs to.
     * <p>
     * The category is determined by the magnitude of the members BMI according to the following:
     * <p>
     * BMI less than    16    (exclusive)                     is "SEVERELY UNDERWEIGHT"
     * BMI between      16   (inclusive) and 18.5 (exclusive) is "UNDERWEIGHT"
     * BMI between      18.5 (inclusive) and 25   (exclusive) is "NORMAL"
     * BMI between      25   (inclusive) and 30   (exclusive) is "OVERWEIGHT"
     * BMI between      30   (inclusive) and 35   (exclusive) is "MODERATELY OBESE"
     * BMI greater than 35   (inclusive)                      is "SEVERELY OBESE"
     *
     * @return The format of the String is similar to this (note the double quotes around the category):
     * "NORMAL".
     */

    public static String determineBMICategory(double bmiValue) {
        if (bmiValue < 16) return "SEVERELY UNDERWEIGHT";
        else if (bmiValue >= 16 && bmiValue < 18.5) return "UNDERWEIGHT";
        else if (bmiValue >= 18.5 && bmiValue < 25) return "NORMAL";
        else if (bmiValue >= 25 && bmiValue < 30) return "OVERWEIGHT";
        else if (bmiValue >= 30 && bmiValue < 35) return "MODERATELY OBESE";
        else return "SEVERELY OBESE";
    }

    /**
     * This static method returns a boolean to indicate if the member has an ideal
     * body weight based on the Devine formula.
     * <p>
     * <pre>
     * For males, an ideal body weight is:   50 kg + 2.3 kg for each inch over 5 feet.
     * For females, an ideal body weight is: 45.5 kg + 2.3 kg for each inch over 5 feet.
     *
     * Note:  if no gender is specified, return the result of the female calculation.
     *
     * </pre>
     *
     * @return Returns true if the result of the devine formula is within 2 kgs (inclusive) of the
     * starting weight; false if it is outside this range.
     */

    public static boolean isIdealBodyWeight(Member member, Assessment assessment) {

        // member weight and height
        float weightInKg = assessment.getWeight();
        float heightInInch = metreToInchConversion(member.getHeight());
        String gender = member.getGender();

        // formula values
        float baseHeightInInch = 60f; // 5 feet x 12 = 60 inches
        float baseWeightMale = 50f;
        float baseWeightFemale = 45.5f;
        float incrementalWeight = 2.3f;

        // if member is 5 feet or less return 50kg for male and 45.5kg for female
        if (heightInInch <= baseHeightInInch) {
            return (gender.equals("M"))
                    ? (weightInKg == baseWeightMale)
                    : (weightInKg == baseWeightFemale);
        }

        // assign correct base weight
        float baseWeight = (gender.equals("M")) ? baseWeightMale : baseWeightFemale;

        // add 2.3kg for each inch over 5 feet (60 inches)
        float calculatedIdeal = baseWeight + (incrementalWeight * (heightInInch - baseHeightInInch));

        return (weightInKg > calculatedIdeal - 0.2f && weightInKg < calculatedIdeal + 0.2f);
    }

    /**
     * Converts height in meters to inches.
     *
     * @param lengthMeters length in meters as float
     * @return length in inches
     */
    public static float metreToInchConversion(float lengthMeters) {
        // 1 metre = 39.3701 inch
        return (lengthMeters * 39.3701f);
    }

    /**
     * This method returns the member weight converted from KGs to pounds.
     *
     * @return member weight converted from KGs to pounds.
     * The number returned is truncated to two decimal places.
     **/
    public static float kgToPoundConversion(float weightKg) {
        // 1 kg = 2.20462 pound
        return (weightKg * 2.20462f);
    }

    /**
     * Round up float number to one decimal place.
     *
     * @param number number to be rounded up as float
     * @return number rounded to one decimal place
     */
    public static float roundFloatTo1Decimal(float number) {
        DecimalFormat oneDForm = new DecimalFormat("#.#");
        return Float.valueOf(oneDForm.format(number));
    }


}
