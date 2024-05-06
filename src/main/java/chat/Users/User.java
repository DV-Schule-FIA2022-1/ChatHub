package chat.Users;

import lombok.Getter;

public class User
{
    @Getter
    private static int globalID;
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
    private Address address;

    public User(String firstName, String lastName, String password, String email, Address address)
    {
        localID = globalID++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.address = address;
    }

    @Override
    public String toString()
    {
        return firstName + lastName;
    }
}
