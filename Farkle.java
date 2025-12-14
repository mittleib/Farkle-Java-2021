/* PROGRAMMER:  Brynne Mittleider
 * CLASS: AP Computer Science
 * ASSIGNMENT: Farkle
 * 
 * CERTIFICATION: I certify that this work is my own and that
 *                none of it is the work of any other person.
 */

package mittleiderb.farkle;

import java.util.ArrayList;
import java.util.Scanner;

public class Farkle {
    public static void main(String[] args) {
        
        int nPlayers;
        
        Scanner input = new Scanner(System.in);
        
        System.out.println("Welcome to Farkle!");
        System.out.println("Farkle is a game where players throw six dice to");
        System.out.println("accumulate points by matching several of them, risking");
        System.out.println("the loss of all points if no dice match up. Some");
        System.out.println("winning score is established before the game begins");
        System.out.println("and then players attempt to reach it, taking turns");
        System.out.println("to earn points.");
        System.out.println("\nPLEASE NOTE:");
        System.out.println("When entering die numbers (possible answers are 1-6),");
        System.out.println("separate them with or without a space. E.g.: enter \"1 3 4\"");
        System.out.println("or \"134\" for dicehand 1, 3, 4, 5, 6, 1 to select the");
        System.out.println("first (1), second (3), and third (4) dice.\n");
        
        boolean play = false;
        String fPlayers = "";
        
        while (!play) {
            System.out.print("How many players are there? ");
            fPlayers = input.nextLine();
            play = players(fPlayers);
        }
        
        nPlayers = Integer.valueOf(fPlayers);

        System.out.print("What is the maximum score? ");
        String squire = input.nextLine();
        boolean goodScore = isNum(squire);
        int maxScore;
        if (!goodScore) {
            System.out.println("The maximum score is assumed to be 10,000.");
            maxScore = 10000;
        } else {
            if (Integer.valueOf(squire) < 0) {
                System.out.println("The maximum score is assumed to be 10,000.");
                maxScore = 10000;
            } else {
                maxScore = Integer.valueOf(squire);
            }
        }
        
        Player[] people = new Player[nPlayers];
        for (int i = 1; i <= nPlayers; i++)  {
            Player person = new Player(i);
            people[i - 1] = person;
        }
        
        int score = 0;
        int winner = -1;
        while (score < maxScore) {
            
            for (int i = 0; i < nPlayers; i++) {
                if (winner == -1) {
                    System.out.println("\n\nPlayer " + (i + 1) + "'s turn.");
                    roll(people[i]);
                    score = people[i].getScore();
                    if (people[i].getScore() >= maxScore) {
                        winner = i;
                    }
                }
            }
        }
        if (maxScore == 0) {
            winner = 0;
        }
        System.out.println("Player " + (winner + 1) + " wins!");
        System.out.println("");
        for (Player person : people) {
            System.out.println(person.showScore());
        }
    }
    
    private static void roll(Player person) {
        Scanner input = new Scanner(System.in);
        System.out.println("\n\nRolling dice.\n\n");
        
        ArrayList<Integer> scoringDice = person.rollingProcess();
        boolean hotDice = false;
        if (scoringDice.size() == person.rHSize()) {
            hotDice = true;
        }
        if (scoringDice.size() != 0) {
            System.out.print("Scoring dice are:");
            for (int num : scoringDice) {
                System.out.print(" " + (num));
            }
            System.out.println("");
            System.out.print("Which do you want to use to score? ");
            ArrayList<Integer> scoreChosen = set(input.nextLine());
            boolean diceMatch = person.checkDiceAnswer(scoreChosen);

            while (scoreChosen.size() == 0 || diceMatch == false) {
                System.out.print("Invalid answer. Which do you want to use to score? ");
                scoreChosen = set(input.nextLine());
                diceMatch = person.checkDiceAnswer(scoreChosen);
            }
            System.out.println("");
            person.addToRoundScore(scoreChosen);
            System.out.println("Round score: " + person.getRoundScore());
            if (hotDice == true) {
                System.out.println("You have Hot Dice! This means if you keep playing,");
                System.out.println("you roll with all six dice but keep your previous score.");
            }
            scoreChosen.clear();
            scoringDice.clear();
            System.out.println("");
            System.out.println("Do you want to stop (and save your score) or keep playing? ");
            System.out.println("Type 'stop' to stop playing.");
            System.out.println("Type 'keep' to keep playing.");
            String stopOrKeep = input.nextLine();
            while (!stopOrKeep.toLowerCase().equals("stop") && !stopOrKeep.toLowerCase().equals("keep")) {
                System.out.println("Do you want to stop (and save your score) or keep playing?");
                System.out.println("Type 'stop' to stop playing.");
                System.out.println("Type 'keep' to keep playing.");
                stopOrKeep = input.nextLine();
            }
            System.out.println("");
            if (stopOrKeep.equals("stop")) {
                person.addScore();
                System.out.println(person.showScore());
                person.reset();
                person.resetRoundScore();

            } else {
                if (hotDice) {
                    person.addScore();
                    person.reset();
                }
                roll(person);
            }

        } else {
            System.out.println("You have farkled.");
            System.out.println(person.showScore());
            person.reset();
            person.resetRoundScore();
        }
        
        
    }
    
    
    
    private static boolean players(String num) {
        if (isNum(num)) {
            int players = Integer.valueOf(num);
            if (players < 2) {
                return false;
            }
            return true;
        } else {
            return false;
        }
    }
    
    private static boolean isNum(String num) {
        if (num.length() == 0) {
            return false;
        }
        for (int i = 0; i < num.length(); i++) {
            if (!(num.substring(i, i + 1).contains("0") || num.substring(i, i + 1).contains("1") || num.substring(i, i + 1).contains("2") || num.substring(i, i + 1).contains("3") || num.substring(i, i + 1).contains("4") || num.substring(i, i + 1).contains("5") || num.substring(i, i + 1).contains("6") || num.substring(i, i + 1).contains("7") || num.substring(i, i + 1).contains("8") || num.substring(i, i + 1).contains("9"))) {
                return false;
            }
        }
        return true;
    }
    
    private static ArrayList<Integer> set(String input) {
        ArrayList<Integer> selection = new ArrayList<>();
        
        for (int i = 0; i < input.length(); i++) {
            String link = input.substring(i, i + 1);
            if (link.equals("1") || link.equals("2") || link.equals("3") || link.equals("4") || link.equals("5") || link.equals("6")) {
                selection.add(Integer.valueOf(link));
            }
        }
        return selection;
    }
}