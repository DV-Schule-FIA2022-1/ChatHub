package chat.mains;

import chat.users.MainViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lombok.Getter;

import java.io.IOException;
import java.net.URL;

public class MainWindow extends Application
{
    @Getter
    private static Stage primaryStage;
    private Pane mainLayout;
    private static Scene scene;
    public static void main(String[] args)
    {
        launch();
    }
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        this.primaryStage = primaryStage;
        showMainView();
    }

    public void showMainView() throws IOException
    {
        URL fxmlLocation = MainViewController.class.getResource("/MainWindow.fxml");
        FXMLLoader loader = new FXMLLoader(fxmlLocation);
        mainLayout = loader.load();
        scene = new Scene(mainLayout);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Chathub MainWindow");
        primaryStage.show();
    }
}
