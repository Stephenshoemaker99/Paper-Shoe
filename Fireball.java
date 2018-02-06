import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Fireball here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Fireball extends Projectile {
    public Fireball(Character initialProducer, Character initialTarget, Battlefield initialWorld) {

        super(initialProducer, initialTarget, initialWorld);
        int stackDamage = 0;
        if (getProducer().getStatus("Heating Up") != null) {
            stackDamage = getProducer().getStatus("Heating Up").getStacks() * 2;
        }
        setDamage(((int) Math.floor(Math.random() * 11)) + stackDamage);
        if (initialProducer.findStatus("Heating Up") == false) {
            initialProducer.addStatus(new StatusHeatingUp(initialProducer));
        } else {
            initialProducer.getStatus("Heating Up").tickUp();
        }
        activate();

    }

    public Fireball(Character initialProducer, Battlefield initialWorld)// adds stacks of heating up
    {
        super(initialProducer, initialWorld);
        setDamage((int) Math.floor(Math.random() * 11));
        if (initialProducer.findStatus("Heating Up") == false) {
            initialProducer.addStatus(new StatusHeatingUp(initialProducer));
        } else {
            initialProducer.getStatus("Heating Up").tickUp();
        }
        activate();
    }

    public void activate() {
        getWorld().addObject(this, getProducer().getX(), getProducer().getY());
        getWorld().repaint();//need to repaint in order to display the object
    }
}
