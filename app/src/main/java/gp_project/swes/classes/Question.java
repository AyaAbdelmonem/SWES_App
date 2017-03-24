package gp_project.swes.classes;

import java.util.List;

/**
 * Created by Sameh on 3/24/2017.
 */

public class Question {

    private String question;
    private  int question_number;
    private String RightAnswer;
    private int RightAnswerIndex;
    private List<String> Answers;
    private Boolean IsRight;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getQuestion_number() {
        return question_number;
    }

    public void setQuestion_number(int question_number) {
        this.question_number = question_number;
    }

    public String getRightAnswer() {
        return RightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        RightAnswer = rightAnswer;
    }

    public int getRightAnswerIndex() {
        return RightAnswerIndex;
    }

    public void setRightAnswerIndex(int rightAnswerIndex) {
        RightAnswerIndex = rightAnswerIndex;
    }

    public List<String> getAnswers() {
        return Answers;
    }

    public void setAnswers(List<String> answers) {
        Answers = answers;
    }

    public Boolean getRight() {
        return IsRight;
    }

    public void setRight(Boolean right) {
        IsRight = right;
    }
}
