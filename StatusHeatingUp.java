import greenfoot.*;

public class StatusHeatingUp extends Status {

    public StatusHeatingUp(Character initialAffCharacter) {
        super("Heating Up", 3, initialAffCharacter);

    }

    public int tickUp()//basically I wanted heating up to be a multi stack status that increases the damage of the fire attacks
    {
        setStacks(getStacks() + 1);
        setDuration(3);
        updateImage();
        return getDuration();
    }

    public int tick()//if the duration goes below 3 remove a stack than try again
    {
        int currentDuration = getDuration();
        currentDuration--;
        if (currentDuration <= 0 && getStacks() > 1) {
            setStacks(getStacks() - 1);
            setDuration(1);
        }
        setDuration(currentDuration);
        updateImage();
        return getDuration();
    }

}