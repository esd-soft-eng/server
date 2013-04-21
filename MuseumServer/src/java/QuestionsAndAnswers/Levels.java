package QuestionsAndAnswers;

/**
 *
 * @author neil
 */
public enum Levels {

    PHD(50), UNI(40), ALEVEL(30), GCSE(20), PRIMARY(10);

    private final int score;

    Levels(int score) {
        this.score = score;
    }

    public int getScore() {
        return this.score;
    }
}
