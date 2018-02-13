import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Button here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Button extends Actor {
    private Ability ability;
    private GreenfootImage image;
    private boolean isPressed;

    public Button(Ability initialAbility) {
        ability = initialAbility;
        image = ability.getImage();

    }

    public Button() {

    }

    public boolean isPressed() {
        return Greenfoot.mouseClicked(this);
    }//unneded because clicking was glitchier then expected

    public void remove() {
        getWorld().removeObject(this);
    }

    public abstract void go(Character producer);

    public abstract void go(Character producer, Character target);

    public void act() {
    }
}
