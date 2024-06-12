package chat.users.permission;

import chat.users.role.Role;
import lombok.Getter;
import java.util.Set;

public class Permission
{
    @Getter
    private String name;
    @Getter
    private String description;
    @Getter
    private Set<Role> roles;
    @Getter
    private String resource;
    @Getter
    private String action;
    @Getter
    private String condition;
    private PermissionController permissionController;

    public Permission()
    {
        permissionController = new PermissionController(this);
    }
}
