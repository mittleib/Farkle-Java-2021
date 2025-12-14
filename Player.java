/* PROGRAMMER:  Brynne Mittleider
 * CLASS: AP Computer Science
 * ASSIGNMENT: Farkle
 * 
 * CERTIFICATION: I certify that this work is my own and that
 *                none of it is the work of any other person.
 */

package mittleiderb.farkle;

import java.util.ArrayList;

public class Player {
    DiceHand hand;
    int totalScore;
    int name;
    int roundScore;
    
    public Player(int num) {
        name = num;
        hand = new DiceHand();
        totalScore = 0;
    }
    
    public Player() {
        name = 1;
        hand = new DiceHand();
        totalScore = 0;
    }
    
    public ArrayList<Integer> rollingProcess() {
        hand.roll();
        System.out.println(hand.printHand());
        return hand.getScoringDice();
    }
    
    public void reset() {
        hand.resetRollingHand();
    }
    
    public int rHSize() {
        return hand.rHSize();
    }
    
    public String showScore() {
        return "Player " + name + " total score: " + totalScore;
    }
    
    public boolean checkDiceAnswer(ArrayList<Integer> notHand) {
        if (!hand.goodChoice(notHand)) {
            return false;
        }
        return true;
    }
    
    public int getRoundScore() {
        return roundScore;
    }
    
    public int getScore() {
        return totalScore;
    }
    
    public void addToRoundScore(ArrayList<Integer> scoreChosen) {
        roundScore += hand.points(scoreChosen);
    }
    
    public void resetRoundScore() {
        roundScore = 0;
    }
    
    public void addScore() {
        totalScore += roundScore;
        roundScore = 0;
    }
    
}
