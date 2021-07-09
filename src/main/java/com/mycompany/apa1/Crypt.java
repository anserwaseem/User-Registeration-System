package com.mycompany.apa1;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import javax.crypto.BadPaddingException;

import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
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
            System.out.println("encrypted password: " + new String(encodedBytes));
            return new String(encodedBytes);
        } catch (UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            System.out.println(e);
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

            System.out.println("decrypted password: " + new String(originalText));
            return new String(originalText);
        } catch (UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            System.out.println(e);
        }
        return null;
    }
}