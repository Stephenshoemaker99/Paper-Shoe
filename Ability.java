import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public abstract class Ability extends Actor {
    // was planning to add more abilities and more types of abilities, still works as intended

    private String description;

    public Ability() {
        description = null;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String newDescription) {
        description = newDescription;
    }
}
