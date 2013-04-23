package QuestionsAndAnswers;

/**
 *
 * @author Neil Donnelly <neil.m.donnelly@gmail.com>
 */
public enum Levels {

    PHD(50), UNI(40), ALEVEL(30), GCSE(20);

    private final int score;

    Levels(int score) {
        this.score = score;
    }

    public int getScore() {
        return this.score;
    }
}
