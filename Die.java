/* PROGRAMMER:  Brynne Mittleider
 * CLASS: AP Computer Science
 * ASSIGNMENT: Farkle
 * 
 * CERTIFICATION: I certify that this work is my own and that
 *                none of it is the work of any other person.
 */

package mittleiderb.farkle;

public class Die {
    int side;
    
    public Die() {
        side = 1;
    }
    
    public String print() {
        if (side == 1) {
            return """
                _____________
               |             |
               |             |
               |      O      |
               |             |
               |_____________|
               """;
        }
        if (side == 2) {
            return """
                _____________
               |             |
               |   O         |
               |             |
               |         O   |
               |_____________|
               """;
        }
        if (side == 3) {
            return """
                _____________
               |             |
               |         O   |
               |      O      |
               |   O         |
               |_____________|
               """;
        }
        if (side == 4) {
            return """
                _____________
               |             |
               |   O     O   |
               |             |
               |   O     O   |
               |_____________|
               """;
        }
        if (side == 5) {
            return """
                _____________
               |             |
               |   O     O   |
               |      O      |
               |   O     O   |
               |_____________|
               """;
        }
        if (side == 6) {
            return """
                _____________
               |             |
               |   O     O   |
               |   O     O   |
               |   O     O   |
               |_____________|
               """;
        }
        return "";
    }
    
    public void roll() {
        side = (int)(Math.random() * 6 + 1);
    }
    
    public void load(int favor) {
        int decision = (int)(Math.random() * 7 + 1);
        if (decision == favor || decision == 7) {
            side = favor;
        } else {
            side = decision;
        }
    }
    
    public int getSide() {
        return side;
    }
    
    public void load() {
        load(1);
    }
}
