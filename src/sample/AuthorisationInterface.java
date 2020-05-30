package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;

import static sample.InformationWindows.authorizationError;

public class AuthorisationInterface {
    public static final String Answers ="D:\\Answers";
    @FXML
    private Button registrationButton;
    @FXML
    private TextField textFieldLogin;
    @FXML
    private PasswordField passwordField;
    public int authorization(ActionEvent actionEvent) {
         Client admin=new Client();
        try {
            admin.sendMenuNumberServer(1);
            if(admin.checkLoginPasswordServer(textFieldLogin.getText(), passwordField.getText())==1) {
                admin.sendMenuNumberServer(10);
            adminInterface();
            textFieldLogin.clear();
            passwordField.clear();
              return 0;
        }
            admin.sendMenuNumberServer(2);
            if(admin.checkLoginPasswordServer(textFieldLogin.getText(), passwordField.getText())==2){
                admin.sendMenuNumberServer(10);
                FileWriter fileWriter=new FileWriter(Answers);
                fileWriter.write(textFieldLogin.getText());
                fileWriter.close();
                userInterface();
                textFieldLogin.clear();
                passwordField.clear();
                return 0;
            }
            else {authorizationError();admin.sendMenuNumberServer(10);}
        } catch (IOException e) {
            e.printStackTrace();
        }
return 0;
    }

    public void registration(ActionEvent actionEvent) {
        registrationButton.setOnAction(event -> {
            try {
                registrationInterface();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    private void registrationInterface() throws IOException {
        Stage newStage = new Stage();
        AnchorPane anchorPanePopup = (AnchorPane)  FXMLLoader.load(getClass().getResource("Fxml/registration.fxml"));
        Scene scene = new Scene(anchorPanePopup);
        newStage.setScene(scene);
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.setTitle("Регистрация");
        newStage.setMaxHeight(730);
        newStage.setMaxWidth(900);
        newStage.showAndWait();
    }
    private  void adminInterface() throws IOException {
        Stage newStage = new Stage();
        AnchorPane anchorPanePopup = (AnchorPane)  FXMLLoader.load(getClass().getResource("Fxml/admin.fxml"));
        Scene scene = new Scene(anchorPanePopup);
        newStage.setScene(scene);
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.setTitle("");
        newStage.setMaxHeight(730);
        newStage.setMaxWidth(900);
        newStage.showAndWait();
    }
    public  void userInterface() throws IOException {
        Stage newStage = new Stage();
        AnchorPane anchorPanePopup = (AnchorPane)  FXMLLoader.load(getClass().getResource("Fxml/userInterface.fxml"));
        Scene scene = new Scene(anchorPanePopup);
        newStage.setScene(scene);
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.setTitle("");
        newStage.setMaxHeight(730);
        newStage.setMaxWidth(900);
        newStage.showAndWait();
    }
}
