package utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class EncryptUtils {

	private static final String KEY = "Ov3atJOEPsDdWtLo"; // Don't mention key like this in your class file.

	// private static final String KEY = System.getenv("ENCRYPTION_KEY");
	// External key more secure it hides your key.

	public static String Encode(String data) throws Exception {
		if (KEY == null || !(KEY.length() == 16 || KEY.length() == 24 || KEY.length() == 32)) {
			throw new IllegalArgumentException("ENCRYPTION_KEY must be 16, 24, or 32 characters for AES-128/192/256");
		}

		SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(), "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, keySpec);
		byte[] encrypted = cipher.doFinal(data.getBytes()); // encoding given data with key.
		String Es = Base64.getEncoder().encodeToString(encrypted); // it gives encrypted byte value into string format.
		return Es;
	}

	public static String Decode(String encryptedData) throws Exception {
		if (KEY == null || !(KEY.length() == 16 || KEY.length() == 24 || KEY.length() == 32)) {
			throw new IllegalArgumentException("ENCRYPTION_KEY must be 16, 24, or 32 characters for AES-128/192/256");
		}

		SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(), "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, keySpec);
		byte[] decoded = Base64.getDecoder().decode(encryptedData);
		String Ds = new String(cipher.doFinal(decoded)); // decoding given encryptedData with key and gives into string format.
		return Ds;
	}

	// you need to pass your credentials as arguments to Encode for secure your credentials
	// don't forget after remove your valuable data in Encode after encoding.
	public static void main(String[] args) throws Exception {
		String encUsername = Encode("rahulshettyacademy ");
		String encPassword = Encode("learning");

		System.out.println("Encrypted Username: " + encUsername);
		System.out.println("Length -- > " + encUsername.length());

		System.out.println("Encrypted Password: " + encPassword);
		System.out.println("Length -- > " + encUsername.length());

	}

}