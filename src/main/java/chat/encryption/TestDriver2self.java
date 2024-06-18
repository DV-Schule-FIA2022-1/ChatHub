package chat.encryption;

import chat.encryption.decryption.RSADecryption;
import chat.encryption.encryption.RSAEncryption;
import chat.encryption.generators.RSAKeyGenerator;
import chat.encryption.prime.Prime;
import chat.encryption.prime.PrimeNumber;
import java.io.IOException;
import java.math.BigInteger;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

public class TestDriver2self
{
    private PrimeNumber prime;
    private RSAKeyGenerator rsaGenerator;
    private RSAEncryption encryptor;
    private RSADecryption decryptor;

    public TestDriver2self() throws IOException
    {
        prime = new PrimeNumber();
        System.out.println(prime);
        rsaGenerator = new RSAKeyGenerator();
        Prime p = new Prime(prime.start().getNum1(), prime.start().getNum2());
        rsaGenerator.start(p);

        char[] e = rsaGenerator.getE();
        char[] d = rsaGenerator.getD();
        char[] n = rsaGenerator.getN();


        BigInteger eBigInt = new BigInteger(String.valueOf(e));
        BigInteger dBigInt = new BigInteger(String.valueOf(d));
        BigInteger nBigInt = new BigInteger(String.valueOf(n));

        encryptor = new RSAEncryption(eBigInt, nBigInt);
        decryptor = new RSADecryption(dBigInt, nBigInt);

        /*BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String input = in.readLine();
        System.out.println("Original input: " + input);


        int[] asciiMessage = AsciiConvertor.stringToAscii(input);
        System.out.println("ASCII: " + Arrays.toString(asciiMessage));


        BigInteger[] encryptedMessage = encryptor.encryptMessage(asciiMessage);
        System.out.println("Encrypted message: " + Arrays.toString(encryptedMessage));


        int[] decryptedMessage = decryptor.decryptMessage(encryptedMessage);
        System.out.println("Decrypted ASCII: " + Arrays.toString(decryptedMessage));


        String decryptedText = AsciiConvertor.asciiToString(decryptedMessage);
        System.out.println("Decrypted text: " + decryptedText);*/
    }
}

class Main
{
    public static void main(String[] args) throws IOException, KeyStoreException, CertificateException, NoSuchAlgorithmException
    {
        new TestDriver2self();
    }
}
