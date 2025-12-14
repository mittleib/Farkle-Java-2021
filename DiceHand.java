/* PROGRAMMER:  Brynne Mittleider
 * CLASS: AP Computer Science
 * ASSIGNMENT: Farkle
 * 
 * CERTIFICATION: I certify that this work is my own and that
 *                none of it is the work of any other person.
 */

package mittleiderb.farkle;

import java.util.ArrayList;

public class DiceHand {
    private Die[] cube;
    ArrayList<Die> rollingHand;
    ArrayList<Die> scoringDice;
    
    public DiceHand() {
        cube = new Die[6];
        
        for (int i = 0; i < 6; i++) {
            Die bob = new Die();
            cube[i] = bob;
        }
        rollingHand = new ArrayList<>();
        for (Die die : cube) {
            rollingHand.add(die);
        }
        scoringDice = new ArrayList<>();
    }
    
    public void resetRollingHand() {
        rollingHand.clear();
        for (Die die : cube) {
            rollingHand.add(die);
        }
    }
    
    public int rHSize() {
        return rollingHand.size();
    }
    
    public void roll() {
        scoringDice.clear();
        
        for (Die die : rollingHand) {
            die.roll();
        }
        
        setScoringDice();
        
    }
    
    private void setScoringDice() {
        int c1 = 0;
        int c2 = 0;
        int c3 = 0;
        int c4 = 0;
        int c5 = 0;
        int c6 = 0;
            
        for (Die die : rollingHand) {
            if (die.getSide() == 1) {
                scoringDice.add(die);
                c1++;

            }
            if (die.getSide() == 2) {
                c2++;
            }
            if (die.getSide() == 3) {
                c3++;
            }
            if (die.getSide() == 4) {
                c4++;
            }
            if (die.getSide() == 5) {
                scoringDice.add(die);
                c5++;
            }
            if (die.getSide() == 6) {
                c6++;
            }
            
        }
        
        repeatHelper(scoringDice, c1, 1);
        repeatHelper(scoringDice, c2, 2);
        repeatHelper(scoringDice, c3, 3);
        repeatHelper(scoringDice, c4, 4);
        repeatHelper(scoringDice, c5, 5);
        repeatHelper(scoringDice, c6, 6);
    }
    
    
    private void repeatHelper (ArrayList<Die> scoringDice, int counter, int num) {
        counter = (counter / 3) * 3;
        if (counter == 3 || counter == 6) {
            int track = 0;
            for (Die die : rollingHand) {
                if (track != counter) {
                    if (die.getSide() == num) {
                        boolean inDice = false;
                        for (Die d : scoringDice) {
                            if (die == d) {
                                inDice = true;
                            }
                        }
                        if (!inDice) {
                            scoringDice.add(die);
                            track++;
                        }
                    }
                }
            }
        }
    }
    
    
    
    public ArrayList<Integer> getScoringDice() {
        ArrayList<Integer> list = new ArrayList<>();
        for (Die die : scoringDice) {
            list.add(die.getSide());
        }
        return list;
    }
    
    public int points(ArrayList<Integer> settingAsideDice) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int num : settingAsideDice) {
            list.add(num);
        }
        
        for (int num : list) {
            boolean isThere = false;
            for (int i = 0; i < rollingHand.size(); i++) {
                if (!isThere) {
                    if (num == rollingHand.get(i).getSide()) {
                        rollingHand.remove(i);
                        isThere = true;
                    }
                }
            }
        }
        
        int points = 0;
        
        for (int die : settingAsideDice) {
            if (die == 1) {
                points += 100;

            }
            if (die == 5) {
                points += 50;
            }
        }
        
        int count1 = 0;
        int num1 = -1;
        int count2 = 0;
        int num2 = -1;
        int c1 = 0;
        int c5 = 0;
        for (int n : settingAsideDice) {
            if (n != 1 && n != 5) {
                if (num1 == -1 || num1 == n) {
                    num1 = n;
                    count1++;
                } else if (num2 == -1 || num2 == n) {
                    num2 = n;
                    count2++;
                    }
            }
            if (n == 1) {
                c1++;
            }
            if (n == 5) {
                c5++;
            }
            
        }
        
        if (num1 != -1 && num1 != 1) {
            points += (count1 / 3) * num1 * 100;
        }
        points += (c1 / 3) * 1000;
        points += (c5 / 3) * 500;
        if (num2 != -1 && num2 != 1) {
            points += (count2 / 3) * num2 * 100;
        }
        
        return points;
    }
    
    public boolean goodChoice(ArrayList<Integer> chosenDice) {
        ArrayList<Integer> given = getScoringDice();
        
        for (int num : chosenDice) {
            boolean isThere = false;
            for (int k = 0; k < given.size(); k++) {
                if (isThere != true && num == given.get(k)) {
                    given.set(k,-1);
                    isThere = true;
                }
            }
            if (isThere == false) {
                return false;
            }
        }
        int count1 = 0;
        int num1 = -1;
        int count2 = 0;
        int num2 = -1;
        for (int n : chosenDice) {
            if (n != 1 && n != 5) {
                if (num1 == -1 || num1 == n) {
                    num1 = n;
                    count1++;
                } else if (num2 == -1 || num2 == n) {
                    num2 = n;
                    count2++;
                    }
                if (n != num1 && n != num2) {
                    return false;
                }
            }
        }
        if (count1 % 3 != 0 || count2 % 3 != 0) {
            return false;
        }
        return true;
    }
    
    
    
    
    public String printHand() {
        String print = "";
        for (Die die : rollingHand) {
            print += die.print();
        }
        return print;
    }
    
    public void setRollingHand() {
        rollingHand.clear();
        for (Die die : cube) {
            rollingHand.add(die);
        }
    }
    
    
}