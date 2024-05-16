package chat.encryption;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestDriver {
    /*public static void main(String[] args) {
        try {
            // Key generation
            SecretKey aesKey = AESKeyGenerator.generateAESKey();
            KeyPair rsaKeyPair = RSAKeyGenerator.generateRSAKeyPair();

            // message
            String message = "Message 123";

            ///////////////////////////////////////////////////////

            // convert to ascii:
            byte[] bytes = message.getBytes(StandardCharsets.US_ASCII);

            // print the first byte
            List<Integer> result = new ArrayList<>();   // convert bytes to ascii
            for (byte aByte : bytes) {
                int ascii = (int) aByte;                // byte -> int
                result.add(ascii);
            }

            System.out.println("ASCII: "+result.toString());
            //////////////////////////////////////////////////////

            //encryprion
            byte[] encryptedData = AESEncryption.encrypt(message, aesKey);
            for (byte b : encryptedData) {
                System.out.print(b + " ");
            }
            System.out.println();

            // decryption
            String decryptedMessage = AESEncryption.decrypt(encryptedData, aesKey);
            System.out.println("(AES): " + decryptedMessage);

            // aes encryption with rsa
            byte[] encryptedAESKey = RSAEncryption.encryptAESKey(aesKey, rsaKeyPair.getPublic());

            // decryption aes with rsa
            SecretKey decryptedAESKey = RSAEncryption.decryptAESKey(encryptedAESKey, rsaKeyPair.getPrivate());

            // encryption with decrypded AES
            byte[] encryptedDataRSA = AESEncryption.encrypt(message, decryptedAESKey);

            // decryption with dycrypted aes
            String decryptedMessageRSA = AESEncryption.decrypt(encryptedDataRSA, decryptedAESKey);
            System.out.println("(RSA + AES): " + decryptedMessageRSA);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*List<Integer> ascii = Arrays.asList(-41, -9, -125, 57, 99, 34, 66, 96, -50, 50, -95, 35, -115, 72, 4, 58);

        // Java 8 stream
        String result = ascii.stream()
                .map(x -> Character.toString(x))    // int -> string
                .collect(Collectors.joining());     // return a string

        System.out.println(result);*/



    //}
}
//https://www.chiragbhalodia.com/2021/09/rsa-algorithm-with-example.html#google_vignette