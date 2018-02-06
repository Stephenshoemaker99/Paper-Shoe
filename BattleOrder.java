import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class BattleOrder here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BattleOrder extends Actor {
    private List<Character> order;
    private boolean inTurn;
    private int turnIndex;
    private World world;

    public BattleOrder(List<Character> initialOrder, World initialWorld) {
        order = organizeOrder(initialOrder);
        for (Character character : order) {
            character.setOrder(this);
        }
        inTurn = false;
        turnIndex = 0;
        world = initialWorld;
    }

    public static List<Character> organizeOrder(List<Character> input) {
        int length = input.size();//bubblesort method 
        for (int index = 0; index < length - 1; index++) {
            for (int secondIndex = 0; secondIndex < length - index - 1; secondIndex++) {
                if (input.get(secondIndex).getDexterity() > input.get(index).getDexterity()) {
                    Character stored = input.get(secondIndex);
                    input.set(secondIndex, input.get(index));
                    input.set(index, stored);
                }
            }
        }
        return input;
    }

    public List<Character> getList() {
        return order;
    }

    public Character getCharacter(boolean charAlliance) {
        return getCharacter(charAlliance, 0);
    }

    public Character getCharacter(boolean charAlliance, int index) {//gets the character from the alliance that is a the index
        Character output = null;
        int ctr = 0;
        boolean found = false;
        for (Character test : order) {
            if (test.getAlliance() == charAlliance) {
                if (ctr == index) {
                    output = test;
                    found = true;
                }
                ctr++;
            }
        }

        return output;
    }

    public int allianceCount(boolean alliance) {// counts how many characters are in the alliance
        int output = 0;
        for (Character character : order) {
            if (character.getAlliance() == alliance) {
                output++;
            }
        }
        return output;
    }

    public boolean getInTurn() {//finds out whosever turn it is
        return inTurn;
    }

    public int size() {
        return order.size();
    }

    public void setInTurn(boolean bool) {
        inTurn = bool;
    }

    public World getWorld() {
        return world;
    }

    public Character get(int index) {
        return order.get(index);
    }

    public void act() {
        if (getInTurn()) {
            //System.out.println("In " + order.get(turnIndex).getName() + "'s turn'");
            if (turnIndex >= order.size()) {
                turnIndex--;
            } else {
                if (order.get(turnIndex).getResolved()) {
                    setInTurn(false);
                    turnIndex++;
                    if (turnIndex >= order.size()) {
                        turnIndex = 0;
                    }
                    order.get(turnIndex).go();
                }
            } // if it is someone turn it checks if that character is done then goes to the next turn
        } else {
            System.out.println("go: " + turnIndex + order.get(turnIndex).getName());

            setInTurn(true);
        }
        for (int index = 0; index < order.size(); index++) {
            if (order.get(index).getHealth().getCurrent() <= 0) {
                world.removeObject(order.get(index));

                order.remove(index);
            }
        }
        boolean sideOnePresent = false;
        boolean sideTwoPresent = false;// checking if either party has lost all members
        for (Character character : order) {
            if (character.getAlliance()) {
                sideOnePresent = true;
            } else {
                sideTwoPresent = true;
            }
        } //checking if there is at least one person on each team

        if (!sideOnePresent || !sideTwoPresent) {
            world.showText("Match Complete", world.getWidth() / 2, world.getHeight() / 2);
            Greenfoot.stop();

        }
    }
}
