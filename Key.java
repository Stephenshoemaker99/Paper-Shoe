public class Key
// this class only exists so that i can check if a key from key pressed is a valid int before attempting to parse it with the Integer class
{
    public static boolean checkIfInt(String input) {
        if (input == null) {
            return false;
        }
        if (input.equals("0") || input.equals("1") || input.equals("2") || input.equals("3") || input.equals("4")
                || input.equals("5") || input.equals("6") || input.equals("7") || input.equals("8")
                || input.equals("9")) {
            return true;
        } else {
            return false;
        }
    }
}
