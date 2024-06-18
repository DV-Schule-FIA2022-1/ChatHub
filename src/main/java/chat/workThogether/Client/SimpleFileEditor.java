package chat.workThogether.Client;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SimpleFileEditor extends Application
{
    int port = 1234;
    public static void Main(String[] args)
    {
        launch();
    }
    @Override
    public void start(Stage primaryStage) throws Exception
    {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/WorkTogether.fxml"));
//        Parent root = loader.load();
////        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("@img/EdenCodingIcon.png")));
//        primaryStage.setTitle("Work Thogether");
//        primaryStage.setScene(new Scene(root));
//        primaryStage.show();
//        EditorController editorController = loader.getController();
//        primaryStage.setOnCloseRequest(event -> {
//            editorController.getClient().closeClient();
//            System.out.println("Stage wird geschlossen");
//            //Nochmal bearbeiten, das der Thread richtig geschlossen wird
//        });
        creatTextEditor(port);
    }

    public void creatTextEditor(int port)
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/WorkTogether.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Work Thogether");
            stage.setScene(new Scene(fxmlLoader.load()));
            //stage.getIcons().add(new Image("file:src/main/Bilder/GameIcon.png"));
            EditorController editorController = fxmlLoader.getController();
            editorController.loadServerConection(port, stage);
            stage.setOnCloseRequest(e -> {
                stage.close();
                editorController.getClient().disconnectFromServer();
                System.out.println("Fenster geschlossen");
                //System.exit(0);
            });
            stage.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}