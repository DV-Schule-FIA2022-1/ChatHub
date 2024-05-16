package chat.encryption;

import java.io.FileNotFoundException;

public class TestDriver2self {

    private PrimeNumber prime;
    public TestDriver2self() throws FileNotFoundException {
        prime = new PrimeNumber();
        System.out.println(prime.start().getNum1());

    }
}
class Main {
    public static void main(String[] args) throws FileNotFoundException {
        new TestDriver2self();
    }
}