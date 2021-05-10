package com.springboot.bankapp.helper;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.security.crypto.codec.Hex;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

public class AesUtil {
    private final int keySize=128;
    private final int iterationCount=1000;
    private final Cipher cipher;
    private final String salt="f4d2e613fa9cefddc637a0e72f7eed9d";
    private final String iv="67b52091dc4b98645fe707d019095502";
    private final String passphrase="thisissecret";
    private SecretKey finalKey;
    
    public String getIv() {
		return iv;
	}

	public String getSalt() {
		return salt;
	}

	public String getPassphrase() {
		return passphrase;
	}


	public AesUtil() {
        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        }
        catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw fail(e);
        }
    }
    
    public String decrypt(String ciphertext) {
        try {
        	this.generateKey();
            SecretKey key = finalKey;
            byte[] decrypted = doFinal(Cipher.DECRYPT_MODE, key, this.getIv(), base64(ciphertext));
            return new String(decrypted, "UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            return null;
        }catch (Exception e){
            return null;
        }
    }
    
    private byte[] doFinal(int encryptMode, SecretKey key, String iv, byte[] bytes) {
        try {
            cipher.init(encryptMode, key, new IvParameterSpec(hex(iv)));
            return cipher.doFinal(bytes);
        }
        catch (InvalidKeyException
                | InvalidAlgorithmParameterException
                | IllegalBlockSizeException
                | BadPaddingException e) {
            return null;
        }
    }
    
    public SecretKey generateKey() {
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            KeySpec spec = new PBEKeySpec(this.getPassphrase().toCharArray(), hex(this.getSalt()), iterationCount, keySize);
            SecretKey key = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
            this.finalKey=key;
            return key;
        }
        catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            return null;
        }
    }

    public static byte[] base64(String str) {
        return Base64.getDecoder().decode(str);
    }
    
    public static byte[] hex(String str) {
        try {
            return Hex.decode(str);
        }
        catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
    
    private IllegalStateException fail(Exception e) {
        return null;
    }

}
