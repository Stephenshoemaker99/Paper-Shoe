public class Health {

    private double[] healthArray = new double[2];
    public Health() {
        healthArray[0] = 0;
        healthArray[1] = 0;
    }//zero-arg should probably never be called
     //just basic getters and setters nothing special

    public Health(int max) {
        healthArray[0] = max;
        healthArray[1] = max;
    }

    public double getCurrent() {
        return healthArray[0];
    }

    public double getMax() {
        return healthArray[1];
    }

    public void setCurrent(double newCurrent) {
        healthArray[0] = newCurrent;
    }

    public void setMax(int newMax) {
        healthArray[1] = newMax;
    }

    public void removeCurrent(double remove) {
        healthArray[0] -= remove;
    }

    public void removeMax(int remove) {
        healthArray[1] -= remove;
    }

    public void maxPercentToCurrent(double percent) {
        healthArray[0] -= (percent / 100) * healthArray[1];
    }

    public void currentPercentToCurrent(double percent) {
        healthArray[0] -= (percent / 100) * healthArray[0];
    }

    public String toString() {
        String output = new String("Max Health: " + healthArray[1] + "\n" + "Current Health: " + healthArray[0]);
        return output;
    }
}