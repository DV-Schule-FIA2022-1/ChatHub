package chat.users.role;

import chat.users.User;
import chat.users.permission.Permission;
import lombok.Getter;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Role
{
    private int roleID;
    @Getter
    private String roleName;
    private Permission permissions;
    @Getter
    private String description;
    private ArrayList<User> users;
    @Getter
    private LocalDateTime createAt;
    @Getter
    private Date updatedAt;
    @Getter
    private boolean isActive;

    public Role(String roleName, String description, LocalDateTime createAt, Date updatedAt, boolean isActive)
    {
        this.roleName = roleName;
        this.description = description;
        this.createAt = createAt;
        this.updatedAt = updatedAt;
        this.isActive = isActive;
    }
}
