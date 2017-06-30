package swes.swes.classes;

import java.util.List;

/**
 * Created by Sameh on 3/24/2017.
 */

public class Level implements Comparable<Level> {

    private List<Lesson> Lessons;
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


    public  List<Lesson> getLessons() {
        return Lessons;
    }

    public void setLessons( List<Lesson> lessons) {
        Lessons = lessons;
    }

    @Override
    public int compareTo(Level level) {
        if (LevelNumber > level.getLevelNumber()) {
            return 1;
        }
        else if (LevelNumber < level.getLevelNumber()) {
            return -1;
        }
        else {
            return 0;
        }


    }
}
