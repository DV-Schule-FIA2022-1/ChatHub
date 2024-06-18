package chat.encryption.test;

import java.io.IOException;
import java.net.MalformedURLException;

public class randNumberGenerator
{
    private int num1;
    private int num2;

    public randNumberGenerator() {
        buildURL();
    }

    private void buildURL()
    {
        try
        {
            java.net.URL url = new java.net.URL("http://www.randomnumberapi.com/api/v1.0/random?min=1&max=3000&count=1");
            String numStr1 = cleanNumberString(stream(url));
            String numStr2 = cleanNumberString(stream(url));

            num1 = Integer.parseInt(numStr1);
            num2 = Integer.parseInt(numStr2);
            System.out.println(num1 +" " +num2);
        }
        catch (MalformedURLException e)
        {
            throw new RuntimeException(e);
        }
    }

    private String stream(java.net.URL url)
    {
        try (java.io.InputStream input = url.openStream())
        {
            java.io.InputStreamReader isr = new java.io.InputStreamReader(input);
            java.io.BufferedReader reader = new java.io.BufferedReader(isr);
            StringBuilder json = new StringBuilder();
            int c;
            while ((c = reader.read()) != -1)
            {
                json.append((char) c);
            }

            return json.toString();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    private String cleanNumberString(String str)
    {
        return str.replaceAll("[\\[\\]\\s]", "");
    }

    public static void main(String[] args)
    {
        new randNumberGenerator();
    }
}
