package chat.encryption.test;

import java.io.FileWriter;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class SaveKeysToFile {

    public static void main(String[] args) {
        try {

            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(2048);
            KeyPair pair = keyGen.generateKeyPair();


            String privateKeyPEM = encodeKeyToPEM(pair.getPrivate().getEncoded(), "PRIVATE KEY");



            String publicKeyPEM = encodeKeyToPEM(pair.getPublic().getEncoded(), "PUBLIC KEY");
            try (FileWriter privateKeyWriter = new FileWriter("H:\\javaProjectChat\\ChatHub\\src\\main\\resources\\keys\\private_key.pem")) {
                privateKeyWriter.write(privateKeyPEM);
            }
            try (FileWriter publicKeyWriter = new FileWriter("H:\\javaProjectChat\\ChatHub\\src\\main\\resources\\keys.pem")) {
                publicKeyWriter.write(publicKeyPEM);
            }



        } catch (NoSuchAlgorithmException | IOException e) {
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
