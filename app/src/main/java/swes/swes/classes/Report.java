package swes.swes.classes;

/**
 * Created by Sameh on 3/24/2017.
 */

public class Report {

    private  Student student;

    private  int TotalScore ;
    private  float TotalProgressPrecentage;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getTotalScore() {
        return TotalScore;
    }

    public void setTotalScore(int totalScore) {
        TotalScore = totalScore;
    }

    public float getTotalProgressPrecentage() {
        return TotalProgressPrecentage;
    }

    public void setTotalProgressPrecentage(float totalProgressPrecentage) {
        TotalProgressPrecentage = totalProgressPrecentage;
    }
}
