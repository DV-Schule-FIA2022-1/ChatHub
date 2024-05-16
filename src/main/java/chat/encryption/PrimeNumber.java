package chat.encryption;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class PrimeNumber {
    private Prime p;
    private int num1;
    private int num2;
    private String fileName = "primes.txt";
    Scanner sr;
    /*public PrimeNumber() throws FileNotFoundException {
        start();
    }*/
    public Prime start() throws FileNotFoundException {
        while (num1 != num2) {
            num1 = (int) ((Math.random() * (619 - 1)) + 1);
            num2 = (int) ((Math.random() * (619 - 1)) + 1);
        }

        p = new Prime(num1, num2);

        return readFile(num1, num2);
    }
    private Prime readFile(int num1, int num2) throws FileNotFoundException {
        int firstPrime = 0;
        int secondPrime = 0;
        sr = new Scanner(new FileReader(fileName));
        int currentFile = 1;
        while (sr.hasNextLine())
        {
            String line= sr.nextLine();
            if(currentFile ==  num1);
                firstPrime = Integer.parseInt(line);
        }
        currentFile = 1;
        while (sr.hasNextLine())
        {
            String line= sr.nextLine();
            if(currentFile ==  num2);
                secondPrime = Integer.parseInt(line);
        }
        Prime p = new Prime(firstPrime, secondPrime);
        return p;
    }
}
