package chat.encryption.generators;

import chat.encryption.keys.AesKeyClass;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;

public class AESKeyGenerator implements KeyGeneration
{
    AesKeyClass aesKeyClass;

    public AesKeyClass getAesKeyClass() {
        return aesKeyClass;
    }

    @Override
    public SecretKey generateKey() throws NoSuchAlgorithmException
    {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256);
        aesKeyClass = new AesKeyClass(keyGenerator.generateKey());
        return keyGenerator.generateKey();
    }
}
