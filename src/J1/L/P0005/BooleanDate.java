package J1.L.P0005;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Vinh Thai
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author PC
 */
public class BooleanDate {
    

    public static String inputString(String pattern) throws Exception {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        if (!s.matches(pattern)) {
            throw new Exception();
        }
        return s;
    }

    public static boolean isValidId(String id) {
        String idRegex = "^[0-9]{5,5}$";
        Pattern userNamePat = Pattern.compile(idRegex);
        Matcher matcher = userNamePat.matcher(id);
        return matcher.find();
    }

    public static int getInteger() {
        int n;
        int i;
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
              do
              {
                n = Integer.parseInt(sc.nextLine());
                if(n>0 && n<10){
                    return n;
                }else{
                    System.out.println("Please input number 1-9!");
                }
              }while(n>7);
                return n;

            } catch (Exception e) {
                System.out.println("Please input number 1-9!");
            }
        }

    }

    public static double getDouble() {
        Double n;
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                do
                {
                
                n = Double.parseDouble(sc.nextLine());
                if(n>0){
                    return n;
                }else{
                    System.out.println("Please input again!!");
                }
                
                }while(n<=0);
                
            } catch (Exception e) {
                System.out.println("Please input again!!");
            }
        }

    }
    public static boolean CheckLen(String x) throws Exception {
        boolean lowercase = false;
        boolean uppercase = false;
        boolean digits = false;
        boolean specialchar = false;
        String SC = "~`!@#$%^&*()-_=+\\|[{]};:'\",<.>/?";
        if (x.length() >= 6) {
            for (int i = 0; i < x.length(); i++) {
                char s = x.charAt(i);
                if (Character.isLowerCase(s)) {
                    lowercase = true;
                } else if (Character.isUpperCase(s)) {
                    uppercase = true;
                } else if (Character.isDigit(s)) {
                    digits = true;
                } else if (SC.contains(String.valueOf(s))) {
                    specialchar = true;
                }
            }
            return lowercase && uppercase && digits && specialchar;
        } else {
            throw new Exception();
        }
    }
    
}
