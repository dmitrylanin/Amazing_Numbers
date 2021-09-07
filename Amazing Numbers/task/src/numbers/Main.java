package numbers;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static String strDTypes = Arrays.toString(DigitTypes.values()).substring(1,(Arrays.toString(DigitTypes.values()).length()-2));

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

            //СЛУЧАЙ: стартовое число, количество чисел, нужное свойство
            if (strRequest.length == 3 && marker){
                base = Long.parseLong(strRequest[0]);
                amount = Long.parseLong(strRequest[1]);

                if (strDTypes.contains(strRequest[2].toUpperCase())) {
                    NumbersRouting.routerSolo(base, amount, strRequest[2].toUpperCase());
                    marker = false;
                }else if (strRequest[2].charAt(0) == '-' && strDTypes.contains(strRequest[2].substring(1).toUpperCase())) {
                    NumbersRouting.routerSolo(base, amount, strRequest[2].substring(1).toUpperCase());
                    marker = false;
                }else {
                    System.out.println("The property [" + strRequest[2].toUpperCase() + "] is wrong.");
                    System.out.println("Available properties: [" + strDTypes + "]");
                    marker = false;
                }
            }


            //СЛУЧАЙ: стартовое число, количество чисел, нужное свойство1, нужное свойство2... n
            if (strRequest.length >= 4){
                String[] propertiesArr = Arrays.copyOfRange(strRequest, 2, strRequest.length);

                //ИЩУ ОШИБОЧНЫЕ СВОЙСТВА
                CheckStatus incorrectProperties = CheckingProperties.getIncorrectProperties(propertiesArr);
                if (incorrectProperties.getStatus()) {
                    if (!incorrectProperties.getStr().trim().contains(" ")) {
                        System.out.println("The property [" + incorrectProperties + "] is wrong.");
                        System.out.println("Available properties: [" + strDTypes + "]");
                        marker = false;
                    } else {
                        System.out.println("The properties [" + incorrectProperties.getStr().trim() + "] are wrong.");
                        System.out.println("Available properties: [" + strDTypes + "]");
                        marker = false;
                    }
                }


                //ОТФИЛЬТРОВЫВАЮ ВЗАИМОИСКЛЮЧАЮЩИЕ СВОЙСТВА - число санни и НЕсанни одновременно
                CheckStatus selfControversialProperties = CheckingProperties.isSelfControversialProperties(propertiesArr);

                if (selfControversialProperties.getStatus()){
                    System.out.println(selfControversialProperties.getStr());
                    System.out.println("There are no numbers with these properties.");
                    marker = false;
                }

                StringBuilder strWithOutDouble = new StringBuilder(propertiesArr[0].toUpperCase());

                //ИЩУ ДУБЛИ
                if(marker){
                    for (int j = 1; j < propertiesArr.length; j++) {
                        if (strWithOutDouble.indexOf(propertiesArr[j]) == -1) {
                            strWithOutDouble = strWithOutDouble.append(" " + propertiesArr[j].toUpperCase());
                        }
                    }
                }
                String finalProperties = new String(strWithOutDouble).toUpperCase().trim();

                //Если свойства оказались дублями
                if (!finalProperties.contains(" ") && marker) {
                    if (finalProperties.charAt(0) == '-') {
                        NumbersRouting.routerSolo(base, amount, finalProperties);
                        marker = false;
                    } else {
                        NumbersRouting.routerSolo(base, amount, finalProperties);
                        marker = false;
                    }
                }


                //ОТФИЛЬТРОВЫВАЮ ВЗАИМОИСКЛЮЧАЮЩИЕ ТРЕБОВАНИЯ - ЧИСЛО ОДНОВРЕМЕННО ЧЕТНОЕ И НЕЧЕТНОЕ
                CheckStatus controversialPropertiesStatus = CheckingProperties.isControversialProperties(finalProperties);
                if(!controversialPropertiesStatus.getStatus() && marker){
                    System.out.println(controversialPropertiesStatus.getStr());
                    System.out.println("There are no numbers with these properties.");
                    marker = false;
                }else if (marker) {
                    NumbersRouting.routerMulty(base, amount, finalProperties);
                    marker = false;
                }
            }

            if (marker){
                for (long i = base; i < base + amount; i++) {
                    System.out.println(Printing.makeString(i));
                }
            }

            System.out.println();
            System.out.println("Enter a request:");
        }
    }
}