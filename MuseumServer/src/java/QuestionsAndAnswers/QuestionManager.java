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
public class QuestionManager {
    
    private ArrayList<Question> questions;
    private PersistanceRepositoryQuestions repository;
    private int questionSetId;
    
    public QuestionManager(ArrayList<Question> questions, PersistanceRepositoryQuestions repository, int questionSetId) {
        this.questions = questions;
        this.repository = repository;
        this.questionSetId = questionSetId;
    }
    
    public boolean addQuestion(Question question) {
        if (repository.addQuestionToExistingQuestionSet(question, this.questionSetId)) {
            this.questions.add(question);
            return true;
        }
        return false;
    }
    
    public boolean removeQuestion(Question question) {
        if (repository.removeQuestion(question, this.questionSetId)) {
            this.removeQuestionFromQuestionSet(question);
            return true;
        }
        return false;
    }
    
    private void removeQuestionFromQuestionSet(Question question) {
        
        for (Question q : questions) {
            if (q.getQuestionId() == q.getQuestionId()) {
                questions.remove(q);
            }
        }
    }
}
