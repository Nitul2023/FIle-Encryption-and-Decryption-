package com.exampls.demo;

import java.util.Scanner;

import javax.crypto.SecretKey;

public class Main {
    public static void main(String[] args) {
        try {
            try (Scanner scanner = new Scanner(System.in)) {
                System.out.print("Enter file to encrypt: ");
                String inputFile = scanner.nextLine();

                System.out.print("Enter path to save encrypted file: ");
                String encryptedFile = scanner.nextLine();

                System.out.print("Enter path to save decrypted file: ");
                String decryptedFile = scanner.nextLine();

                SecretKey key = AESUtils.generateKey();
                String keyStr = AESUtils.keyToString(key);
                System.out.println("Generated Key (Save this to decrypt): " + keyStr);

                // Encrypt
                FileEncryptor.encryptFile(inputFile, encryptedFile, key);
                System.out.println("File encrypted and saved to: " + encryptedFile);

                // Decrypt
                FileDecryptor.decryptFile(encryptedFile, decryptedFile, key);
                System.out.println("File decrypted and saved to: " + decryptedFile);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
