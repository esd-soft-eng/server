/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domainObjects;

import java.util.ArrayList;

/**
 *
 * @author neil
 */
public class QuestionSet{
    
    private ArrayList<Question> questions;
    
    public QuestionSet(ArrayList<Question> questions){
        this.questions = questions;
    }
    
    public boolean answerQuestion(int question, int answer){
        
        return questions.get(question).answerQuestion(answer);
    }
    
    public int getTotalScore(){
        
        int total = 0;
        
        for(Question q : questions){
            total += q.getScore();
        }        
        return total;
    }
    
}
