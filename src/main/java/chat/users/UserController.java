package chat.users;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserController
{
    String databaseURL = "jdbc:ucanaccess:C:\\Users\\Patrick\\Desktop\\Development\\ChatHub\\src\\main\\java\\chat\\database\\TestDB.accdb";
    public UserController()
    {

    }

    public void addUser(User user)
    {
        try(Connection connection = DriverManager.getConnection(databaseURL))
        {
            String sql = "INSERT INTO User (firstName, lastName, password, email, birthdate) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setDate(5, user.getBirthdate());

            int row = preparedStatement.executeUpdate();

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

    public void editUser()
    {

    }

    public void deleteUser()
    {

    }
}
