/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QuestionsAndAnswers;

import java.util.ArrayList;

/**
 *
 * @author neil
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

    public boolean answerQuestion(int answer) {

        if (answer > answers.size() - 1) {
            return false;
        }

        this.answer = answer;
        return true;
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
    
    public int getQuestionId(){
        return this.id;
    }

    public int getScore() {

        Answer answerToValue = answers.get(this.answer);
        return answerToValue.value;
    }
    
    public Answer[] getAnswerListAsArray(){
        return this.answers.toArray(new Answer[0]);        
    }
}
