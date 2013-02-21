/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistance;

import QuestionsAndAnswers.Question;
import java.util.ArrayList;

/**
 *
 * @author neil
 */
public class PersistanceRepositoryQuestions {

    public synchronized ArrayList<Question> getQuestionSet(){
       
        return new ArrayList<Question>();
    }
    
    public synchronized boolean addQuestionToQuestionSet(Question question, int questionSetId) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public synchronized boolean removeQuestion(Question question, int questionSetId) {
        throw new UnsupportedOperationException("Not yet implemented");
    }    
}
