package swes.swes.classes;

import java.util.ArrayList;

/**
 * Created by win on 01/05/2017.
 */

public class CourseInfo {
    String photo;
    ArrayList<Ref> prerequisets ;
    String desc;

    public CourseInfo() {
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public ArrayList<Ref> getPrerequisets() {
        return prerequisets;
    }

    public void setPrerequisets(ArrayList<Ref> prerequisets) {
        this.prerequisets = prerequisets;
    }

    public String getDesc() {

        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}