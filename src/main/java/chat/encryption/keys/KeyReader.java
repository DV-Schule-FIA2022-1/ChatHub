package chat.encryption.keys;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Base64;

public class KeyReader
{
    public static SecretKey readAESKeyFromFile(String fileName)
    {
        StringBuilder keyPEM = new StringBuilder();
        try (BufferedReader keyReader = new BufferedReader(new FileReader("M:\\programmirovanie\\javaChat\\ChatHub\\src\\main\\resources\\keys" + fileName)))
        {
            String line;
            while ((line = keyReader.readLine()) != null)
            {
                if (line.contains("-----BEGIN") || line.contains("-----END"))
                {
                    continue;
                }
                keyPEM.append(line);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        byte[] decodedKey = Base64.getDecoder().decode(keyPEM.toString());
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
    }
}
