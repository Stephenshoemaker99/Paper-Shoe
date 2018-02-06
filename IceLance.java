import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Fireball here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class IceLance extends Projectile {
    public IceLance(Character initialProducer, Character initialTarget, Battlefield initialWorld) {

        super(initialProducer, initialTarget, initialWorld);
        super.setDescription("Ice Lance shot at " + initialTarget.getName() + " for " + getDamage() + " Damage!");
        setDamage((int) Math.floor(Math.random() * 13));
        initialTarget.getHealth().removeCurrent(getDamage());

        activate();

    }

    public IceLance(Character initialProducer, Battlefield initialWorld) {
        super(initialProducer, initialWorld);
        setDamage((int) Math.floor(Math.random() * 13));
    }

    public void activate() {
        getWorld().addObject(this, getProducer().getX(), getProducer().getY());
        getWorld().repaint();
    }

    public void contact() {
        super.contact();
        getTarget().removeStatus("Heating Up");

    }
}
