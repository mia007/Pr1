package ua.nure.mykytenko.Practice3;

import java.nio.charset.Charset;
import java.security.*;

public class Part4 {

	public static String hash(String input, String algorithm) throws NoSuchAlgorithmException {
		if ((algorithm == null) || (algorithm.trim().length() == 0) || (input == null)){
			return null;
		}
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance(algorithm);
			md.update(input.getBytes(Charset.forName("UTF-8")));
		} catch (NoSuchAlgorithmException ex) {
			System.out.println(ex.toString());
		}
		if (md != null) {
			byte[] digest = md.digest();
			StringBuilder sb = new StringBuilder();
			for (byte b : digest) {
				sb.append(String.format("%02X", b));
			}
			return sb.toString();
		} else {
			return null;
		}
	}

	public static void main(String[] args) throws NoSuchAlgorithmException {
		System.out.println(hash("password", "SHA-256"));
		System.out.println(hash("passwort", "SHA-512"));
	}

}
