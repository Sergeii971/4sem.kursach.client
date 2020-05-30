package sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static sample.InformationWindows.*;

public class ChangeAdminDataInterface {
    @FXML
    private PasswordField oldPassword;
    @FXML
    private TextField oldLogin;
    @FXML
    private TextField newLogin;
    @FXML
    private PasswordField newPassword;
    @FXML
    private Button buttonOk;
    public void changeAdminData(ActionEvent actionEvent) {
        buttonOk.setOnAction(event -> {
            Client admin=new Client();
            if (oldLogin.getLength() == 0 || oldPassword.getLength() == 0 || newLogin.getLength() == 0 || newPassword.getLength() == 0) {
                lineNullError();
            }
            else {
                try {
                    admin.sendMenuNumberServer(1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (admin.checkLoginPasswordServer(oldLogin.getText(),oldPassword.getText())==1) {
                    inputNewLoginPassword(actionEvent);
                } else {
                    inputOldLoginPasswordError();
                }
            }
        });}

    private void inputNewLoginPassword(ActionEvent actionEvent) {
        try {
            Client admin=new Client();
            User user=new User();
            user.setLogin(newLogin.getText());
            user.setPassword(newPassword.getText());
            ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).close();
            admin.inputNewLoginPasswordServer(user);
            successSaving();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private Button buttonExitChangeAdminData;
    public void exitChangeAdminData(ActionEvent actionEvent) {
        buttonExitChangeAdminData.setOnAction(event -> {
            ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).close();
        });
    }
}
