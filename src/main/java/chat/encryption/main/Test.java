package chat.encryption.main;

import chat.encryption.decryption.AESDecryption;
import chat.encryption.encryption.AESEncryption;
import chat.encryption.generators.AESKeyGenerator;
import chat.encryption.keys.RsaKeyClass;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

public class Test {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        NewRsaKey test = new NewRsaKey();
        RsaKeyClass keys = test.createNewRsaKey();

        System.out.println(keys);

        SecretKey aesKey = new AESKeyGenerator().generateKey();
        saveAESKeyToFile(aesKey, "aes_key.pem");
        System.out.println("Generated AES Key: " + Arrays.toString(aesKey.getEncoded()));

        AESEncryption aesEncryptor = new AESEncryption(aesKey);
        AESDecryption aesDecryptor = new AESDecryption(aesKey);

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter a message to encrypt with AES: ");
        String input = in.readLine();
        System.out.println("Original input: " + input);

        String encryptedAES = aesEncryptor.encrypt(input);
        System.out.println("Encrypted message with AES: " + encryptedAES);

        String decryptedAES = aesDecryptor.decrypt(encryptedAES);
        System.out.println("Decrypted message with AES: " + decryptedAES);
    }

    private static void saveAESKeyToFile(SecretKey aesKey, String fileName) {
        String aesKeyPEM = encodeKeyToPEM(aesKey.getEncoded(), "AES KEY");
        try (FileWriter keyWriter = new FileWriter(fileName)) {
            keyWriter.write(aesKeyPEM);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String encodeKeyToPEM(byte[] keyBytes, String keyType) {
        String encodedKey = Base64.getEncoder().encodeToString(keyBytes);
        StringBuilder pem = new StringBuilder();
        pem.append("-----BEGIN ").append(keyType).append("-----\n");
        pem.append(encodedKey);
        pem.append("\n-----END ").append(keyType).append("-----\n");
        return pem.toString();
    }
}
