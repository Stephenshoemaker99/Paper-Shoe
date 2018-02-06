import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ButtonFireball here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ButtonFireball extends Button {
    public ButtonFireball() {
        super();
    }

    public boolean isPressed() {
        return Greenfoot.mousePressed(this);
    }

    public void go(Character producer, Character target) {
        System.out.println("Shooting Balls");
        Fireball fireball = new Fireball(producer, target, producer.getWorld());
    }

    public void go(Character producer) {
        System.out.println("Shooting Balls");
        Fireball fireball = new Fireball(producer, producer.getWorld());
    }

}
