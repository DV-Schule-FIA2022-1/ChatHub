package chat.encryption;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class TestDriver {
    public static KeyPair generateRSAKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.generateKeyPair();
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        KeyPair keyPair = generateRSAKeyPair();
        System.out.println("Public key RSA: " + keyPair.getPublic());
        System.out.println("Private key RSA: " + keyPair.getPrivate());
    }
}

