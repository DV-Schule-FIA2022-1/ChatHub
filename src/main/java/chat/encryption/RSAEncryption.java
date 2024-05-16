package chat.encryption;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;

public class RSAEncryption {
    public static byte[] encryptAESKey(Key aesKey, PublicKey rsaPublicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, rsaPublicKey);
        return cipher.doFinal(aesKey.getEncoded());
    }

    public static SecretKey decryptAESKey(byte[] encryptedAESKey, PrivateKey rsaPrivateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, rsaPrivateKey);
        byte[] decryptedKey = cipher.doFinal(encryptedAESKey);
        return new SecretKeySpec(decryptedKey, "AES");
    }
}