package utils;

import java.security.SecureRandom;

public class KeyGeneratorUtil {

	
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*";

    public static String generateRandomKey(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);
        for(int i = 0; i < length; i++) {
            int index = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }
        return sb.toString();
    }

    
    // Note :- It generating AES format -->  AES-128       AES-192              AES-256
    // key length(chars)                -->     16           24                    32
    // Strength Level	                -->  Secure        Stronger            more Stronger
    // Speed (Performance)              -->  Fastest   Slower than AES-128,      Slowest
    // you can generate key based on your criteria. 
    // it gives random key.
    
    public static void main(String[] args) {
        String key = generateRandomKey(16);
        System.out.println("Random "+key.length() +"-char Key: " + key);
        
    }
}