package numbers;

import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        System.out.println("Welcome to Amazing Numbers!");

        printInstruction();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a request:");
        String [] strRequest = null;
        long number;

        while (true){
            strRequest = scanner.nextLine().split(" ");
            boolean marker = true;
            long base = 0;
            long amount = 0;

            if (strRequest.length == 1){
                if(strRequest[0].equals("")){
                    printInstruction();
                }else {
                    try {
                        number = Long.parseLong(strRequest[0]);
                        if (number == 0) {
                            System.out.println("Goodbye!");
                            break;
                        } else if (number < 0) {
                            System.out.println("The first parameter should be a natural number or zero.");
                        } else {
                            printProperties(number);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("The first parameter should be a natural number or zero.");
                    }
                }
            }else{
                base = Long.parseLong(strRequest[0]);
                amount = Long.parseLong(strRequest[1]);
                if (base < 0) {
                    System.out.println("The first parameter should be a natural number or zero.");
                    marker = false;
                }

                if (amount <= 0) {
                    System.out.println("The second parameter should be a natural number.");
                    marker = false;
                }
            }

            if(strRequest.length == 3 && marker){
                base = Long.parseLong(strRequest[0]);
                amount = Long.parseLong(strRequest[1]);

                if("EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING".contains(strRequest[2].toUpperCase())){
                    routerSolo(base, amount, strRequest[2].toUpperCase());
                    marker = false;
                }else{
                    System.out.println("The property [" + strRequest[2].toUpperCase() + "] is wrong.");
                    System.out.println("Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING]");
                    marker = false;
                }
            }


            if(strRequest.length >= 4){
                String [] strArr = Arrays.copyOfRange(strRequest, 2, strRequest.length);

                //ИЩУ ОШИБОЧНЫЕ СВОЙСТВА
                String incorrectProperties = "";

                for(int j = 0; j < strArr.length; j++){
                    if (!"EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING".contains(strArr[j].toUpperCase())){
                        incorrectProperties += strArr[j] + " ";
                    }
                }

                incorrectProperties = incorrectProperties.trim();
                if (incorrectProperties.length()>0){
                    if(!incorrectProperties.contains(" ")){
                        System.out.println("The property [" + incorrectProperties + "] is wrong.");
                        System.out.println("Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING]");
                        marker = false;
                    }else{
                        System.out.println("The properties [" + incorrectProperties + "] are wrong.");
                        System.out.println("Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING]");
                        marker = false;
                    }
                }

                //ИЩУ ДУБЛИ
                StringBuilder strWithOutDouble = new StringBuilder(strArr[0].toUpperCase());
                for(int j = 1; j < strArr.length; j++){
                    if(strWithOutDouble.indexOf(strArr[j]) == -1){
                        strWithOutDouble = strWithOutDouble.append(" "+strArr[j].toUpperCase());
                    }
                }
                String finalProperties = new String(strWithOutDouble).toUpperCase().trim();

                //Если свойства оказались дублями
                if (!finalProperties.contains(" ")){
                    routerSolo(base, amount, finalProperties);
                    marker = false;
                }

                //ПРОВЕРКА, что нет взаимоисключающих свойств
                if(finalProperties.contains("EVEN") && finalProperties.contains("ODD")){
                    System.out.println("The request contains mutually exclusive properties: [EVEN, ODD]");
                    System.out.println("There are no numbers with these properties.");
                    marker = false;
                }else if(finalProperties.contains("DUCK") && finalProperties.contains("SPY")){
                    System.out.println("The request contains mutually exclusive properties: [DUCK, SPY]");
                    System.out.println("There are no numbers with these properties.");
                    marker = false;
                }else if(finalProperties.contains("SUNNY") && finalProperties.contains("SQUARE")) {
                    System.out.println("The request contains mutually exclusive properties: [SUNNY, SQUARE]");
                    System.out.println("There are no numbers with these properties.");
                    marker = false;
                }else if (marker){
                    routerMulty(base, amount, finalProperties);
                    marker = false;
                }
            }

            if(marker){
                for(long i = base; i<base+amount; i++){
                    System.out.println(makeString(i));
                }
            }

            System.out.println();
            System.out.println("Enter a request:");
        }
    }

    public static String makeString(Long number){
        String str = number + " is ";

        if (DigitTypes.PALINDROMIC.isType(number)){
            str +="palindromic, ";
        }

        if (DigitTypes.SPY.isType(number)){
            str +="spy, ";
        }

        if (DigitTypes.BUZZ.isType(number)){
            str +="buzz, ";
        }

        if (DigitTypes.DUCK.isType(number)){
            str +="duck, ";
        }

        if (DigitTypes.GAPFUL.isType(number)){
            str +="gapful, ";
        }

        if (DigitTypes.JUMPING.isType(number)){
            str +="jumping, ";
        }

        if (DigitTypes.SUNNY.isType(number)){
            str +="sunny, ";
        }

        if (DigitTypes.SQUARE.isType(number)){
            str +="square, ";
        }

        if (DigitTypes.ODD.isType(number)){
            str +="odd";
        }else{
            str +="even";
        }

        str = str.trim();
        if(str.toCharArray()[str.toCharArray().length-1] == ','){
            str = str.substring(0, str.length()-1);
        }

        return str;
    }

    public static void printInstruction(){
        System.out.println();
        System.out.println("Supported requests:");
        System.out.println("- enter a natural number to know its properties;");
        System.out.println("- enter two natural numbers to obtain the properties of the list:");
        System.out.println("  * the first parameter represents a starting number;");
        System.out.println(" * the second parameter shows how many consecutive numbers are to be printed;");
        System.out.println("- two natural numbers and a property to search for;");
        System.out.println("- two natural numbers and two properties to search for;");
        System.out.println("- separate the parameters with one space;");
        System.out.println("- enter 0 to exit.");
    }

    public static void printProperties(Long number){
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
    }

    public static void routerSolo(long number, long amount, String type){
        int count = 0;
        for (long i = number; count<amount; i++){
            if(DigitTypes.valueOf(type).isType(i)){
                count++;
                System.out.println(makeString(i));
            }
        }
    }

    public static void routerMulty(long number, long amount, String str){
        int count = 0;
        String [] strArr = str.split(" ");
        boolean marker = false;

        for (long i = number; count<amount; i++){
            for (int j = 0; j<strArr.length; j++){
                if(DigitTypes.valueOf(strArr[j]).isType(i)){
                    marker = true;
                }else{
                    marker = false;
                    break;
                }
            }
            if(marker){
                count++;
                System.out.println(makeString(i));
                marker = false;
            }
        }
    }
}