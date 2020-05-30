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

public class AdminInterface {
    @FXML
    private Button buttonShowApplications;
    public void showApplications(ActionEvent actionEvent) {
        buttonShowApplications.setOnAction(event -> {
            try {
                ShowApplicationsInterface();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    private void ShowApplicationsInterface() throws IOException {
        Stage newStage = new Stage();
        AnchorPane anchorPanePopup = (AnchorPane)  FXMLLoader.load(getClass().getResource("Fxml/showApplications.fxml"));
        Scene scene = new Scene(anchorPanePopup);
        newStage.setScene(scene);
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.setTitle("");
        newStage.setMaxHeight(730);
        newStage.setMaxWidth(1100);
        newStage.showAndWait();
    }
    @FXML
private Button buttonAccount;
    public void accountsManaging(ActionEvent actionEvent) {
        buttonAccount.setOnAction(event -> {
            try {
                accountManagingInterface();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    private void accountManagingInterface() throws IOException {
        Stage newStage = new Stage();
        AnchorPane anchorPanePopup = (AnchorPane)  FXMLLoader.load(getClass().getResource("Fxml/accountsManaging.fxml"));
        Scene scene = new Scene(anchorPanePopup);
        newStage.setScene(scene);
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.setTitle("");
        newStage.setMaxHeight(530);
        newStage.setMaxWidth(700);
        newStage.showAndWait();
    }
@FXML
private Button buttonExitAdmin;
    public void exitAdmin(ActionEvent actionEvent) {
        buttonExitAdmin.setOnAction(event -> {
            ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).close();
        });

    }
}
