package chat;

import chat.server.Server;
import chat.server.ServerController;
import chat.server.SocketManager;
import chat.users.login.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lombok.Getter;

import java.io.IOException;
import java.net.URL;

public class Chathub extends Application
{
    @Getter
    private static Chathub instance;
    @Getter
    private int serverport = 40000;
    private Server server;
    private ServerController serverController;
    private Thread serverThread;
    @Getter
    private static Stage primaryStage;
    private Pane mainLayout;
    private static Scene scene;
    @Getter
    private LoginController loginController;
    private SocketManager socketManager;
    public static void main(String[] args)
    {
        instance = new Chathub();
        instance.startServer();
        instance.testData();

        launch();
    }

    public void startServer()
    {
        serverController = new ServerController();
        socketManager = new SocketManager();

        serverThread = new Thread(() -> server = Server.getInstance(socketManager, serverController, serverport), "Server-Thread");
        serverThread.start();

        System.out.println("Server läuft auf localhost/" + serverport);
    }

    public void testData()
    {
        //LocalDate date = LocalDate.of(2024, 6, 3);
        //UserController test = new UserController();
        //User usertest = new User("Test", "Test2", "1234", "werwe@test.de", Date.valueOf(date), new Address("teststraße", "Würzburg", "97082", "Germany"));
        //test.addUser(usertest);
        //test.readUser();

        //Chatroom defaultroom = new Chatroom("Default", "Default Roomn", "1234", LocalDateTime.now(), 20, true);
        //ChatroomController chatroomController = new ChatroomController(defaultroom);
        //chatroomController.createChatroom(defaultroom);
    }

    public void start(Stage primaryStage) throws Exception
    {
        this.primaryStage = primaryStage;
        loginController = new LoginController();
        showMainView();
    }

    public void showMainView() throws IOException
    {
        URL fxmlLocation = LoginController.class.getResource("/LoginScreen.fxml");
        FXMLLoader loader = new FXMLLoader(fxmlLocation);
        mainLayout = loader.load();
        scene = new Scene(mainLayout);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Login");
        primaryStage.setOnCloseRequest(e -> System.exit(0));
        primaryStage.show();
    }
}
