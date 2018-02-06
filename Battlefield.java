import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class Battlefield here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Battlefield extends World implements Order {
    private BattleOrder order;

    /**
     * Constructor for objects of class Battlefield.
     * 
     */
    public BattleOrder getOrder() {
        return order;
    }

    public Battlefield() {
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 500, 1);
        int allianceCount = 0;
        int enemyCount = 0;

        List<Character> combatants = new ArrayList<Character>();//adding characters
        combatants.add(new Player(new Health(20), 1, this));
        combatants.add(new Player(new Health(20), 3, this));
        combatants.add(new Player(new Health(20), 5, this));
        combatants.add(new Player(new Health(20), 7, this));
        combatants.add(new Enemy("Barbarian", new Health(20), 2, this));
        combatants.add(new Enemy("Barbarian", new Health(20), 4, this));
        combatants.add(new Enemy("Barbarian", new Health(20), 6, this));
        combatants.add(new Enemy("Barbarian", new Health(20), 8, this));
        int indices = 0;
        while(indices < combatants.size())
        {
            if (combatants.get(indices).getAlliance() == true) {
                addObject(combatants.get(indices), 400 - 80 * allianceCount, 300);
                allianceCount++;
            } else {
                addObject(combatants.get(indices), 800 - 80 * enemyCount, 300);
                enemyCount++;
            }
            indices++;
        } // adding characters to world

        order = new BattleOrder(combatants, this);
        for (int index = 0; index < order.getList().size(); index++) {
            System.out.println(order.getList().get(index).getName());
        } //adding characters to order which keeps track of who goes first

    }

    public void act() {
        order.act();
    }

    public String toString() {
        return null;
    }
}
