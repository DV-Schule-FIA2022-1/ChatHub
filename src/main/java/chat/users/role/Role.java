package chat.users.role;

import chat.users.User;
import chat.users.permission.Permission;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Role
{
    private int roleID;
    @Getter
    private String roleName;
    @Getter
    private ArrayList<Permission> permissions;
    @Getter @Setter
    private String description;
    private ArrayList<User> users;
    @Getter
    private LocalDateTime createAt;
    @Getter @Setter
    private LocalDateTime updatedAt;
    @Getter @Setter
    private boolean isActive;

    public Role(String roleName, String description, LocalDateTime createAt, LocalDateTime updatedAt, boolean isActive)
    {
        this.roleName = roleName;
        this.description = description;
        this.createAt = createAt;
        this.updatedAt = updatedAt;
        this.isActive = isActive;
    }
}
