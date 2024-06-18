package chat.workThogether.TextEditor;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class SimpleFileEditor extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SimpleFileEditor.fxml"));
        Parent root = loader.load();
        //primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("Picture/EdenCodingIcon.png")));
        primaryStage.setTitle("Simple EdenCoding JavaFX File Editor");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}