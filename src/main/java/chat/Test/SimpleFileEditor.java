package chat.Test;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class SimpleFileEditor extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/WorkTogether.fxml"));
        Parent root = loader.load();
//        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("@img/EdenCodingIcon.png")));
        primaryStage.setTitle("Work Thogether");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}