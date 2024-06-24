package chat;

import chat.chatBot.Main;
import chat.server.Server;
import chat.server.ServerController;
import chat.server.SocketManager;
import chat.users.login.LoginController;
import chat.users.permission.Permission;
import chat.users.role.Role;
import chat.users.role.RoleController;
import chat.view.MainViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lombok.Getter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Set;

public class Chathub extends Application
{
    @Getter
    private static Chathub instance;
    @Getter
    private int serverport = 40000;
    @Getter
    private Server server;
    private MainViewController mainViewController;
    private Thread serverThread;
    @Getter
    private static Stage primaryStage;
    private Pane mainLayout;
    private static Scene scene;
    @Getter
    private static LoginController loginController;
    private SocketManager socketManager;

    public static void main(String[] args)
    {
        instance = new Chathub();
        loginController = new LoginController();
        instance.testData();

        launch();
    }

    public void startServer(MainViewController mainViewController)
    {
        socketManager = new SocketManager();

        serverThread = new Thread(() -> server = Server.getInstance(socketManager, mainViewController, serverport), "Server-Thread");
        serverThread.start();

        System.out.println("Server läuft auf localhost/" + serverport);
    }

    public void testData()
    {
        //insertRoles();
        //LocalDate date = LocalDate.of(2024, 6, 3);
        //UserController test = new UserController();
        //User usertest = new User("Test", "Test2", "1234", "werwe@test.de", Date.valueOf(date), new Address("teststraße", "Würzburg", "97082", "Germany"));
        //test.addUser(usertest);
        //test.readUser();

        //Chatroom defaultroom = new Chatroom("Default", "Default Roomn", "1234", LocalDateTime.now(), 20, true);
        //ChatroomController chatroomController = new ChatroomController(defaultroom);
        //chatroomController.createChatroom(defaultroom);
    }

    public void insertRoles()
    {
        Role admin = new Role("Admin", "Administrator role with full permissions", 1, Set.of(Permission.BAN_USER, Permission.EDIT_USER, Permission.DELETE_USER, Permission.EDIT_PROFILE) ,LocalDateTime.now(), LocalDateTime.now(), true);
        Role moderator = new Role("Moderator", "Moderator role with limited permissions", 2, Set.of(Permission.EDIT_MESSAGE, Permission.DELETE_MESSAGE, Permission.MODERATE_MESSAGE, Permission.ADD_USER, Permission.DELETE_CHATROOM, Permission.EDIT_CHATROOM, Permission.WARN_USER), LocalDateTime.now(), LocalDateTime.now(), true);
        Role user = new Role("User", "Regular user role with basic permissions", 3, Set.of(Permission.SEND_MESSAGE, Permission.DOWNLOAD_FILE, Permission.RECEIVE_MESSAGE, Permission.UPLOAD_FILE, Permission.ENTER_CHATROOM, Permission.CREATE_CHATROOM, Permission.LEAVE_CHATROOM, Permission.VIEW_PROFILE), LocalDateTime.now(), LocalDateTime.now(), true);

        RoleController roleController = new RoleController();

        roleController.createRole(admin);
        roleController.createRole(moderator);
        roleController.createRole(user);
    }

    public void start(Stage primaryStage) throws Exception
    {
        this.primaryStage = primaryStage;
        showMainView();
    }

    public void showMainView() throws IOException
    {
        URL fxmlLocation = LoginController.class.getResource("/view/LoginScreen.fxml");
        FXMLLoader loader = new FXMLLoader(fxmlLocation);
        mainLayout = loader.load();
        scene = new Scene(mainLayout);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Login");
        primaryStage.setOnCloseRequest(e -> System.exit(0));
        primaryStage.show();
    }
}
