package chat.users.permission;

import chat.users.User;
import chat.users.role.Role;

public class PermissionController
{
    private Permission permission;

    public PermissionController(Permission permission)
    {
        this.permission = permission;
    }

    public void addPermission(User user, Permission permission)
    {
        user.getPermissions().add(permission);
    }

    public void removePermission(User user, Permission permission)
    {
        user.getPermissions().remove(permission);
    }

    public boolean hasPermisison(User user, Permission permission)
    {
        return user.getPermissions().contains(permission);
    }
}
