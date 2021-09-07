package numbers;

public class NumbersRouting {
    /*
    В этом классе содержатся роутеры, позволяющие подобрать число по заданному набору свойств
    */

    //Метод для подбора числа с каким-то одним заданным свойством
    public static void routerSolo(long number, long amount, String type) {
        int count = 0;
        for (long i = number; count < amount; i++) {
            if(type.charAt(0) == '-'){
                if (!DigitTypes.valueOf(type).isType(i)) {
                    count++;
                    System.out.println(Printing.makeString(i));
                }
            }else{
                if (DigitTypes.valueOf(type).isType(i)) {
                    count++;
                    System.out.println(Printing.makeString(i));
                }
            }
        }
    }

    //Метод для подбора чисел с несколькими свойствами
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
}
