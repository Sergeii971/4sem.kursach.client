package sample;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static sample.InformationWindows.expertsError;

public class Check {
    private static int s;

    public static boolean isNumeric(String x) {
        return isInt(x) || isDouble(x);
    }

    public static boolean isInt(String x) throws NumberFormatException {
        try {
            Integer.parseInt(x);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

     static boolean isDouble(String x) throws NumberFormatException {
        try {
            Double.parseDouble(x);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

   public static boolean checkLine(String array) {
        if (array.length() == 0) {
            return false;
        }
        for (int i = 0; i < 50; i++) {
            if ((array.charAt(i) >= 'a' && array.charAt(i) <= 'z') || (array.charAt(i) >= 'A' && array.charAt(i) <= 'Z') || (array.charAt(i) >= 'а' && array.charAt(i) <= 'я') || (array.charAt(i) >= 'А' && array.charAt(i) <= 'Я')) {
                if (i == array.length() - 1) {
                    return true;
                }
                continue;
            }
            else {
                return false;
            }
        }
        return true;
    }
   public static boolean checkYear(String year){
        if(!isInt(year))return false;
       Calendar date = new GregorianCalendar();
       if(Integer.parseInt(year)<=1800 || Integer.parseInt(year)>date.get(Calendar.YEAR)){
           return false;
       }
return true;
   }
   public static boolean checkMurk(String murk){
        if(murk.length()==0)return false;
        if(!isInt(murk))return false;
        if(Integer.parseInt(murk)>=0 && Integer.parseInt(murk)<=10)return true;
        else return false;
   }
}