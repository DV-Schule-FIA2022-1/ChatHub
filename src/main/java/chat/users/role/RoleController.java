package chat.users.role;

import chat.users.functions.HashFunction;

import java.sql.*;

public class RoleController
{
    private Role role;
    private String databaseURL = "jdbc:ucanaccess://src/main/java/chat/database/TestDB.accdb";
    private int row;

    public RoleController(Role role)
    {
        this.role = role;
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
            preparedStatement.setDate(4, role.getUpdatedAt());
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
}
