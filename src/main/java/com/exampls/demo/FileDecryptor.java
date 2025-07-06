package com.exampls.demo;

import java.io.*;
import javax.crypto.SecretKey;

public class FileDecryptor {
    public static void decryptFile(String inputPath, String outputPath, SecretKey key) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(inputPath));
        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        reader.close();

        byte[] decryptedBytes = AESUtils.decrypt(sb.toString(), key);

        FileOutputStream fos = new FileOutputStream(outputPath);
        fos.write(decryptedBytes);
        fos.close();
    }
}
