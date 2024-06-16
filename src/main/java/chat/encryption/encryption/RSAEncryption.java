package chat.encryption.encryption;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;

public class RSAEncryption {
    private BigInteger e;
    private BigInteger n;

    public RSAEncryption(BigInteger e, BigInteger n) {
        this.e = e;
        this.n = n;
    }

    public BigInteger encrypt(BigInteger message) {
        return message.modPow(e, n);
    }

    public BigInteger[] encryptMessage(int[] message) {
        BigInteger[] encryptedMessage = new BigInteger[message.length];
        for (int i = 0; i < message.length; i++) {
            encryptedMessage[i] = encrypt(BigInteger.valueOf(message[i]));
        }
        return encryptedMessage;
    }
}