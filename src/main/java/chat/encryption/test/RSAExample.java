package chat.encryption.test;

import java.math.BigInteger;

public class RSAExample {
    public static void main(String[] args) {
        // Публичный ключ (e, n)
        BigInteger e = new BigInteger("44681213");
        BigInteger n = new BigInteger("4145181040035148943");

        // Приватный ключ (d, n)
        BigInteger d = new BigInteger("3818451484774460213");

        // Сообщение "привет" в ASCII кодах
        int[] message = {1087, 1088, 1080, 1074, 1077, 1090};

        // Зашифрование
        BigInteger[] encryptedMessage = new BigInteger[message.length];
        for (int i = 0; i < message.length; i++) {
            encryptedMessage[i] = encrypt(BigInteger.valueOf(message[i]), e, n);
        }

        // Расшифрование
        int[] decryptedMessage = new int[message.length];
        for (int i = 0; i < encryptedMessage.length; i++) {
            decryptedMessage[i] = decrypt(encryptedMessage[i], d, n).intValue();
        }

        // Вывод результатов

        for (BigInteger bi : encryptedMessage) {
            System.out.println(bi);
        }


        for (int m : decryptedMessage) {
            System.out.print((char) m);
        }
    }

    // Функция зашифрования
    public static BigInteger encrypt(BigInteger message, BigInteger e, BigInteger n) {
        return message.modPow(e, n);
    }

    // Функция расшифрования
    public static BigInteger decrypt(BigInteger encrypted, BigInteger d, BigInteger n) {
        return encrypted.modPow(d, n);
    }
}
