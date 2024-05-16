package chat.encryption;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class RSAKeyGenerator {
    /*public static KeyPair generateRSAKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.generateKeyPair();
    }*/
    private int num1;
    private int num2;

    public RSAKeyGenerator(Prime p)
    {
        num1 = p.getNum1();
        num2 = p.getNum2();
    }



}
