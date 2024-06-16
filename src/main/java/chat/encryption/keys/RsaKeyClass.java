package chat.encryption.keys;

import java.util.Arrays;

public class RsaKeyClass {
    private char[] prvNum1;
    private char[] prvNum2;
    private char[] pubNum1;
    private char[] pubNum2;

    public RsaKeyClass(char[] prvNum1, char[] prvNum2, char[] pubNum1, char[] pubNum2) {
        this.prvNum1 = prvNum1;
        this.prvNum2 = prvNum2;
        this.pubNum1 = pubNum1;
        this.pubNum2 = pubNum2;
    }

    public char[] getPrvNum1() {
        return prvNum1;
    }

    public char[] getPrvNum2() {
        return prvNum2;
    }

    public char[] getPubNum1() {
        return pubNum1;
    }

    public char[] getPubNum2() {
        return pubNum2;
    }





    @Override
    public String toString() {
        return "RsaKeyClass{" +
                "prvNum1=" + Arrays.toString(prvNum1) +
                ", prvNum2=" + Arrays.toString(prvNum2) +
                ", pubNum1=" + Arrays.toString(pubNum1) +
                ", pubNum2=" + Arrays.toString(pubNum2) +
                '}';
    }
}
