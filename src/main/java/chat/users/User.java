package chat.users;


import chat.users.permission.Permission;
import chat.users.role.Role;
import lombok.Getter;
import lombok.Setter;;
import java.sql.Date;
import java.util.Set;

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
    private Date birthdate;
    @Getter
    private Address address;
    @Getter @Setter
    private Role role;
    @Getter
    private Set<Permission> permissions;

    public User(String firstName, String lastName, String password, String email, Date birthdate, Address address)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.birthdate = birthdate;
        this.address = address;
    }

    public User(String firstName, String lastName, String password, String email, Date birthdate, Address address, Set<Permission> permissions)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.birthdate = birthdate;
        this.address = address;
        this.permissions = permissions;
    }

    public void setAttempts()
    {
        attempts++;
    }

    public void resetAttempts()
    {
        attempts = 0;
    }

    @Override
    public String toString()
    {
        return firstName + lastName;
    }
}
