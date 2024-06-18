package chat.encryption.generators;

import chat.encryption.keys.RsaKeyClass;
import chat.encryption.prime.Prime;
import java.math.BigInteger;

public class RSAKeyGenerator implements KeyGeneration
{
    private char[] num1;
    private char[] num2;
    private char[] n;
    private char[] phi;
    private char[] e;
    private char[] d;
    private RsaKeyClass keys;

    public void start(Prime p)
    {
        this.num1 = BigInteger.valueOf(p.getNum1()).toString().toCharArray();
        this.num2 = BigInteger.valueOf(p.getNum2()).toString().toCharArray();
        computeTheModulus();
    }

    private void computeTheModulus()
    {
        BigInteger num1BI = new BigInteger(new String(num1));
        BigInteger num2BI = new BigInteger(new String(num2));
        this.n = num1BI.multiply(num2BI).toString().toCharArray();
        System.out.println("Modulus n: " + new String(n));
        computeTheTotient();
    }

    private void computeTheTotient()
    {
        BigInteger num1BI = new BigInteger(new String(num1));
        BigInteger num2BI = new BigInteger(new String(num2));
        this.phi = num1BI.subtract(BigInteger.ONE).multiply(num2BI.subtract(BigInteger.ONE)).toString().toCharArray();
        System.out.println("Totient φ(n): " + new String(phi));
        chooseE();
    }

    private void chooseE()
    {
        BigInteger phiBI = new BigInteger(new String(phi));
        this.e = BigInteger.valueOf(44681213).toString().toCharArray();
        if (phiBI.gcd(new BigInteger(new String(e))).equals(BigInteger.ONE))
        {
            computeD();
        }
        else
        {
            throw new ArithmeticException("gcd(φ(n), e) != 1");
        }
    }

    private void computeD()
    {
        BigInteger eBI = new BigInteger(new String(e));
        BigInteger phiBI = new BigInteger(new String(phi));
        this.d = eBI.modInverse(phiBI).toString().toCharArray();
        System.out.println("Public Key (e, n): (" + new String(e) + ", " + new String(n) + ")");
        System.out.println("Private Key (d, n): (" + new String(d) + ", " + new String(n) + ")");
        keys = new RsaKeyClass(e, n, d, n);
    }

    @Override
    public RsaKeyClass generateKey() {
        return keys;
    }

    public RsaKeyClass getKeys() {
        return keys;
    }

    public char[] getN() {
        return n;
    }

    public char[] getE() {
        return e;
    }

    public char[] getD() {
        return d;
    }

    @Override
    public String toString() {
        return "RSAKeyGenerator{" +
                "keys=" + keys +
                '}';
    }
}
