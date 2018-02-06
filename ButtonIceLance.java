import greenfoot.*;

public class ButtonIceLance extends Button {
    public ButtonIceLance() {
        super();
    }

    public void go(Character producer, Battlefield world) {
        System.out.println("freeeze time");
        IceLance icelance = new IceLance(producer, world);
    }

    public void go(Character producer, Character target) {
        go(producer);
    }

    public void go(Character producer) {
        go(producer, producer.getWorld());
    }
}