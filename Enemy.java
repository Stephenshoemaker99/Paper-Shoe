import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Character {
    public Enemy(String initialName, Health initialHealth, int initialDexterity, Battlefield initialWorld) {
        super(initialName, initialHealth, initialDexterity, initialWorld);
        setAlliance(false);
    }

    public void act() {
        displayHealth();
        if (!getResolved())//mostly for testing if the resolve boolean is working unneeeded now
        {
            System.out.println("its my turn squares");
        }
        if (!getResolved() && abilityNotInProgress()) {
            new Fireball(this, getWorld().getOrder().getCharacter(true), getWorld());
            setAbilityStatus(0);

        }
        if (abilityComplete()) {
            setResolve(true);
            setAbilityStatus(-1);
            tickStatus();
            //doing all end of turn acitvities to prepare the next character
        }
    }
}
