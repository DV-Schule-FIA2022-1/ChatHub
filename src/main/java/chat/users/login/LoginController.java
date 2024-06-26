package chat.users.login;

import chat.Chathub;
import chat.core.client.Client;
import chat.core.client.ClientController;
import chat.users.*;
import chat.view.MainViewController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class LoginController implements Initializable
{
    private AuthenticationController authenticationManager;
    @Getter
    private UserController userController;
    @Getter
    private static Stage stage;
    @Getter
    @FXML private TextField emailLoginField;
    @FXML private PasswordField passwordLoginField;
    @FXML private TextField firstNameTextfield;
    @FXML private TextField lastNameTextfield;
    @FXML private PasswordField passwordTextfield;
    @FXML private PasswordField passwordAgainTextfield;
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
    @FXML private DatePicker birthdateTextfield;
    private int counter;
    private Timer timer;
    @Setter
    private User activeUser;
    private CheckEmail checkEmailFunction;
    private MainViewController mainViewController;
    private ClientController clientController;
    @Getter
    private static Client newClient;
    private Chathub chathub;
    @Getter
    private User registeredUser;
    private LoginController loginController;

    public LoginController()
    {
        userController = new UserController();
        checkEmailFunction = new CheckEmail(this, userController);
        authenticationManager = new AuthenticationController(this, checkEmailFunction);
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

        chathub = new Chathub();
        this.loginController = this;
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
            if(checkEmailFunction.checkEmail(emailLoginField.getText()) == true)
            {
                passwordLoginLabel.setVisible(true);
                passwordLoginField.setVisible(true);
                btnLogin.setVisible(true);

                disableRegisterArea();
            }
            else
            {
                emailTextfield.setText(emailLoginField.getText());

                showRegisterArea();
            }
        }
        else
        {
            infoBox("Email must be included @", "Error Message");
        }
    }

    private void disableRegisterArea()
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
        btnRegistration.setVisible(false);
        registrationLabel.setVisible(false);
        birthdateLabel.setVisible(false);
        birthdateTextfield.setVisible(false);
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

    public String getDate()
    {
        LocalDate date = birthdateTextfield.getValue();
        String formattedDate = date.format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));
        System.out.println(formattedDate);
        return formattedDate;
    }

    public void createUser()
    {
        if(passwordTextfield.getText().equals(passwordAgainTextfield.getText()) && emailTextfield.getText().contains("@") && !checkEmailFunction.checkEmail(emailTextfield.getText()))
        {
            Address newAdress = new Address(streetTextfield.getText(), cityTextfield.getText(), zipCodeTextfield.getText(), countryTextfield.getText());
            User newUser = new User(firstNameTextfield.getText(), lastNameTextfield.getText(), passwordTextfield.getText(), emailTextfield.getText(), Date.valueOf(getDate()), newAdress);
            userController.addUser(newUser);

            loginUser(newUser, chathub);
        }
        else
        {
            if(!passwordTextfield.getText().equals(passwordAgainTextfield.getText()))
            {
                infoBox("Passwörter stimmen nicht überein", "Error Message");
            }
            else if(checkEmailFunction.checkEmail(emailTextfield.getText()))
            {
                infoBox("Email bereits Registert", "Error Message");
            }
            else
            {
                infoBox("Email must be included @", "Error Message");
            }
        }
    }

    public void loginUser(User registeredUser, Chathub chathub)
    {
        registeredUser.resetAttempts();
        mainViewController = new MainViewController(registeredUser, loginController);
        chathub.startServer(mainViewController);

        newClient = new Client(registeredUser, chathub.getServerport(), mainViewController);

        Chathub.getPrimaryStage().close();

        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(MainViewController.class.getResource("/view/MainWindow.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 630, 400);
            stage = new Stage();
            stage.setWidth(825);
            stage.setHeight(530);
            stage.setTitle("Chathub Main Window");
            stage.setScene(scene);
            stage.setOnCloseRequest(e -> System.exit(0));
            stage.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void confirmPassword()
    {
        if(authenticationManager.checkPassword(emailLoginField.getText(), passwordLoginField.getText()) == true)
        {
            loginUser(activeUser, chathub);
        }
    }



    public void disableLoginButton()
    {
        TimerTask timerTask = new TimerTask()
        {
            @Override
            public void run()
            {
                counter++;
            }
        };

        timer = new Timer("Timer");
        timer.scheduleAtFixedRate(timerTask, 0, 1000);

        Thread t = new Thread(() ->
        {
            while (true)
            {
                try
                {
                    btnLogin.setVisible(false);
                    if (counter == 120)
                    {
                        btnLogin.setVisible(true);
                        timer.cancel();
                        break;
                    }
                    Thread.sleep(1000);
                }
                catch (InterruptedException ex)
                {
                    ex.printStackTrace();
                }
            }
        });
        t.start();
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
