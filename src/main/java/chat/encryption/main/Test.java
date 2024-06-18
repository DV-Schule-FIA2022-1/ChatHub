package chat.encryption.main;

import chat.encryption.decryption.AESDecryption;
import chat.encryption.encryption.AESEncryption;
import chat.encryption.generators.AESKeyGenerator;
import chat.encryption.keys.KeyReader;
import chat.encryption.keys.KeySaver;
import chat.encryption.keys.RsaKeyClass;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Test
{
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        System.out.println("start");
        NewRsaKey test = new NewRsaKey();
        RsaKeyClass keys = test.createNewRsaKey();

        System.out.println(keys);

        SecretKey aesKey = new AESKeyGenerator().generateKey();
        KeySaver.saveKeyToFile(aesKey.getEncoded(), "AES KEY", "aes_key.pem");
        System.out.println("Generated AES Key: " + Arrays.toString(aesKey.getEncoded()));

        AESEncryption aesEncryptor = new AESEncryption(aesKey);
        AESDecryption aesDecryptor = new AESDecryption(aesKey);

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter a message to encrypt with AES: ");
        String input = in.readLine();
        System.out.println("Original input: " + input);

        String encryptedAES = aesEncryptor.encrypt(input);
        System.out.println("Encrypted message with AES: " + encryptedAES);

        String decryptedAES = aesDecryptor.decrypt(encryptedAES);
        System.out.println("Decrypted message with AES: " + decryptedAES);

        SecretKey loadedAesKey = KeyReader.readAESKeyFromFile("aes_key.pem");
        System.out.println("Loaded AES Key: " + Arrays.toString(loadedAesKey.getEncoded()));

        AESDecryption loadedAesDecryptor = new AESDecryption(loadedAesKey);
        String decryptedLoadedAES = loadedAesDecryptor.decrypt(encryptedAES);
        System.out.println("Decrypted message with loaded AES key: " + decryptedLoadedAES);
    }
}
