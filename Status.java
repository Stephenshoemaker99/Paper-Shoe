import greenfoot.*;

public abstract class Status extends Actor // abstract because it should never be created and want to make that clear
{
    private String name;
    private int duration;
    private Character affCharacter;
    private int stacks;

    public Status(String initialName, int initialDuration, Character initialAffCharacter) {
        name = initialName;
        duration = initialDuration;
        affCharacter = initialAffCharacter;
        initialAffCharacter.getWorld().addObject(this, initialAffCharacter.getX(), initialAffCharacter.getY() - 75);
        getWorld().showText("" + duration, getX(), getY() - 10);
        stacks = 1;
    }

    public int getDuration() {
        return duration;
    }

    public int getStacks() {
        return stacks;
    }

    public void setStacks(int newStacks) {
        stacks = newStacks;
    }

    public void removeStacks(int stacksRemoved) {
        stacks -= stacksRemoved;
    }

    public int tick() {
        duration--;
        if (duration == 0) {
            remove();
        }
        updateImage();
        return duration;
    }

    public int tickUp() {
        duration++;
        updateImage();
        return duration;

    }

    public void setDuration(int newDuration) {
        duration = newDuration;
        updateImage();
    }

    public void updateImage() {
        getWorld().showText("" + duration, getX(), getY() - 10);
    }

    public String getName() {
        return name;
    }

    public Character getAffCharacter() {
        return affCharacter;
    }

    public void addToCharacter() {
        affCharacter.addStatus(this);
    }

    public void remove() {
        getWorld().showText("", getX(), getY() - 10);
        getWorld().removeObject(this);
    }

}