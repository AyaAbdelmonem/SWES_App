package gp_project.swes.classes;

import java.util.List;

/**
 * Created by Sameh on 3/24/2017.
 */

public class Lesson {

    private int Lesson_number;
    private  String Lesson_name;
    private float TotalRate;
    private int RateCount;
    private Test test;
    private List<Video> videos;
    private List<Refrences> refrences;
    private  List<Comment> comments;

    public int getLesson_number() {
        return Lesson_number;
    }

    public void setLesson_number(int lesson_number) {
        Lesson_number = lesson_number;
    }

    public String getLesson_name() {
        return Lesson_name;
    }

    public void setLesson_name(String lesson_name) {
        Lesson_name = lesson_name;
    }

    public float getTotalRate() {
        return TotalRate;
    }

    public void setTotalRate(float totalRate) {
        TotalRate = totalRate;
    }

    public int getRateCount() {
        return RateCount;
    }

    public void setRateCount(int rateCount) {
        RateCount = rateCount;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

    public List<Refrences> getRefrences() {
        return refrences;
    }

    public void setRefrences(List<Refrences> refrences) {
        this.refrences = refrences;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
