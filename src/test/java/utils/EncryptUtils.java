package utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class EncryptUtils {

	private static final String KEY= "Ov3atJOEPsDdWtLo"; //Don't mention key like this in your class file.
    

    //private static final String KEY = System.getenv("ENCRYPTION_KEY"); // External key more secure it hides your key.
 
    public static String Encode(String data) throws Exception {
        if (KEY == null || !(KEY.length() == 16 || KEY.length() == 24 || KEY.length() == 32)) {
            throw new IllegalArgumentException("ENCRYPTION_KEY must be 16, 24, or 32 characters for AES-128/192/256");
        }
        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        byte[] encrypted = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encrypted); // it gives encrypted value into string format.
    }

    public static String Decode(String encryptedData) throws Exception {
        if (KEY == null || !(KEY.length() == 16 || KEY.length() == 24 || KEY.length() == 32)) {
            throw new IllegalArgumentException("ENCRYPTION_KEY must be 16, 24, or 32 characters for AES-128/192/256");
        }
        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(), "AES");
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        byte[] decoded = Base64.getDecoder().decode(encryptedData);
        return new String(cipher.doFinal(decoded));
    }
    
    
    
    public static void main(String[] args) throws Exception {
        String encUsername = Encode("student");
        String encPassword = Encode("Password123");

        System.out.println("Encrypted Username: " + encUsername);
        System.out.println("Length -- > "+encUsername.length());
        
        System.out.println("Encrypted Password: " + encPassword);
        System.out.println("Length -- > "+encUsername.length());
    
    }
    
}