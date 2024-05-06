package chat.Users;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable
{
    @FXML private TextField passwordLoginField;
    @FXML private TextField firstNameTextfield;
    @FXML private TextField lastNameTextfield;
    @FXML private TextField passwordTextfield;
    @FXML private TextField passwordAgainTextfield;
    @FXML private TextField streetTextfield;
    @FXML private TextField cityTextfield;
    @FXML private TextField zipCodeTextfield;
    @FXML private TextField countryTextfield;
    @FXML private TextField emailTextfield;
    @FXML private Label passwordLoginLabel;
    @FXML private Label registrationLabel;
    @FXML private Label firstNameLabel;
    @FXML private Label lastNameLabel;
    @FXML private Label passwordLabel;
    @FXML private Label passwordAgainLabel;
    @FXML private Label streetLabel;
    @FXML private Label cityLabel;
    @FXML private Label countryLabel;
    @FXML private Label zipCodeLabel;
    @FXML private Label emailLabel;
    @FXML private Button btnLogin;
    @FXML private Button btnRegistration;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        firstNameTextfield.setVisible(false);
        lastNameTextfield.setVisible(false);
        passwordTextfield.setVisible(false);
        passwordAgainTextfield.setVisible(false);
        streetTextfield.setVisible(false);
        cityTextfield.setVisible(false);
        zipCodeTextfield.setVisible(false);
        countryTextfield.setVisible(false);
        emailTextfield.setVisible(false);
        firstNameLabel.setVisible(false);
        lastNameLabel.setVisible(false);
        passwordLabel.setVisible(false);
        passwordAgainLabel.setVisible(false);
        streetLabel.setVisible(false);
        cityLabel.setVisible(false);
        countryLabel.setVisible(false);
        zipCodeLabel.setVisible(false);
        emailLabel.setVisible(false);
        passwordLoginField.setVisible(false);
        passwordLoginLabel.setVisible(false);
        btnRegistration.setVisible(false);
        btnLogin.setVisible(false);
        registrationLabel.setVisible(false);
    }
}
