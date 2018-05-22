package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Assessment extends Model {

     public float weight;
     public float chest;
     public float thigh;
     public float upperarm;
     public float waist;
     public float hips;
     //public String comment;
     public Date timeStamp;

     public Assessment(float weight, float chest, float thigh, float upperarm, float waist, float hips) {
         this.weight = weight;
         this.chest = chest;
         this.thigh = thigh;
         this.upperarm = upperarm;
         this.waist = waist;
         this.hips = hips;
         this.timeStamp = new Date();
     }

     // -------
     // getters
     // -------

    public String getFormattedDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss");
        return sdf.format(timeStamp);
    }


    /**
      * @return returns the weight
      */
     public float getWeight() {
         return weight;
     }


     /**
      * @return returns the thigh
      */
     public float getThigh() {
         return thigh;
     }


     /**
      * @return returns the waist
      */
     public float getWaist() {
         return waist;
     }

    /**
     * @return returns the chest
     */
    public float getChest() {
        return chest;
    }

    /**
     * @return returns the upper arm
     */
    public float getUpperarm() {
        return upperarm;
    }

    /**
     * @return returns the hips
     */
    public float getHips() {
        return hips;
    }

//     /**
//      * @return returns the comment
//      */
//     public String getComment() {
//         return comment;
//     }

    public Date getTimeStamp() {
        return timeStamp;
    }

    // -------
     // setters
     // -------

     /**
      * @param trainer the trainer to set
      */
//     public void setTrainer(String trainer) {
//         this.trainer = trainer;
//     }

     /**
      * @param weight the weight to set
      */
     public void setWeight(float weight) {
         this.weight = weight;
     }

     /**
      * @param thigh the thigh to set
      */
     public void setThigh(float thigh) {
         this.thigh = thigh;
     }


     /**
      * @param waist the waist to set
      */
     public void setWaist(float waist) {
         this.waist = waist;
     }

    public void setChest(float chest) {
        this.chest = chest;
    }

    public void setUpperarm(float upperarm) {
        this.upperarm = upperarm;
    }

    public void setHips(float hips) {
        this.hips = hips;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

//    /**
//      * @param comment the comment to set
//      */
//     public void setComment(String comment) {
//         this.comment = comment;
//     }

    @Override
    public String toString() {
        return "\nDate:             "
                + getFormattedDate()
                + "\nWeight:           "
                + weight
                + "\nChest:            "
                + chest
                + "\nThigh:            "
                + thigh
                + "\nUpper Arm:        "
                + upperarm
                + "\nWaist:            "
                + waist
                + "\nHips:            "
                + hips;
    }
 }
