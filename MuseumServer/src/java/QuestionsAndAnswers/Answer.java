/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QuestionsAndAnswers;

/**
 *
 * @author neil
 */
public class Answer {
    
    public int value;
    public String answerText;
    private int id;
    
    public Answer(String answerText, int value){
        this.value = value;
        this.answerText = answerText;
    }
}
