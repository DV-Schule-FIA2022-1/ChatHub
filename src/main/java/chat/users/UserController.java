package chat.users;

import chat.users.functions.HashFunction;
import lombok.Getter;

import java.sql.*;
import java.util.ArrayList;

public class UserController
{
    private int row;
    private int insertId;
    @Getter
    private ArrayList<User> userlist;
    private String databaseURL = "jdbc:ucanaccess://src/main/java/chat/database/TestDB.accdb";
    public UserController()
    {

    }

    public void addUser(User user)
    {
        try(Connection connection = DriverManager.getConnection(databaseURL))
        {
            String sqladdress = "INSERT INTO Address (street, city, zipCode, country) VALUES (?, ?, ?, ?)";

            PreparedStatement preparedStatementAdress = connection.prepareStatement(sqladdress);
            preparedStatementAdress.setString(1, user.getAddress().getStreet());
            preparedStatementAdress.setString(2, user.getAddress().getCity());
            preparedStatementAdress.setString(3, user.getAddress().getZipCode());
            preparedStatementAdress.setString(4, user.getAddress().getCountry());

            row = preparedStatementAdress.executeUpdate();

            if(row > 0)
            {
                ResultSet generatedKeys = preparedStatementAdress.getGeneratedKeys();
                if(generatedKeys.next())
                {
                    insertId = generatedKeys.getInt(1);
                    System.out.println("Data inserted sucessfully with ID " + insertId);
                }
                else
                {
                    System.out.println("Data insert failed");
                }
            }

            String sql = "INSERT INTO User (firstName, lastName, password, email, birthdate, addressNr) VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, HashFunction.toHexString(HashFunction.getSHA(user.getPassword())));
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setDate(5, user.getBirthdate());
            preparedStatement.setInt(6, insertId);

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

    public void readUser()
    {
        try(Connection conn = DriverManager.getConnection(databaseURL))
        {
            userlist = new ArrayList<>();
            String sql = "SELECT u.firstName, u.lastName, u.password, u.email, u.birthdate, a.street, a.city, a.zipCode, a.country FROM User u INNER JOIN Address a ON u.addressNR = a.ID";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next())
            {
                Address newadress = new Address(rs.getString("street"), rs.getString("city"), rs.getString("zipCode"), rs.getString("country"));
                User newuser = new User(rs.getString("firstName"), rs.getString("lastName"), rs.getString("password"), rs.getString("email"), rs.getDate("birthdate"), newadress);
                userlist.add(newuser);
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
