package chat.mains;

import chat.users.login.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lombok.Getter;
import java.io.IOException;
import java.net.URL;

public class MainLogin extends Application
{
    @Getter
    private static Stage primaryStage;
    private Pane mainLayout;
    private static Scene scene;
    @Getter
    private LoginController loginController;
    public static void main(String[] args)
    {
        launch();
    }
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        this.primaryStage = primaryStage;
        loginController = new LoginController();
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
        primaryStage.show();
    }
}
