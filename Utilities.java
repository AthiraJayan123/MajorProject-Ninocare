/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.util.Random;

/**
 *
 * @author TeamJava
 */
public class Utilities {
 
    public static String uid="1";
    public static String utype="";
     public static char[] OTP(int len) 
    {
        String numbers = "0123456789abcdefghijklmnopqrstuvwxyz!@#$%&*";
        Random rndm_method = new Random();

        char[] otp = new char[len];

        for (int i = 0; i < len; i++) {
            otp[i] = numbers.charAt(rndm_method.nextInt(numbers.length()));
        }
        return otp;
    }
}
