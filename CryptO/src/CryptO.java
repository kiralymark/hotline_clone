import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class CryptO {
    static char[] validChars = new char[] { 'c', '0', '1', '2' };
    static char[] inputChars;
    static List<Character> inputList = new LinkedList<>();
    static List<Character> outputList = new LinkedList<>();
    static List<Character> finalList = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        String inputString;

        try (Scanner sc = new Scanner(System.in)) {
            inputString = sc.next();
            //System.out.println(inputString);
        }

        inputChars = new char[inputString.length()];
        inputString.getChars(0, inputString.length(), inputChars, 0); // inputString-et beletöltöm
        //System.out.println(inputString + " \nend\n");

        if (validatedChars()) {
            for (char character : inputChars) {
                inputList.add(character);
            }
            translateInputList();
        } else {
            System.out.println("Error");
        }
    }

    // character = 'c', charArray = validChars
    static boolean contains(char c, char[] charArray) {
        for (int i = 0; i < charArray.length; i++) {
            if (c == charArray[i]) { // { 'c', OR '0', OR '1', OR '2' };
                return true; // A lényeg, hogy benne van-e
            }
        }

        return false;
    }

    static boolean validatedChars() {
        for (char kar : inputChars) {
            if (!contains(kar, validChars)) {
                return false; // 'c' and '0' and '1' and '2'
                              // mindegyiknek érvényesnek kell lennie
            }
        }

        return true;
    }

    /*
     * Mit csinál a validateChars:
     * beadok 'c31'-et
     * elindul c-vel a contains() megnézi, egyenlő a validChars[0]-val (c-vel) igen
     * -> igaz
     * jön a 3 a contains() megnézi, egyenlő a validChars[0]-val (c-vel): nem
     * egyenlő a validChars[1]-el (0-val): nem
     * egyenlő a validChars[2]-vel (1-vel): nem
     * -> hamis
     * mivel hamis, ezért a validateChars hamisat ad vissza
     */

    static void translateInputList() {
        int i = 0;
        while (inputList.size() > 0 && i < 40) { // 012012012012012012012012
            char currentChar = inputList.get(0); // 'cc'
            inputList.remove(0);

            switch (currentChar) { //
                case 'c': // if(currentChar=='c')
                    outputList.addAll(inputList);
                    break;
                case 'f': // else if(currentChar=='f')
                    break;
                default: // else
                    finalList.add(currentChar);
                    break;
            }

            outputList.addAll(inputList);
            inputList.clear();
            inputList.addAll(outputList);

            System.out.println(outputList);
            outputList.clear();

            i++;
        }

        System.out.println("\n" + finalList);
    }
}
