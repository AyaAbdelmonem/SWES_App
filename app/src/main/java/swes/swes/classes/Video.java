package swes.swes.classes;

import java.util.List;

/**
 * Created by Sameh on 3/24/2017.
 */

public class Video {

    private String name;
    private String link;
    private VideoType video;
    private Boolean seen;
    private List <Student> student;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public VideoType getVideo() {
        return video;
    }

    public void setVideo(VideoType video) {
        this.video = video;
    }

    public Boolean getSeen() {
        return seen;
    }

    public void setSeen(Boolean seen) {
        this.seen = seen;
    }

    public List<Student> getStudent() {
        return student;
    }

    public void setStudent(List<Student> student) {
        this.student = student;
    }
}
