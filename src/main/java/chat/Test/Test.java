package chat.Test;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class Test extends Application
{

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();

        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10, 10, 10, 10));

        TextArea textArea = new TextArea();
        textArea.setPrefSize(400, 200);

        ImageView imageView = new ImageView(new Image("file:C:/Users/FAbdu/Downloads/java.png"));
        imageView.setFitWidth(200);
        imageView.setPreserveRatio(true);

        TextFlow textFlow = new TextFlow();
        textFlow.getChildren().addAll(new Text(textArea.getText()), imageView);

        textArea.textProperty().addListener((observable, oldValue, newValue) -> {
            textFlow.getChildren().clear();
            textFlow.getChildren().addAll(new Text(newValue), imageView);
        });

        vbox.getChildren().addAll(textArea, textFlow);
        root.setCenter(vbox);

        Scene scene = new Scene(root, 640, 480);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
