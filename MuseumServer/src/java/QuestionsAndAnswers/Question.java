package QuestionsAndAnswers;

import java.util.ArrayList;

/**
 *
 * @author Neil Donnelly <neil.m.donnelly@gmail.com>
 */
public class Question {

    private ArrayList<Answer> answers;
    private int answer;
    private String questionText;
    private int id;

    public Question(int id, String questionText, ArrayList answers) {

        this.id = id;
        this.questionText = questionText;
        this.answers = answers;
        this.answer = 0;
    }

    public String getQuestionText() {
        return this.questionText;
    }

    public String[] getAnswerText() {

        String[] answerTexts = new String[answers.size()];
        for (int i = 0; i < this.answers.size(); i++) {
            answerTexts[i] = this.answers.get(i).answerText;
        }
        return answerTexts;
    }

    public int getQuestionId() {
        return this.id;
    }

    public int getScore(int answer) {

        if (answer > answers.size() - 1) {
            return 0;
        }
        return answers.get(answer).value;
    }

    public Answer[] getAnswerListAsArray() {
        return this.answers.toArray(new Answer[0]);
    }
}
