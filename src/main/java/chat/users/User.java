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

    public User(String firstName, String lastName, String password, String email, Date birthdate, Address address)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = HashFunction.toHexString(HashFunction.getSHA(password));
        this.email = email;
        this.birthdate = birthdate;
        this.address = address;
    }

    public User(String firstName, String lastName)
    {
        this.firstName = firstName;
        this.lastName = lastName;
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