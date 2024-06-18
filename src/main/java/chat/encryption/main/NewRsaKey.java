package chat.encryption.main;

import chat.encryption.decryption.RSADecryption;
import chat.encryption.encryption.RSAEncryption;
import chat.encryption.generators.RSAKeyGenerator;
import chat.encryption.keys.RsaKeyClass;
import chat.encryption.prime.Prime;
import chat.encryption.prime.PrimeNumber;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Base64;

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

        BigInteger eBigInt = new BigInteger(new String(e));
        BigInteger dBigInt = new BigInteger(new String(d));
        BigInteger nBigInt = new BigInteger(new String(n));

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
