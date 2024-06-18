package chat.encryption.keys;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Base64;

public class KeySaver
{
    public static void saveKeyToFile(byte[] keyBytes, String keyType, String fileName)
    {
        String encodedKey = Base64.getEncoder().encodeToString(keyBytes);
        StringBuilder pem = new StringBuilder();
        pem.append("-----BEGIN ").append(keyType).append("-----\n");
        pem.append(encodedKey);
        pem.append("\n-----END ").append(keyType).append("-----\n");

        try (FileWriter keyWriter = new FileWriter("M:\\programmirovanie\\javaChat\\ChatHub\\src\\main\\resources\\keys" + fileName))
        {
            keyWriter.write(pem.toString());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
