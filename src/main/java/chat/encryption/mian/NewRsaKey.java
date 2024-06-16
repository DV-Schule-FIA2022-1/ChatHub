package chat.encryption.mian;

import chat.encryption.decryption.RSADecryption;
import chat.encryption.encryption.RSAEncryption;
import chat.encryption.generators.RSAKeyGenerator;
import chat.encryption.generators.AESKeyGenerator;
import chat.encryption.keys.RsaKeyClass;
import chat.encryption.prime.Prime;
import chat.encryption.prime.PrimeNumber;
import chat.encryption.encryption.AESEncryption;
import chat.encryption.decryption.AESDecryption;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;
import java.util.Arrays;

public class NewRsaKey {
    private PrimeNumber prime;
    private RSAKeyGenerator rsaGenerator;
    private RSAEncryption encryptor;
    private RSADecryption decryptor;

    public RsaKeyClass createNewRsaKey() throws IOException {
        prime = new PrimeNumber();
        rsaGenerator = new RSAKeyGenerator();
        return startNewKeyGenerator(generateTwoPrimes());
    }

    private Prime generateTwoPrimes() throws IOException {
        Prime p = new Prime(prime.start().getNum1(), prime.start().getNum2());
        return p;
    }

    private RsaKeyClass startNewKeyGenerator(Prime p) {
        rsaGenerator.start(p);

        char[] e = rsaGenerator.getE();
        char[] d = rsaGenerator.getD();
        char[] n = rsaGenerator.getN();

        if (e == null || d == null || n == null) {
            throw new IllegalStateException("RSA key generation failed. e, d, or n is null.");
        }

        BigInteger eBigInt = new BigInteger(String.valueOf(e));
        BigInteger dBigInt = new BigInteger(String.valueOf(d));
        BigInteger nBigInt = new BigInteger(String.valueOf(n));

        encryptor = new RSAEncryption(eBigInt, nBigInt);
        decryptor = new RSADecryption(dBigInt, nBigInt);

        // Сохранение ключей в файлы
        saveKeyToFile("H:\\javaProjectChat\\ChatHub\\src\\main\\resources\\keys\\rsa_private_key.pem", encodeKeyToPEM(dBigInt.toByteArray(), "RSA PRIVATE KEY"));
        saveKeyToFile("H:\\javaProjectChat\\ChatHub\\src\\main\\resources\\keys\\rsa_public_key.pem", encodeKeyToPEM(eBigInt.toByteArray(), "RSA PUBLIC KEY"));

        return new RsaKeyClass(d, n, e, n);
    }

    public RSAEncryption getEncryptor() {
        return encryptor;
    }

    public RSADecryption getDecryptor() {
        return decryptor;
    }

    private void saveKeyToFile(String fileName, String keyPEM) {
        try (FileWriter keyWriter = new FileWriter(fileName)) {
            keyWriter.write(keyPEM);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String encodeKeyToPEM(byte[] keyBytes, String keyType) {
        String encodedKey = Base64.getEncoder().encodeToString(keyBytes);
        StringBuilder pem = new StringBuilder();
        pem.append("-----BEGIN ").append(keyType).append("-----\n");
        pem.append(encodedKey);
        pem.append("\n-----END ").append(keyType).append("-----\n");
        return pem.toString();
    }
}

class Test {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        NewRsaKey test = new NewRsaKey();
        RsaKeyClass keys = test.createNewRsaKey();

        System.out.println(keys);

        // Генерация и сохранение AES ключа
        SecretKey aesKey = AESKeyGenerator.generateAESKey();
        saveAESKeyToFile(aesKey, "aes_key.pem");
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
    }

    private static void saveAESKeyToFile(SecretKey aesKey, String fileName) {
        String aesKeyPEM = encodeKeyToPEM(aesKey.getEncoded(), "AES KEY");
        try (FileWriter keyWriter = new FileWriter(fileName)) {
            keyWriter.write(aesKeyPEM);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String encodeKeyToPEM(byte[] keyBytes, String keyType) {
        String encodedKey = Base64.getEncoder().encodeToString(keyBytes);
        StringBuilder pem = new StringBuilder();
        pem.append("-----BEGIN ").append(keyType).append("-----\n");
        pem.append(encodedKey);
        pem.append("\n-----END ").append(keyType).append("-----\n");
        return pem.toString();
    }
}
