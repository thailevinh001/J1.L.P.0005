/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package J1.L.P0005;

/**
 *
 * @author ADMIN
 */
public class CaesarCodeing {
    public static String caesarCodeing(String src){
        String result="";
        for (int i = 0; i < src.length(); i++) {
            char c =(char) (((int)src.charAt(i)+123)%256);
            result+=c;
            
        }
        return result;
    }
    public static String cearDecode123(String src){
        String result="";
        for (int i = 0; i < src.length(); i++) {
            char c =(char) (((int)src.charAt(i)-123)%256);
            result+=c;
            
        }
        return result;
    }
}
