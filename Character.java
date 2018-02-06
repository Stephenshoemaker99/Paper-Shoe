import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class Character here.
 * 
 * @Stephen Shoemaker
 * @2/5/18
 */
public abstract class Character extends Actor implements Order {
    private String name;
    private Health health;
    private int dexterity;
    private boolean alliance;
    private boolean resolved;
    private BattleOrder order;
    private boolean inTurn;
    private Battlefield world;
    private String abilityStatusString;
    public List<Status> statuses = new ArrayList<Status>();

    /* Constructor to create a character
    initialName can be any string
    initialHealth must be Health class, should not have 0 for either current or max Health
    InitialDexterity can be any int
    initialWorld is just a Battlefield Class
    */
    public Character(String initialName, Health initialHealth, int initialDexterity, Battlefield initialWorld) {
        name = initialName;
        health = initialHealth;
        dexterity = initialDexterity;
        resolved = true;
        world = initialWorld;
        abilityStatusString = "before";
    }
    /*changes the alliance to any alliance
    can be true or false */
    public void setAlliance(boolean newAlliance) {
        alliance = newAlliance;
    }//unused but may be expanded
    //returns dexterity value
    public int getDexterity() {
        return dexterity;
    }
    //returns current health object
    public Health getHealth() {
        return health;
    }
    //returns current alliance
    public boolean getAlliance() {
        return alliance;
    }
    //returns name
    public String getName() {
        return name;
    }
    //returns current state of wether it is resolved or not
    public boolean getResolved() {
        return resolved;
    }
    //parmaater can be ably boolean
    //sets the resolved status to the paramater
    public void setResolve(boolean bool) {
        resolved = bool;
    }
    //paramater is any battleorder
    //sets the saved order as that battle order
    public void setOrder(BattleOrder initialOrder) {
        order = initialOrder;
    }
    //no paramater
    //returns the ordercurrently used
    public BattleOrder getOrder() {
        return order;
    }
    //returns the Battlefield that the character is in
    public Battlefield getWorld() {
        return world;
    }
    //returns the current ability status, Where the character is in its turn
    private String getAbilityStatusString() {
        return abilityStatusString;
    }

    /*using a three way switch and decided the best way to
    do that was with strings for each of the three states instead of
    ints that do not describe the status*/


    // returns true if the character is complete with its ability and false otherwise
    public boolean abilityComplete() {
        if (getAbilityStatusString() == "complete") {
            return true;
        } else {
            return false;
        }
    }
    //returns true if the ability is in progress and false otherwise
    public boolean abilityInProgress() {
        if (getAbilityStatusString() == "inProgress") {
            return true;
        } else {
            return false;
        }
    }
    //returns true if it is before the ability and false otherwise
    public boolean abilityNotInProgress() {
        if (getAbilityStatusString() == "before") {
            return true;
        } else {
            return false;
        }
    }
    //takes an int as a paramater to set the status to either before inprogress or complete
    //does nothing if it is not -1, 0, or 1
    public void setAbilityStatus(int status) {
        if (status == -1) {
            abilityStatusString = "before";
        } else {
            if (status == 0) {
                abilityStatusString = "inProgress";
            } else {
                if (status == 1) {
                    abilityStatusString = "complete";
                }
            }
        }
    }
    //takes a string as paramter
    //sets the status to either inProgress,before, or complete
    //will not set abilityStatusString to anything else
    public void setAbilityStatus(String status) {
        if (status == "inProgress" || status == "before" || status == "complete") {
            abilityStatusString = status;
        }
    }
    //no input and sets the player to do his action when it is time
    public void go()
    {
        System.out.println(getName() + " is going");
        setResolve(false);
    }//tells to begin acting
    
    //takes a status as the paramater and has it add to the status array
    public void addStatus(Status initialStatus) {
        statuses.add(initialStatus);
    }
    //takes a status name in and if it present returns true, if not returns false
    public boolean findStatus(String statusName) {
        for (Status status : statuses) {
            if (status.getName().equals(statusName)) {
                return true;
            }
        }
        return false;
    }//sees if the status is present by checking the strings
    //searches the status list for any status that shares the same name as the paramater then removes it from the list
    public void removeStatus(String statusName) {
        for (int index = 0; index < statuses.size(); index++) {
            if (statuses.get(index).getName().equals(statusName)) {
                statuses.remove(index);
            }
        }
    }
    //returns the status that shares then name of the status
    public Status getStatus(String statusName) {
        for (Status status : statuses) {
            if (status.getName().equals(statusName)) {
                return status;
            }
        }
        return null;
    }//returns the status if it is there
    //no input and displays the health below the character
    public void displayHealth() {
        world.showText("Health\n" + getHealth().getCurrent() + "/" + getHealth().getMax(), getX(), getY() + 50);
    }
    //ticks down all the statuses in the status array and delets any that reaches 0
    public void tickStatus() {
        for (int index = 0; index < statuses.size(); index++) {
            if (statuses.get(index).getDuration() <= 0) {
                statuses.get(index).remove();
                statuses.remove(index);
                index--;
            } else {
                statuses.get(index).tick();
            }
        }
    }//checks and removes any unneded statuses

    public void act() {

    }
}
