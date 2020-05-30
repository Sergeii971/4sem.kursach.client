package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

import static sample.Check.checkLine;
import static sample.InformationWindows.*;

public class RegistrationInterface {
    @FXML
private TextField textFieldName;
    @FXML
    private TextField textFieldSurname;
    @FXML
    private TextField textFieldLogin;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button buttonSave;
    public void registration(ActionEvent actionEvent) {
        buttonSave.setOnAction(event -> {
            Client user=new Client();
            if (textFieldName.getLength() == 0 || textFieldSurname.getLength() == 0 || textFieldLogin.getLength() == 0 || passwordField.getLength() == 0) {
                lineNullError();
            }
            else {
                try {

                    user.sendMenuNumberServer(8);
                    if(user.checkLoginRepeat(textFieldLogin.getText())!=0) {
                        if (checkLine(textFieldName.getText()) && checkLine(textFieldSurname.getText()))
                              saveUserInformation(actionEvent);
                        else lineCheckError();
                    }
                    else  loginRepeatError();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }});
    }

   @FXML
   private Button buttonExitRegistration;
    public void exitRegistration(ActionEvent actionEvent) {
        buttonExitRegistration.setOnAction(event -> {
            ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).close();
        });
    }

private void saveUserInformation(ActionEvent actionEvent) throws IOException {
        Client admin=new Client();
        User user=new User();
        admin.sendMenuNumberServer(4);
        user.setName(textFieldName.getText());
        user.setSurname(textFieldSurname.getText());
        user.setLogin(textFieldLogin.getText());
        user.setPassword(passwordField.getText());
        admin.sendUserInformation(user);
        ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).close();
        successSaving();
        admin.sendMenuNumberServer(10);
}

}
