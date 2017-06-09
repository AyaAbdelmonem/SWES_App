package swes.swes.classes;

import java.util.List;

/**
 * Created by Sameh on 3/24/2017.
 */

public class Level {

    private List<Lesson> lessons;
    private  CaseStudy caseStudy;
    private  String LevelName;
    private  int LevelNumber;

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public CaseStudy getCaseStudy() {
        return caseStudy;
    }

    public void setCaseStudy(CaseStudy caseStudy) {
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
}
