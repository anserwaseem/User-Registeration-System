package Q1;

import java.nio.charset.Charset;
import java.util.*;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Crypt {
    Crypt() {
    }

    public String encrypt(String key, String randomVector, String value) {
        try {
            // creates an IvParameterSpec object which'll later be used to init the cipher
            IvParameterSpec iv = new IvParameterSpec(randomVector.getBytes("UTF-8"));
            // constructs a secret key from the given key(in byte array)
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(Charset.forName("UTF-8")), "AES");
            // creating the cipher with the CBC algorithm
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            // start encryption
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(value.getBytes());
            byte[] encodedBytes = Base64.getEncoder().encode(encrypted);
            System.out.println("encrypted text: " + new String(encodedBytes));
            return new String(encodedBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String decrypt(String key, String randomVector, String encrypted) {
        try {
            // creates an IvParameterSpec object which'll later be used to init the cipher
            IvParameterSpec iv = new IvParameterSpec(randomVector.getBytes("UTF-8"));
            // constructs a secret key from the given key(in byte array)
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(Charset.forName("UTF-8")), "AES");
            // creating the cipher with the CBC algorithm
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            // start decryption
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] originalText = cipher.doFinal(Base64.getDecoder().decode(encrypted));

            System.out.println("decrypted text: " + new String(originalText));
            return new String(originalText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // public static void main(String[] args) {
    // String key = "JavasEncryptDemo"; // 128 bit key
    // String randomVector = "RandomJavaVector"; // 16 bytes IV
    // decrypt(key, randomVector, encrypt(key, randomVector, "Anything you want to
    // encrypt!"));

    // }
}