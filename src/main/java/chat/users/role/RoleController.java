package chat.users.role;

import chat.users.User;
import chat.enums.ChathubEnum;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

public class RoleController
{
    private Role role;
    private int row;
    private ArrayList<Role> roleList;

    public RoleController(Role role)
    {
        this.role = role;
    }

    public boolean hasRole(User user, Role role)
    {
        return user.getRole().equals(role);
    }

    public void editRole(User user, Role role)
    {
        user.setRole(role);
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

    public void createRole(Role role)
    {
        try(Connection connection = DriverManager.getConnection(ChathubEnum.DatabasePath.getPath()))
        {
            String sql = "INSERT INTO Role (roleName, description, priortiyLevel, createAt, updatedAt, isActive) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, role.getRoleName());
            preparedStatement.setString(2, role.getDescription());
            preparedStatement.setInt(3, role.getPriortiyLevel());
            Timestamp timestamp = Timestamp.valueOf(role.getCreateAt());
            Date sqlDate = new Date(timestamp.getTime());
            preparedStatement.setDate(4, sqlDate);
            Timestamp timestampUpdate = Timestamp.valueOf(role.getUpdatedAt());
            Date sqlDateUpdate = new Date(timestampUpdate.getTime());
            preparedStatement.setDate(5, sqlDateUpdate);
            preparedStatement.setBoolean(6, role.isActive());

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

    public void readRole()
    {
        try(Connection conn = DriverManager.getConnection(ChathubEnum.DatabasePath.getPath()))
        {
            roleList = new ArrayList<>();
            String sql = "SELECT roleName, description, priortiyLevel, createAt, updatedAt, isActive FROM Role";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next())
            {
                Role role = new Role(rs.getString("roleName"), rs.getString("description"), rs.getInt("priortiyLevel"), rs.getDate("createAt").toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(), rs.getDate("updatedAt").toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(), rs.getBoolean("isActive"));
                roleList.add(role);
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }
}
