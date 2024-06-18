package chat.encryption.keys;

import javax.crypto.SecretKey;
import java.util.Arrays;

public class AesKeyClass
{
    private SecretKey key;

    public AesKeyClass(SecretKey key)
    {
        this.key = key;
    }

    public SecretKey getKey()
    {
        return key;
    }

    @Override
    public String toString() {
        return "AesKeyClass{" +
                "key=" + Arrays.toString(key.getEncoded()) +
                '}';
    }

}
