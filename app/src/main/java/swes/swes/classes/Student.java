package swes.swes.classes;

import java.util.List;

/**
 * Created by Sameh on 3/24/2017.
 */

public class Student {

    private  String Name ;
    private  String Password;
    private  String Email;
    private  String StudentPicture;
    private  Boolean is_verified = false;



    public Boolean getIs_verified() {
        return is_verified;
    }

    public void setIs_verified(Boolean is_verified) {
        this.is_verified = is_verified;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getStudentPicture() {
        return StudentPicture;
    }

    public void setStudentPicture(String studentPicture) {
        StudentPicture = studentPicture;
    }








}

