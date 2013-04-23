package QuestionsAndAnswers;

/**
 *
 * @author Neil Donnelly <neil.m.donnelly@gmail.com>
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
