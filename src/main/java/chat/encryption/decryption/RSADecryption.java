package chat.encryption.decryption;

import java.math.BigInteger;

public class RSADecryption {
    private BigInteger d;
    private BigInteger n;

    public RSADecryption(BigInteger d, BigInteger n) {
        this.d = d;
        this.n = n;
    }

    public BigInteger decrypt(BigInteger encrypted) {
        return encrypted.modPow(d, n);
    }

    public int[] decryptMessage(BigInteger[] encryptedMessage) {
        int[] decryptedMessage = new int[encryptedMessage.length];
        for (int i = 0; i < encryptedMessage.length; i++) {
            decryptedMessage[i] = decrypt(encryptedMessage[i]).intValue();
        }
        return decryptedMessage;
    }
}
