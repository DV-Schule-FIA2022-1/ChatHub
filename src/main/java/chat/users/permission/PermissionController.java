package chat.users.permission;

import chat.users.role.Role;

public class PermissionController
{
    private Permission permission;

    public PermissionController(Permission permission)
    {
        this.permission = permission;
    }

    public boolean hasRole(Role role)
    {
        return permission.getRoles().contains(role);
    }

    public void addRole(Role role)
    {
        permission.getRoles().add(role);
    }

    public void removeRole(Role role)
    {
        permission.getRoles().remove(role);
    }

    public boolean validate()
    {
        return permission.getName() != null && !permission.getName().isEmpty() &&
                permission.getResource() != null && !permission.getResource().isEmpty() &&
                permission.getAction() != null && !permission.getAction().isEmpty();
    }
}
