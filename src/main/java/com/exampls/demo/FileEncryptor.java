package com.exampls.demo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

import javax.crypto.SecretKey;

public class FileEncryptor {
    public static void encryptFile(String inputPath, String outputPath, SecretKey key) throws Exception {
        // Read input file as bytes
        byte[] inputBytes;
        try (FileInputStream fis = new FileInputStream(inputPath)) {
            inputBytes = fis.readAllBytes();
            System.out.println("File read successfully. Size: " + inputBytes.length + " bytes.");
        }

        // Encrypt the data using AES
        String encrypted = AESUtils.encrypt(inputBytes, key);
        System.out.println("Encryption successful. Encrypted text length: " + encrypted.length());

        // Write encrypted data as UTF-8 text
        try (FileOutputStream fos = new FileOutputStream(outputPath)) {
            fos.write(encrypted.getBytes(StandardCharsets.UTF_8));
            System.out.println("Encrypted file written successfully to: " + outputPath);
        }
    }
}
