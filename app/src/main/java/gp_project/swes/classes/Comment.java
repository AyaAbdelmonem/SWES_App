package gp_project.swes.classes;

/**
 * Created by Sameh on 3/24/2017.
 */

public class Comment {
    private String CommentContent;
    private Student student;

    public String getCommentContent() {
        return CommentContent;
    }

    public void setCommentContent(String commentContent) {
        CommentContent = commentContent;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
