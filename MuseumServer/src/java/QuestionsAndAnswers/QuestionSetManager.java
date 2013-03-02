/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QuestionsAndAnswers;

import java.util.ArrayList;
import persistance.PersistanceRepositoryQuestions;

/**
 *
 * @author neil
 */
public class QuestionSetManager {
    
    PersistanceRepositoryQuestions persistance;    
    ArrayList<QuestionSet> questionSets;
    
    public QuestionSetManager(PersistanceRepositoryQuestions persistance){
        this.persistance = persistance;
        this.init();
    }
    
    public QuestionSet[] getAllQuestionSets(){
        return this.questionSets.toArray(new QuestionSet[0]);        
    }
    
    public boolean addQuestionSet(String name){
        
        if (persistance.addNewQuestionSet(name)){
            this.init();
            return true;
        }
        return false;
    }
    
    public boolean deleteQuestionSet(int index){
        
        if (persistance.removeQuestionSet(this.questionSets.get(index))){
            this.questionSets.remove(this.questionSets.get(index));
            return true;
        }
        return false;
    }

    private void init() {
        this.questionSets = persistance.getAllQuestionSets();
    }
}
