import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Character {
    private List<Button> buttons = new ArrayList<Button>();
    private boolean firstPass;

    public Player(Health initialHealth, int initialDexterity, Battlefield initialWorld) {
        super("Player", initialHealth, initialDexterity, initialWorld);
        setAlliance(true);
        buttons.add(new ButtonFireball());
        buttons.add(new ButtonIceLance());
        firstPass = true;
    }

    private void setFirstPass(boolean bool) {
        firstPass = bool;
    }//useful for the first pass in order to add buttons properly

    private boolean getFirstPass() {
        return firstPass;
    }

    public void addButtons() {
        System.out.println(getWorld());
        for (int index = 0; index < buttons.size(); index++) {
            getWorld().addObject(buttons.get(index), ((getWorld().getWidth() / 4) + index * 50),
                    (getWorld().getHeight() - 25));
        }

    }

    public void act() {

        if (!getResolved()) {
            if (getFirstPass())// if it is the first time through add all of the buttons
            {
                addButtons();
                for (int index = 0; index < buttons.size(); index++) {
                    getWorld().showText("" + (index), buttons.get(index).getX(), buttons.get(index).getY() - 25);
                }
                setFirstPass(false); // so we dont do this again
                Greenfoot.getKey(); //clears the most recent key called before the buttons appeared
            }

            String keyPressed = null;
            int intPressed = -1;
            boolean keyPressedIsInt = false;
            if (!abilityInProgress())//finding the most recent key pressed and 
            {
                keyPressed = Greenfoot.getKey();
                keyPressedIsInt = Key.checkIfInt(keyPressed);//checking if key is int
            }

            if (keyPressedIsInt)//if it is int check if it is not greater than the amount of buttons
            {
                intPressed = Integer.parseInt(keyPressed);
                if (intPressed >= buttons.size()) {
                    keyPressedIsInt = false;
                }
            }
            if (keyPressedIsInt && !abilityInProgress())//final check to make sure we dont do an ability twice
            {
                buttons.get(intPressed).go(this);
                setAbilityStatus(0);
                System.out.println("caught");
            }
        }
        if (abilityComplete()) {
            for (Button button : buttons)//removing all buttons
            {
                getWorld().showText("", button.getX(), button.getY() - 25);
                button.remove();
            }
            tickStatus();
            setResolve(true);
            setFirstPass(true);
            setAbilityStatus(-1);
            //end of turn cleanup
        }
        displayHealth();
    }
}
