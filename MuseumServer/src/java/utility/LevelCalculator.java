package utility;

import QuestionsAndAnswers.Levels;

/**
 *
 * @author Neil Donnelly <neil.m.donnelly@gmail.com>
 */
public class LevelCalculator {

    public static Levels CalculateLevel(int score) {

        for (Levels level : Levels.values()) {
            
            if (score >= level.getScore()){
                return level;
            }
        }
        return Levels.GCSE;
    }
}
