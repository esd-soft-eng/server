/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistance;

import QuestionsAndAnswers.Answer;
import QuestionsAndAnswers.Question;
import QuestionsAndAnswers.QuestionSet;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author neil
 */
public class PersistanceRepositoryQuestions {

    private DatabaseQueryExecutor executor;

    public PersistanceRepositoryQuestions(DatabaseQueryExecutor executor) {
        this.executor = executor;
    }
    
    public synchronized ArrayList<QuestionSet> getAllQuestionSets() {

        String sql = "SELECT * FROM"
                + "`questionset` `qs`";

        ResultSet rs = executor.executeStatement(sql);

        return this.generateQuestionSetListFromResults(rs);
    }

    public synchronized ArrayList<Question> getQuestionSet() {

        return new ArrayList<Question>();
    }

    private synchronized ArrayList<QuestionSet> generateQuestionSetListFromResults(ResultSet rs) {
        
        ArrayList<QuestionSet> questionSetList = new ArrayList();
        
        try {
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                ArrayList<Question> questionList = this.getQuestionSetQuestions(id);
                questionSetList.add(new QuestionSet(id, name, questionList));
           
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersistanceRepositoryQuestions.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return questionSetList;
    }

    private synchronized ArrayList<Question> getQuestionSetQuestions(int id) {
        
        String sql = "SELECT * FROM"
                + "`question`.`q`"
                + "WHERE `q`.`id` = " + id;
        
        ResultSet rs = executor.executeStatement(sql);
        
        return this.generateQuestionsSetQuestionsFromResults(rs);        
    }
    
    private synchronized ArrayList<Question> generateQuestionsSetQuestionsFromResults(ResultSet rs){
        
        ArrayList<Question> questionList = new ArrayList();
        try {
            while(rs.next()){
                int id = rs.getInt("id");
                String questionText = rs.getString("text");
                ArrayList<Answer> questionAnswers = this.getQuestionAnswers(id);
                questionList.add(new Question(id, questionText, questionAnswers));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersistanceRepositoryQuestions.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return questionList;
    }
    
    private ArrayList<Answer> getQuestionAnswers(int id) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    
    
    
    
    
    
        public synchronized boolean addQuestionToQuestionSet(Question question, int questionSetId) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public synchronized boolean removeQuestion(Question question, int questionSetId) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    

}
