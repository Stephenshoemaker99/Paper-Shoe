import greenfoot.*;

public class Projectile extends Ability {
    private Character target;
    private int speed;
    private Character producer;
    private Battlefield world;
    private int damage;
    private boolean hasTarget;
    private boolean findingTarget;
    private String keyPressed = null;

    public Projectile(Character initialProducer, Character initialTarget, Battlefield initialWorld) {

        super();
        target = initialTarget;
        producer = initialProducer;
        world = initialWorld;
        speed = 10;
        hasTarget = true;
        findingTarget = false;
        activate();

    }

    public Projectile(Character initialProducer, Battlefield initialWorld) {
        super();
        producer = initialProducer;
        world = initialWorld;
        speed = 10;
        hasTarget = false;
        findingTarget = false;
        activate();//need function to determine target if it is not given
    }

    public void activate() {
        world.addObject(this, producer.getX(), producer.getY());
        world.repaint();

    }

    public Character getTarget() {
        return target;
    }

    public Character getProducer() {
        return producer;
    }

    public Battlefield getWorld() {
        return world;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int newDamage) {
        damage = newDamage;
    }

    public void travel() {

    }

    public void contact() {
        System.out.println("contact");
        target.getHealth().removeCurrent(damage);
        target.displayHealth();
        producer.setAbilityStatus(1);
        getWorld().removeObject(this);
    }

    public void act() {
        String keyPressed = Greenfoot.getKey();//similar to players check
        int intPressed = -1;
        boolean keyPressedIsInt = Key.checkIfInt(keyPressed);

        if (!hasTarget)//of there is no target yet make sure no target is accidentally selected
        {
            target = null;
            System.out.println(keyPressed);
        }
        // i know this should probably be in if statement but im trying to do whatever works here
        if (hasTarget) {
            turnTowards(target.getX(), target.getY());
            move(speed);
            System.out.println("Flying");
            //has the projectile fly towards target 
            if (intersects(target)) {
                // contact has been made
                contact();
            }
        } else {
            System.out.println("finding target");
            BattleOrder order = producer.getOrder();//did this so i dont have to repeat producer,getOrder repeatedly
            if (!findingTarget) {

                int ctr = 0;
                for (int index = order.size() - 1; index >= 0; index--)//loop used to create the target numbers above each enemy
                {
                    if (order.get(index).getAlliance() != producer.getAlliance())//goes through the order and puts a number above each character that is an enemy
                    {
                        order.get(index).getWorld().showText("Tar\n   " + ctr, order.get(index).getX(),
                                order.get(index).getY() - 150);
                        ctr++;
                    }
                }
                findingTarget = true;//now we can choose our target beacue we know the enemies numbers
            }
            Character potentialTarget = null;
            if (keyPressedIsInt)//if it is an int we can know use it to chhoose a target
            {
                intPressed = Integer.parseInt(keyPressed);//saves the int as an int object instead of a string

                if (intPressed <= producer.getOrder().allianceCount(!producer.getAlliance())) {
                    potentialTarget = producer.getOrder().getCharacter(!producer.getAlliance(),
                            producer.getOrder().allianceCount(!producer.getAlliance()) - intPressed - 1);//sets the target as the opposite alliance and the int pressed
                }

                if (potentialTarget != null)//if there is a potential target make it the actual target and send out the projectile
                {
                    target = potentialTarget;
                    hasTarget = true;
                }
            }
        }

    }
}
