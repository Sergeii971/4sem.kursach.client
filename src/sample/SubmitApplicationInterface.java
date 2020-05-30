package sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import static sample.Check.checkYear;
import static sample.Check.checkLine;
import static sample.InformationWindows.*;
public class SubmitApplicationInterface {
    @FXML
    private Button buttonSave;
    @FXML
    private TextField textFieldName;
    @FXML
    private TextField textFieldSurname;
    @FXML
    private TextField textFieldBrand;
    @FXML
    private TextField textFieldYearRelease;
    @FXML
    private TextField textFieldModel;
    @FXML
    private TextField textFieldVINNumber;
    @FXML
    private TextField textFieldRegistrationNumber;
    public void saveSubmit(ActionEvent actionEvent) {
        buttonSave.setOnAction(event -> {
            Client client=new Client();
            if (textFieldName.getLength() == 0 || textFieldSurname.getLength() == 0 || textFieldBrand.getLength() == 0 || textFieldModel.getLength() == 0 || textFieldYearRelease.getLength()==0 || textFieldVINNumber.getLength()==0 || textFieldRegistrationNumber.getLength()==0) {
                lineNullError();
            }
            else {
                        if (checkLine(textFieldName.getText()) && checkLine(textFieldSurname.getText()) && checkYear(textFieldYearRelease.getText())) {
                            try {
                                Application application=getApplication();
                                client.sendMenuNumberServer(9);
                                client.sendApplicationInformation(application);
                                client.sendMenuNumberServer(10);
                                ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).close();
                                successSaving();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        else lineCheckError();
            }
            });
    }
private Application getApplication(){
        Application application=new Application();
    application.setName(textFieldName.getText());
    application.setSurname(textFieldSurname.getText());
    application.setYearRelease(textFieldYearRelease.getText());
    application.setCarModel(textFieldModel.getText());
    application.setCarBrand(textFieldBrand.getText());
    application.setRegistrationNumber(textFieldRegistrationNumber.getText());
    application.setVinNumber(textFieldVINNumber.getText());
    return application;
}
    @FXML
    private Button buttonExitSubmitApplication;
    public void exitSubmitApplication(ActionEvent actionEvent) {
        buttonExitSubmitApplication.setOnAction(event -> {
            ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).close();
        });
    }


}
