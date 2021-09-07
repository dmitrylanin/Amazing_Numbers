package numbers;

public class Printing {
/*
    В этом классе формирую инструкцию и механику вывода строк со свойствами "одиночных" чисел
 */


    public static void printInstruction() {
        System.out.println();
        System.out.println("Supported requests:");
        System.out.println("- enter a natural number to know its properties;");
        System.out.println("- enter two natural numbers to obtain the properties of the list:");
        System.out.println(" * the first parameter represents a starting number;");
        System.out.println(" * the second parameter shows how many consecutive numbers are to be printed;");
        System.out.println("- two natural numbers and properties to search for;");
        System.out.println("- a property preceded by minus must not be present in numbers;");
        System.out.println("- separate the parameters with one space;");
        System.out.println("- enter 0 to exit.");
    }

    public static void printProperties(Long number) {
        System.out.println("Properties of " + number);
        System.out.println("buzz: " + DigitTypes.BUZZ.isType(number));
        System.out.println("duck: " + DigitTypes.DUCK.isType(number));
        System.out.println("palindromic: " + DigitTypes.PALINDROMIC.isType(number));
        System.out.println("gapful: " + DigitTypes.GAPFUL.isType(number));
        System.out.println("spy: " + DigitTypes.SPY.isType(number));
        System.out.println("square: " + DigitTypes.SQUARE.isType(number));
        System.out.println("sunny: " + DigitTypes.SUNNY.isType(number));
        System.out.println("jumping: " + DigitTypes.JUMPING.isType(number));
        System.out.println("even: " + DigitTypes.EVEN.isType(number));
        System.out.println("odd: " + DigitTypes.ODD.isType(number));
        System.out.println("happy: " + DigitTypes.HAPPY.isType(number));
        System.out.println("sad: " + DigitTypes.SAD.isType(number));
    }

    public static String makeString(Long number) {
        String str = number + " is ";

        if (DigitTypes.PALINDROMIC.isType(number)) {
            str += "palindromic, ";
        }

        if (DigitTypes.SPY.isType(number)) {
            str += "spy, ";
        }

        if (DigitTypes.BUZZ.isType(number)) {
            str += "buzz, ";
        }

        if (DigitTypes.DUCK.isType(number)) {
            str += "duck, ";
        }

        if (DigitTypes.GAPFUL.isType(number)) {
            str += "gapful, ";
        }

        if (DigitTypes.JUMPING.isType(number)) {
            str += "jumping, ";
        }

        if (DigitTypes.HAPPY.isType(number)) {
            str += "happy, ";
        }

        if (DigitTypes.SAD.isType(number)) {
            str += "sad, ";
        }

        if (DigitTypes.SUNNY.isType(number)) {
            str += "sunny, ";
        }

        if (DigitTypes.SQUARE.isType(number)) {
            str += "square, ";
        }

        if (DigitTypes.ODD.isType(number)) {
            str += "odd";
        } else {
            str += "even";
        }

        str = str.trim();
        if (str.toCharArray()[str.toCharArray().length - 1] == ',') {
            str = str.substring(0, str.length() - 1);
        }

        return str;
    }

}
