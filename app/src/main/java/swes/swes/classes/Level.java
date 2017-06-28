package swes.swes.classes;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by Sameh on 3/24/2017.
 */

public class Level implements Serializable {

    private Map<String,Lesson> Lessons;
    private  String caseStudy;
    private  String LevelName;
    private  int LevelNumber;


    public String getCaseStudy() {
        return caseStudy;
    }

    public void setCaseStudy(String caseStudy) {
        this.caseStudy = caseStudy;
    }

    public String getLevelName() {
        return LevelName;
    }

    public void setLevelName(String levelName) {
        LevelName = levelName;
    }

    public int getLevelNumber() {
        return LevelNumber;
    }

    public void setLevelNumber(int levelNumber) {
        LevelNumber = levelNumber;
    }


    public Map<String, Lesson> getLessons() {
        return Lessons;
    }

    public void setLessons(Map<String, Lesson> lessons) {
        Lessons = lessons;
    }
}
