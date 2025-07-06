package com.exampls.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import java.io.*;

@Controller
public class FileController {

    @PostMapping("/encrypt")
    @ResponseBody
    public String encryptFile(@RequestParam("file") MultipartFile file) {
        try {
            String filename = StringUtils.cleanPath(file.getOriginalFilename());
            byte[] data = file.getBytes();

            SecretKey key = AESUtils.generateKey();
            String encrypted = AESUtils.encrypt(data, key);

            // Save encrypted content to a file
            FileWriter writer = new FileWriter("encrypted_" + filename);
            writer.write(encrypted);
            writer.close();

            String keyStr = AESUtils.keyToString(key);

            return "<h3>✅ File Encrypted Successfully!</h3>"
                    + "<p>Saved as: <b>encrypted_" + filename + "</b></p>"
                    + "<p><strong>Save this key to decrypt:</strong><br><code>" + keyStr + "</code></p>";
        } catch (Exception e) {
            return "<h3>❌ Error during encryption:</h3> " + e.getMessage();
        }
    }

    @PostMapping("/decrypt")
    @ResponseBody
    public String decryptFile(@RequestParam("file") MultipartFile file,
                              @RequestParam("key") String keyStr) {
        try {
            String filename = StringUtils.cleanPath(file.getOriginalFilename());
            String encryptedContent = new String(file.getBytes());

            SecretKey key = AESUtils.getKeyFromString(keyStr);
            byte[] decrypted = AESUtils.decrypt(encryptedContent, key);

            FileOutputStream fos = new FileOutputStream("decrypted_" + filename);
            fos.write(decrypted);
            fos.close();

            return "<h3>✅ File Decrypted Successfully!</h3>"
                    + "<p>Saved as: <b>decrypted_" + filename + "</b></p>";
        } catch (Exception e) {
            return "<h3>❌ Error during decryption:</h3> " + e.getMessage();
        }
    }
}
