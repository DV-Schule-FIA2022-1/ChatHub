package chat.encryption.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class RecaptchaExample
{

    private static final String VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";
    private static final String SECRET_KEY = "твой_секретный_ключ_от_reCAPTCHA";

    public static void main(String[] args)
    {
        // Примеры ответов от reCAPTCHA для тестирования
        String validResponse = "valid-recaptcha-response";
        String invalidResponse = "invalid-recaptcha-response";

        // Проверяем корректный ответ
        System.out.println("Testing valid reCAPTCHA response:");
        boolean isValid = verify(validResponse);
        System.out.println("Is valid? " + isValid);

        // Проверяем некорректный ответ
        System.out.println("\nTesting invalid reCAPTCHA response:");
        boolean isInvalid = verify(invalidResponse);
        System.out.println("Is valid? " + isInvalid);
    }

    public static boolean verify(String recaptchaResponse)
    {
        try
        {
            String url = VERIFY_URL + "?secret=" + URLEncoder.encode(SECRET_KEY, "UTF-8") + "&response=" + URLEncoder.encode(recaptchaResponse, "UTF-8");
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null)
            {
                response.append(inputLine);
            }
            in.close();
            // Парсим ответ от сервера Google
            return response.toString().contains("\"success\": true");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
