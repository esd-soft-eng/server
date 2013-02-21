/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package businessDomainObjects;

/**
 *
 * @author neil
 */
public class Answer {
    
    public int value;
    public String answerText;
    private int id;
    
    public Answer(int value, String answerText){
        this.value = value;
        this.answerText = answerText;
    }
}
