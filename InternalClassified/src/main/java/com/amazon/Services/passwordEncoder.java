package com.amazon.Services;

import com.amazon.Model.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

public class passwordEncoder {
    private static passwordEncoder passwordEncoder = new passwordEncoder();
    public static passwordEncoder getInstance()
    {
        return passwordEncoder;
    }
    private passwordEncoder(){

    }
    public String encode(String password)
    {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            String encodedPass=Base64.getEncoder().encodeToString(hash);
            return encodedPass;
        }catch (Exception e){
            System.err.println("Something went wrong: "+e);
        }
        return null;
    }
}
