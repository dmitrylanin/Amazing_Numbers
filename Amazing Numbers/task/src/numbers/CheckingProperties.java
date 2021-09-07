package numbers;

import java.util.Arrays;

public class CheckingProperties {
    static String[][] controversialProperties = {{"DUCK", "SPY"}, {"SUNNY", "SQUARE"}, {"HAPPY", "SAD"}};
    static String [] dTypes = Arrays.toString(DigitTypes.values()).substring(1,(Arrays.toString(DigitTypes.values()).length()-2)).split(" ");

    //ОТФИЛЬТРОВЫВАЮ ВЗАИМОИСКЛЮЧАЮЩИЕ ТРЕБОВАНИЯ - ЧИСЛО ОДНОВРЕМЕННО ЧЕТНОЕ И НЕЧЕТНОЕ
    public static CheckStatus isControversialProperties(String finalProperies){
        String [] finalProperiesArr = finalProperies.split(" ");

        if (finalProperies.toUpperCase().contains("EVEN") && finalProperies.toUpperCase().contains("ODD")){
            return new CheckStatus(false, String.format("The request contains mutually exclusive properties: [%s, %s]", "ODD", "EVEN"));
        }else if(finalProperies.toUpperCase().contains("-EVEN") && finalProperies.toUpperCase().contains("-ODD")){
            return new CheckStatus(false, String.format("The request contains mutually exclusive properties: [%s, %s]", "-ODD", "-EVEN"));
        }else if(finalProperies.toUpperCase().contains("ODD") && finalProperies.toUpperCase().contains("-ODD")){
            return new CheckStatus(false, String.format("The request contains mutually exclusive properties: [%s, %s]", "ODD", "-ODD"));
        }else if(finalProperies.toUpperCase().contains("EVEN") && finalProperies.toUpperCase().contains("-EVEN")){
            return new CheckStatus(false, String.format("The request contains mutually exclusive properties: [%s, %s]", "EVEN", "-EVEN"));
        }

        for(int i = 0; i<controversialProperties.length; i++){
            if (Arrays.asList(finalProperiesArr).contains(controversialProperties[i][0]) && Arrays.asList(finalProperiesArr).contains(controversialProperties[i][1])){
                return new CheckStatus(false,String.format("The request contains mutually exclusive properties: [%s, %s]", controversialProperties[i][0], controversialProperties[i][1]));
            }
        }
        return new CheckStatus(true, "");
    }


    public static CheckStatus getIncorrectProperties(String[] strArr){
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

        if(incorrectProperties.length() > 0){
            return new CheckStatus(true, "");
        }else{
            return new CheckStatus(false, "");
        }
    }


    public static CheckStatus isSelfControversialProperties(String [] strArr){
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
                return new CheckStatus(true, String.format("The request contains mutually exclusive properties: [%s, %s]", dTypes[i], "-"+dTypes[i]));
            }
        }
        return new CheckStatus(false, "");
    }
}
