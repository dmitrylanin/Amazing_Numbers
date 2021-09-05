package numbers;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to Amazing Numbers!");

        Printing.printInstruction();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a request:");
        String[] strRequest = null;
        long number;

        while (true) {
            strRequest = scanner.nextLine().split(" ");
            boolean marker = true;
            long base = 0;
            long amount = 0;

            if (strRequest.length == 1){
                if (strRequest[0].equals("")) {
                    Printing.printInstruction();
                }else{
                    try {
                        number = Long.parseLong(strRequest[0]);
                        if (number == 0) {
                            System.out.println("Goodbye!");
                            break;
                        } else if (number < 0) {
                            System.out.println("The first parameter should be a natural number or zero.");
                        }else{
                            Printing.printProperties(number);
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

            if (strRequest.length == 3 && marker){
                base = Long.parseLong(strRequest[0]);
                amount = Long.parseLong(strRequest[1]);

                if ("EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, HAPPY, SAD".contains(strRequest[2].toUpperCase())) {
                    routerSolo(base, amount, strRequest[2].toUpperCase());
                    marker = false;
                } else if (strRequest[2].charAt(0) == '-' && "EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, HAPPY, SAD".contains(strRequest[2].substring(1).toUpperCase())) {
                    routerSoloExeption(base, amount, strRequest[2].substring(1).toUpperCase());
                    marker = false;
                } else {
                    System.out.println("The property [" + strRequest[2].toUpperCase() + "] is wrong.");
                    System.out.println("Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, HAPPY, SAD]");
                    marker = false;
                }
            }


            if (strRequest.length >= 4){

                String[] strArr = Arrays.copyOfRange(strRequest, 2, strRequest.length);

                //ИЩУ ОШИБОЧНЫЕ СВОЙСТВА
                String incorrectProperties = getIncorrectProperties(strArr);
                incorrectProperties = incorrectProperties.trim();
                if (incorrectProperties.length() > 0) {
                    if (!incorrectProperties.contains(" ")) {
                        System.out.println("The property [" + incorrectProperties + "] is wrong.");
                        System.out.println("Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, HAPPY, SAD]");
                        marker = false;
                    } else {
                        System.out.println("The properties [" + incorrectProperties + "] are wrong.");
                        System.out.println("Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, HAPPY, SAD]");
                        marker = false;
                    }
                }

                //ОТФИЛЬТРОВЫВАЮ ВЗАИМОИСКЛЮЧАЮЩИЕ СВОЙСТВА - число санни и НЕсанни одновременно
                if (isSelfControversialProperties(strArr) != null){
                    System.out.println(isSelfControversialProperties(strArr));
                    System.out.println("There are no numbers with these properties.");
                    marker = false;
                }

                StringBuilder strWithOutDouble = new StringBuilder(strArr[0].toUpperCase());

                //ИЩУ ДУБЛИ
                if(marker){
                    for (int j = 1; j < strArr.length; j++) {
                        if (strWithOutDouble.indexOf(strArr[j]) == -1) {
                            strWithOutDouble = strWithOutDouble.append(" " + strArr[j].toUpperCase());
                        }
                    }
                }
                String finalProperties = new String(strWithOutDouble).toUpperCase().trim();

                //Если свойства оказались дублями
                if (!finalProperties.contains(" ") && marker) {
                    if (finalProperties.charAt(0) == '-') {
                        routerSoloExeption(base, amount, finalProperties);
                        marker = false;
                    } else {
                        routerSolo(base, amount, finalProperties);
                        marker = false;
                    }
                }

                if(isControversialProperties(finalProperties) != null && marker){
                    System.out.println(isControversialProperties(finalProperties));
                    System.out.println("There are no numbers with these properties.");
                    marker = false;
                }else if (marker) {
                    routerMulty(base, amount, finalProperties);
                    marker = false;
                }
            }

            if (marker) {
                for (long i = base; i < base + amount; i++) {
                    System.out.println(Printing.makeString(i));
                }
            }

            System.out.println();
            System.out.println("Enter a request:");
        }
    }

    public static String getIncorrectProperties(String[] strArr){
        String incorrectProperties = "";
        String current;

        String [] propertiesArr = Arrays.toString(DigitTypes.values()).substring(1, Arrays.toString(DigitTypes.values()).length()-1).split(" ");
        for(int i = 0; i<propertiesArr.length; i++){
            if(propertiesArr[i].contains(",")){
                propertiesArr[i] = propertiesArr[i].substring(0, propertiesArr[i].length()-1);
            }
        }

        for (int i = 0; i<strArr.length; i++){
            if(strArr[i].charAt(0) == '-'){
                current = strArr[i].substring(1).toUpperCase();
            }else{
                current = strArr[i].toUpperCase();
            }

            if(!Arrays.asList(propertiesArr).contains(current)){
                incorrectProperties += strArr[i] + " ";
            }
        }

        return incorrectProperties;
    }

    public static void routerSolo(long number, long amount, String type) {
        int count = 0;
        for (long i = number; count < amount; i++) {
            if (DigitTypes.valueOf(type).isType(i)) {
                count++;
                System.out.println(Printing.makeString(i));
            }
        }
    }

    public static void routerSoloExeption(long number, long amount, String type) {
        int count = 0;
        for (long i = number; count < amount; i++) {
            if (!DigitTypes.valueOf(type).isType(i)) {
                count++;
                System.out.println(Printing.makeString(i));
            }
        }
    }

    public static void routerMulty(long number, long amount, String str){
        int count = 0;
        String[] strArr = str.split(" ");
        boolean marker = false;

        for (long i = number; count < amount; i++){
            for (int j = 0; j < strArr.length; j++){
                if (strArr[j].charAt(0) == '-'){
                    if (!DigitTypes.valueOf(strArr[j].substring(1)).isType(i)) {
                        marker = true;
                    }else{
                        marker = false;
                        break;
                    }
                }else if (DigitTypes.valueOf(strArr[j]).isType(i)) {
                    marker = true;
                }else{
                    marker = false;
                    break;
                }
            }
            if (marker){
                count++;
                System.out.println(Printing.makeString(i));
                marker = false;
            }
        }
    }

    //ОТФИЛЬТРОВЫВАЮ ВЗАИМОИСКЛЮЧАЮЩИЕ ТРЕБОВАНИЯ - ЧИСЛО ОДНОВРЕМЕННО ЧЕТНОЕ И НЕЧЕТНОЕ
    public static String isControversialProperties(String finalProperies){
        String[][] controversialProperties = {{"DUCK", "SPY"}, {"SUNNY", "SQUARE"}, {"HAPPY", "SAD"}};
        String [] finalProperiesArr = finalProperies.split(" ");

        if (finalProperies.toUpperCase().contains("EVEN") && finalProperies.toUpperCase().contains("ODD")){
            return String.format("The request contains mutually exclusive properties: [%s, %s]", "ODD", "EVEN");
        }else if(finalProperies.toUpperCase().contains("-EVEN") && finalProperies.toUpperCase().contains("-ODD")){
            return String.format("The request contains mutually exclusive properties: [%s, %s]", "-ODD", "-EVEN");
        }else if(finalProperies.toUpperCase().contains("ODD") && finalProperies.toUpperCase().contains("-ODD")){
            return String.format("The request contains mutually exclusive properties: [%s, %s]", "ODD", "-ODD");
        }else if(finalProperies.toUpperCase().contains("EVEN") && finalProperies.toUpperCase().contains("-EVEN")){
            return String.format("The request contains mutually exclusive properties: [%s, %s]", "EVEN", "-EVEN");
        }

        for(int i = 0; i<controversialProperties.length; i++){
            if (Arrays.asList(finalProperiesArr).contains(controversialProperties[i][0]) && Arrays.asList(finalProperiesArr).contains(controversialProperties[i][1])){
                return String.format("The request contains mutually exclusive properties: [%s, %s]", controversialProperties[i][0], controversialProperties[i][1]);
            }
        }
        return null;
    }

    public static String isSelfControversialProperties(String [] strArr){
        String [] dTypes = Arrays.toString(DigitTypes.values()).substring(1,(Arrays.toString(DigitTypes.values()).length()-2)).split(" ");
        for(int j = 0; j<dTypes.length; j++){
            if(dTypes[j].charAt(dTypes[j].length()-1) == ','){
                dTypes[j] = dTypes[j].substring(0, dTypes[j].length()-1);
            }
        }

        for (int k = 0; k<strArr.length; k++){
            strArr[k] = strArr[k].toUpperCase();
        }

        for (int i = 0; i< dTypes.length; i++){
            if(Arrays.asList(strArr).contains(dTypes[i]) && Arrays.asList(strArr).contains("-"+dTypes[i])){
                return String.format("The request contains mutually exclusive properties: [%s, %s]", dTypes[i], "-"+dTypes[i]);
            }
        }
        return null;
    }
}