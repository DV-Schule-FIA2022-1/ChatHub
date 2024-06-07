package chat.encryption.prime;

import chat.encryption.generators.APINumberGenerator;
import chat.encryption.prime.Prime;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrimeNumber {
    APINumberGenerator randNumbers = new APINumberGenerator();
    private Prime p;
    private int num1 = randNumbers.getNumbers().getNum1();
    private int num2 = randNumbers.getNumbers().getNum2();

    private String fileName = "H:\\javaProjectChat\\ChatHub\\src\\main\\java\\chat\\encryption\\prime\\primes.txt";
    Scanner sr;

    public Prime start() throws FileNotFoundException {
        return readFile(num1, num2);
    }

    private Prime readFile(int num1, int num2) throws FileNotFoundException {
        List<String> lines = new ArrayList<>();
        sr = new Scanner(new FileReader(fileName));
        while (sr.hasNextLine()) {
            lines.add(sr.nextLine());
        }
        sr.close();

        if (num1 <= lines.size() && num2 <= lines.size()) {
            int firstPrime = Integer.parseInt(lines.get(num1 - 1));
            int secondPrime = Integer.parseInt(lines.get(num2 - 1));

            return new Prime(firstPrime, secondPrime);
        } else {
            throw new IllegalArgumentException("Number > file size");
        }
    }

    @Override
    public String toString() {
        return "PrimeNumber{" +
                "num1=" + num1 +
                ", num2=" + num2 +
                '}';
    }
}
