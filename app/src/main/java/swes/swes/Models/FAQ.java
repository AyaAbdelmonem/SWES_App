package swes.swes.Models;

/**
 * Created by Sameh on 5/1/2017.
 */

public class FAQ {

    private String Question;
    private  String Answer;

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String getAnswer() {
        return Answer;
    }

    public FAQ(String question, String answer) {
        Question = question;
        Answer = answer;
    }

    public FAQ() {

    }

    public void setAnswer(String answer) {
        Answer = answer;
    }

}
