package gp_project.swes.classes;

import java.util.List;

/**
 * Created by Sameh on 3/24/2017.
 */

public class Student {

    private  String Name ;
    private  String Password;
    private  String Email;
    private  String StudentPicture;
    private List<Video> videos;
    private  List<Test> tests;
    private List<Comment> comments;
    private  List<CaseStudy> caseStudies;

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

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<CaseStudy> getCaseStudies() {
        return caseStudies;
    }

    public void setCaseStudies(List<CaseStudy> caseStudies) {
        this.caseStudies = caseStudies;
    }



}

