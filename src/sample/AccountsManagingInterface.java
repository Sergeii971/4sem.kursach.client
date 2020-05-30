package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

import static sample.InformationWindows.noData;

public class AccountsManagingInterface {
    @FXML
    private Button buttonShowUsers;
    public void showUsers(ActionEvent actionEvent) {
        buttonShowUsers.setOnAction(event -> {
            try {
                showUsersInterface();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    private int showUsersInterface() throws IOException {
        Client admin=new Client();
        admin.sendMenuNumberServer(7);
        User user=admin.setUserNumberServer();
        admin.sendMenuNumberServer(10);
        if(user.getUserNumber()==0){
            noData();
            return 0;
        }
        Stage newStage = new Stage();
        AnchorPane anchorPanePopup = (AnchorPane)  FXMLLoader.load(getClass().getResource("Fxml/showUsers.fxml"));
        Scene scene = new Scene(anchorPanePopup);
        newStage.setScene(scene);
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.setTitle("");
        newStage.setMaxHeight(730);
        newStage.setMaxWidth(900);
        newStage.showAndWait();
        return 0;
    }
    @FXML
    private Button buttonChangeAdminData;
    public void changeAdminData(ActionEvent actionEvent) {
        buttonChangeAdminData.setOnAction(event -> {
            try {
                changeAdminDataInterface();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    private void changeAdminDataInterface() throws IOException {
        Stage newStage = new Stage();
        AnchorPane anchorPanePopup = (AnchorPane)  FXMLLoader.load(getClass().getResource("Fxml/changeAdminData.fxml"));
        Scene scene = new Scene(anchorPanePopup);
        newStage.setScene(scene);
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.setTitle("Управление учетными записями");
        newStage.setMaxHeight(730);
        newStage.setMaxWidth(900);
        newStage.showAndWait();
    }
@FXML
private Button buttonExitAccountsManaging;
    public void exitAccountsManaging(ActionEvent actionEvent) {
        buttonExitAccountsManaging.setOnAction(event -> {
            ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).close();
        });
    }


}
