package chat.Notification;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;

public class Main extends Application
{
    private static Stage primaryStage;
    private Pane mainLayout;
    private static Scene scene;


    public static void main(String[] args) throws IOException
    {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws IOException
    {
        this.primaryStage = primaryStage;
        showMainView();
    }

    public void showMainView() throws IOException
    {
        URL fxmlLocation = NotificationController.class.getResource("/GUITestNotification.fxml");
        FXMLLoader loader = new FXMLLoader(fxmlLocation);
        mainLayout = loader.load();
        scene = new Scene(mainLayout);
        primaryStage.setScene(scene);
        primaryStage.setTitle("ClientGUI");
        primaryStage.setOnCloseRequest(e ->
        {
            primaryStage.close();
            System.out.println("Fenster geschlossen");
            System.exit(0);
        });
        primaryStage.show();
    }
}
