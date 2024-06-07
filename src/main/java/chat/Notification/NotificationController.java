package chat.Notification;


import javafx.beans.property.ListProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;


import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;


import static javafx.application.Application.launch;

public class NotificationController implements Initializable
{
    @FXML private TextField textboxMessage;


    @FXML private ListView listboxReceived;

    private ArrayList<String> messagelist;

    private ListProperty<String> listProperty = new SimpleListProperty<>();

    public static Stage secondStage;
    private Pane secondLayout;
    private static Scene scene;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

        messagelist = new ArrayList<>();
        messagelist.add("hi");
        messagelist.add("hi2");
        System.out.println(messagelist);

        listProperty.set(FXCollections.observableArrayList(messagelist));
        listboxReceived.itemsProperty().bind(listProperty);
    }
    public void sendMessage() throws IOException
    {
        secondStage = new Stage();
        messagelist.add(textboxMessage.getText());
        listProperty.set(FXCollections.observableArrayList(messagelist));

        //neuer Controller
        URL fxmlLocation = NotificationController.class.getResource("/NotificationPopUp.fxml");
        FXMLLoader loader = new FXMLLoader(fxmlLocation);
        secondLayout = loader.load();
        scene = new Scene(secondLayout);
        secondStage.setScene(scene);
        secondStage.setTitle("PopUp");
        secondStage.setOnCloseRequest(e ->
        {
            secondStage.close();
            System.out.println("Fenster geschlossen");
            System.exit(0);
        });
        secondStage.show();

    }
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException
    {
        sendMessage();
    }
    public TextField getTextboxMessage()
    {
        return textboxMessage;
    }

    public ListView getListboxReceived()
    {
        return listboxReceived;
    }
    public ArrayList<String> getMessagelist()
    {
        return messagelist;
    }

}
