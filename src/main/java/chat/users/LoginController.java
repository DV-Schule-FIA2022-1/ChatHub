package chat.users;

import chat.users.AuthenticationController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import lombok.Getter;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class LoginController implements Initializable
{
    private AuthenticationController authenticationManager;
    @Getter
    @FXML private TextField emailLoginField;
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
    @FXML private Label birthdateLabel;
    @FXML private TextField birthdateTextfield;

    public LoginController()
    {
        authenticationManager = new AuthenticationController();
    }

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
        birthdateLabel.setVisible(false);
        birthdateTextfield.setVisible(false);
    }

    @FXML
    public void enterPressed(KeyEvent event)
    {
        if(event.getCode() == event.getCode().ENTER)
        {
            confirmEmailaddress();
        }
    }

    public void confirmEmailaddress()
    {
        if(emailLoginField.getText().contains("@"))
        {
            if(authenticationManager.checkEmail() == true)
            {
                passwordLoginLabel.setVisible(true);
                passwordLoginField.setVisible(true);
                btnLogin.setVisible(true);

                //confirmPassword();
            }
            else
            {
                showRegisterArea();
            }
        }
        else
        {
            LoginController.infoBox("Email must be included @", "Error Message");
        }
    }

    private void showRegisterArea()
    {
        firstNameTextfield.setVisible(true);
        lastNameTextfield.setVisible(true);
        passwordTextfield.setVisible(true);
        passwordAgainTextfield.setVisible(true);
        streetTextfield.setVisible(true);
        cityTextfield.setVisible(true);
        zipCodeTextfield.setVisible(true);
        countryTextfield.setVisible(true);
        emailTextfield.setVisible(true);
        firstNameLabel.setVisible(true);
        lastNameLabel.setVisible(true);
        passwordLabel.setVisible(true);
        passwordAgainLabel.setVisible(true);
        streetLabel.setVisible(true);
        cityLabel.setVisible(true);
        countryLabel.setVisible(true);
        zipCodeLabel.setVisible(true);
        emailLabel.setVisible(true);
        btnRegistration.setVisible(true);
        registrationLabel.setVisible(true);
        birthdateLabel.setVisible(true);
        birthdateTextfield.setVisible(true);
    }

    public void createUser()
    {
        Address newAdress = new Address(streetTextfield.getText(), cityTextfield.getText(), zipCodeTextfield.getText(), countryTextfield.getText());
        User newUser = new User(firstNameTextfield.getText(), lastNameTextfield.getText(), passwordTextfield.getText(), emailTextfield.getText(), java.sql.Date.valueOf(birthdateTextfield.getText()), newAdress);
    }

    public void loginUser()
    {

    }

    public void confirmPassword()
    {
        if(authenticationManager.checkPassword() == true)
        {
            //Zeige User Profile an
        }
    }

    public static void infoBox(String infoMessage, String titleBar)
    {
        infoBox(infoMessage, titleBar, null);
    }

    public static void infoBox(String infoMessage, String titleBar, String headerMessage)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.setContentText(infoMessage);
        alert.showAndWait();
    }
}
