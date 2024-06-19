package chat.WorkThogether.Server;

import java.io.IOException;
import java.util.ArrayList;
import lombok.Getter;
import java.sql.*;
import java.util.ArrayList;
import chat.enums.ChathubEnum;
import okhttp3.Address;

public class ServerController
{
    private int row;
    private int insertId;
    private ArrayList<Server> serverliste;
    private boolean updateRunning = true;
    private int updateInterval = 60000;

    public ServerController()
    {
        serverliste = new ArrayList<>();
        readWorkThogethers();
        autoUpdateServerDB();
        //int id = newWorkThogether();

    }

    public void readWorkThogethers()
    {
        try(Connection conn = DriverManager.getConnection(ChathubEnum.DatabasePath.getPath()))
        {
            //String sql = "SELECT u.FirstName, u.LastName, u.Password, u.Email, u.Birthday, a.Street, a.City, a.ZipCode, a.Country FROM User u INNER JOIN Address a ON u.AddressNr = a.ID";
            String sql = "SELECT w.ID, w.Text FROM WorkTogether w";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next())
            {
                Server server = new Server(rs.getInt("ID"), rs.getString("Text"));
                serverliste.add(server);
            }
        }
        catch (SQLException | IOException ex)
        {
            ex.printStackTrace();
        }
    }

    public int newWorkThogether()
    {
        try(Connection connection = DriverManager.getConnection(ChathubEnum.DatabasePath.getPath()))
        {
            String text = "";
            //Server server = new Server(id,port, text);
            String sqladdress = "INSERT INTO WorkTogether (Text) VALUES (?)";

            PreparedStatement preparedStatementAdress = connection.prepareStatement(sqladdress);
            preparedStatementAdress.setString(1, text);
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
            Server server = new Server(insertId, text);
            serverliste.add(server);

            return insertId;
        }
        catch (SQLException | IOException ex)
        {
            ex.printStackTrace();
        }

        return 0;
    }

    public void updateWorkThogetherDB()
    {
        for (int i = 0; i < serverliste.size(); i++)
        {
            try (Connection connection = DriverManager.getConnection(ChathubEnum.DatabasePath.getPath()))
            {
                String sqlUpdate = "UPDATE WorkTogether SET Text = ? WHERE id = ?";

                PreparedStatement preparedStatementUpdate = connection.prepareStatement(sqlUpdate);
                preparedStatementUpdate.setString(1, serverliste.get(i).getText());
                preparedStatementUpdate.setInt(2, serverliste.get(i).getID());

                int row = preparedStatementUpdate.executeUpdate();

                if (row > 0) {
                    System.out.println("Data updated successfully with ID " + serverliste.get(i).getID());
                } else {
                    System.out.println("Data update failed");
                }
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
    }

    public void autoUpdateServerDB()
    {
        System.out.println("Autosave start");
        Thread update = new Thread(() -> {
            while (updateRunning)
            {
                try
                {
                    Thread.sleep(updateInterval);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                updateWorkThogetherDB();
            }
        });
        update.start();
    }

    private int getPortByID(int id)
    {
        for (int i = 0; i < serverliste.size(); i++)
        {
            if (serverliste.get(i).getID() == id)
            {
                return serverliste.get(i).getPort();
            }
        }
        return 0;
    }

    public static void main(String[] args) throws IOException
    {
        new ServerController();
    }
}
