package swes.swes.classes;

import java.io.Serializable;

/**
 * Created by Sameh on 3/24/2017.
 */

public class Lesson implements Serializable {

    private int Lesson_number;
    private  String Lesson_name;
    private Test Test;
    private String lesson_video;
    private String story;
    private String lesson_photo;
    private String desc;



    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getLesson_photo() {
        return lesson_photo;
    }

    public void setLesson_photo(String lesson_photo) {
        this.lesson_photo = lesson_photo;
    }

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



    public Test getTest() {
        return Test;
    }

    public void setTest(Test test) {
        this.Test = test;
    }

    public String getLesson_video() {
        return lesson_video;
    }

    public void setLesson_video(String lesson_video) {
        this.lesson_video = lesson_video;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

}
