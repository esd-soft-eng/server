/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import QuestionsAndAnswers.Levels;

/**
 *
 * @author neil
 */
public class LevelCalculator {

    public static Levels CalculateLevel(int score) {

        for (Levels level : Levels.values()) {
            
            if (score >= level.getScore()){
                return level;
            }
        }
        return Levels.PRIMARY;
    }
}
