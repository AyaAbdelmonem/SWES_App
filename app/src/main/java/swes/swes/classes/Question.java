package swes.swes.classes;

import java.util.List;

/**
 * Created by Sameh on 3/24/2017.
 */

public class Question {

    private String question;

    private String RightAnswer;
    private List<String> Answers;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }


    public String getRightAnswer() {
        return RightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        RightAnswer = rightAnswer;
    }

    public List<String> getAnswers() {
        return Answers;
    }

    public void setAnswers(List<String> answers) {
        Answers = answers;
    }


}
