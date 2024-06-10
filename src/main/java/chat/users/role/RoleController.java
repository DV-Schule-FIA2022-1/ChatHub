package chat.users.role;

import chat.users.permission.Permission;
import java.sql.*;
import java.time.LocalDateTime;

public class RoleController
{
    private Role role;
    private String databaseURL = "jdbc:ucanaccess://src/main/java/chat/database/TestDB.accdb";
    private int row;

    public RoleController(Role role)
    {
        this.role = role;
    }

    public void addPermission(Permission permission)
    {
        role.getPermissions().add(permission);
    }

    public void removePermission(Permission permission)
    {
        role.getPermissions().remove(permission);
    }

    public boolean hasPermisison(Permission permission)
    {
        return role.getPermissions().contains(permission);
    }

    public void createRole(Role role)
    {
        try(Connection connection = DriverManager.getConnection(databaseURL))
        {
            String sql = "INSERT INTO Role (roleName, description, createAt, updatedAt, isActive) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, role.getRoleName());
            preparedStatement.setString(2, role.getDescription());
            Timestamp timestamp = Timestamp.valueOf(role.getCreateAt());
            Date sqlDate = new Date(timestamp.getTime());
            preparedStatement.setDate(3, sqlDate);
            Timestamp timestampUpdate = Timestamp.valueOf(role.getUpdatedAt());
            Date sqlDateUpdate = new Date(timestampUpdate.getTime());
            preparedStatement.setDate(4, sqlDateUpdate);
            preparedStatement.setBoolean(5, role.isActive());

            row = preparedStatement.executeUpdate();

            if(row > 0)
            {
                System.out.println("Data inserted sucessfully");
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    public void updateRoleDescription(String description)
    {
        role.setDescription(description);
        role.setUpdatedAt(LocalDateTime.now());
    }

    public void deactivateRole()
    {
        role.setActive(false);
        role.setUpdatedAt(LocalDateTime.now());
    }

    public void reactivateRole()
    {
        role.setActive(true);
        role.setUpdatedAt(LocalDateTime.now());
    }
}
