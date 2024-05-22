package chat.users;

import lombok.Data;
import lombok.Getter;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;

public class User
{
    @Getter
    private static int userID;
    private int localID;
    @Getter
    private int attempts;
    @Getter
    private String firstName;
    @Getter
    private String lastName;
    @Getter
    private String password;
    @Getter
    private String email;
    @Getter
    private int age;
    @Getter
    private Date birthdate;
    @Getter
    private Address address;
    private static MessageDigest md;

    public User(String firstName, String lastName, String password, String email, Date birthdate, Address address)
    {
        localID = userID++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = toHexString(getSHA(password));
        this.email = email;
        this.birthdate = birthdate;
        this.address = address;
    }

    public User(String firstName, String lastName)
    {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static byte[] getSHA(String input)
    {
        try
        {
           md = MessageDigest.getInstance("SHA-256");
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }

        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    public static String toHexString(byte[] hash)
    {
        BigInteger number = new BigInteger(1, hash);
        StringBuilder hexString = new StringBuilder(number.toString(16));

        while (hexString.length() < 64)
        {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }

    public void setAttempts()
    {
        attempts++;
    }

    @Override
    public String toString()
    {
        return firstName + lastName;
    }
}
