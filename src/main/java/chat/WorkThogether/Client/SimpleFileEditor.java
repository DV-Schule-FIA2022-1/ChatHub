package chat.WorkThogether.Client;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SimpleFileEditor extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        int port = 123;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/WorkTogether.fxml"));
        Parent root = loader.load();
//        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("@img/EdenCodingIcon.png")));
        primaryStage.setTitle("Work Thogether");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        EditorController editorController = loader.getController();
        primaryStage.setOnCloseRequest(event -> {
            editorController.getClient().closeClient();
            System.out.println("Stage wird geschlossen");
            //Nochmal bearbeiten, das der Thread richtig geschlossen wird
        });
    }
}