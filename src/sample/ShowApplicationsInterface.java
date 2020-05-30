package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static sample.InformationWindows.noData;
import static sample.InformationWindows.successSaving;

public class ShowApplicationsInterface {
    private ObservableList<Application> applicationData = FXCollections.observableArrayList();
    private ObservableList<Application> applicationDataSearch = FXCollections.observableArrayList();
    @FXML
    private TableView<Application> applicationTable;
    @FXML
    private TableColumn<Application, String>columnName;
    @FXML
    private TableColumn<Application, String>columnSurname;
    @FXML
    private TableColumn<Application, String>columnYearRelease;
    @FXML
    private TableColumn<Application, String>columnCarBrand;
    @FXML
    private TableColumn<Application, String>columnCarModel;
    @FXML
    private TableColumn<Application, String>columnVinNumber;
    @FXML
    private TableColumn<Application, String>columnRegistrationNumber;
    @FXML
    public void initialize() throws IOException {
        initData();
        columnName.setCellValueFactory(new PropertyValueFactory<Application, String>("name"));
        columnSurname.setCellValueFactory(new PropertyValueFactory<Application, String>("surname"));
        columnYearRelease.setCellValueFactory(new PropertyValueFactory<Application, String>("yearRelease"));
        columnCarBrand.setCellValueFactory(new PropertyValueFactory<Application, String>("carBrand"));
        columnCarModel.setCellValueFactory(new PropertyValueFactory<Application, String>("carModel"));
        columnVinNumber.setCellValueFactory(new PropertyValueFactory<Application, String>("vinNumber"));
        columnRegistrationNumber.setCellValueFactory(new PropertyValueFactory<Application, String>("registrationNumber"));
        applicationTable.setItems(applicationData);
    }
    private List<Application> applications;
    private List<Application> applicationsSearch;
    private void initData() throws IOException {
        applications=new ArrayList();
        applicationsSearch=new ArrayList();
        Client admin=new Client();
        Application application;
        admin.sendMenuNumberServer(11);
        int k=admin.setApplicationNumberServer();
        if(k!=0) {
            for (int i = 0; i < k; i++) {
                application=admin.setApplicationData();
                applicationData.add(new Application(application.getName(),application.getSurname(),application.getYearRelease(),application.getCarBrand(),application.getCarModel(),application.getVinNumber(),application.getRegistrationNumber()));
                applications.add(new Application(application.getName(),application.getSurname(),application.getLogin(),application.getYearRelease(),application.getCarBrand(),application.getCarModel(),application.getVinNumber(),application.getRegistrationNumber()));
            }
            applicationTable.setItems(applicationData);
        }
        admin.sendMenuNumberServer(10);
    }
    @FXML
    private Button buttonRespondApplication;
    public int respondApplication(ActionEvent actionEvent) {
        buttonRespondApplication.setOnAction(event -> {
            ObservableList<Application> applicationSelected;
            applicationSelected = applicationTable.getSelectionModel().getSelectedItems();
            if(applicationSelected.size()!=0){
                int i=0;
            for(Application application:applications){
                if(application.getSurname().equals(applicationSelected.get(0).getSurname())&& application.getName().equals(applicationSelected.get(0).getName())) {
                    try {
                        respondApplicationInterface();
                        Client admin1=new Client();
                        admin1.sendMenuNumberServer(15);
                        if(admin1.getMethodInformationSize()!=1) {
                            admin1.sendMenuNumberServer(13);
                            admin1.sendMethodInformation(application.getLogin());
                            admin1.sendMenuNumberServer(10);
                            showResultInterface();
                            Client admin2=new Client();
                            admin2.sendMenuNumberServer(16);
                            admin2.sendMenuNumberServer(10);
                        }else
                            admin1.sendMenuNumberServer(10);
                        break;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }i++;
            }}});
        return 0;
    }
    private void showResultInterface() throws IOException {
        Stage newStage = new Stage();
        AnchorPane anchorPanePopup = (AnchorPane)  FXMLLoader.load(getClass().getResource("Fxml/result.fxml"));
        Scene scene = new Scene(anchorPanePopup);
        newStage.setScene(scene);
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.setTitle("");
        newStage.setMaxHeight(730);
        newStage.setMaxWidth(900);
        newStage.showAndWait();
    }
    private void respondApplicationInterface() throws IOException {
        Stage newStage = new Stage();
        AnchorPane anchorPanePopup = (AnchorPane)  FXMLLoader.load(getClass().getResource("Fxml/respondApplication.fxml"));
        Scene scene = new Scene(anchorPanePopup);
        newStage.setScene(scene);
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.setTitle("");
        newStage.setMaxHeight(730);
        newStage.setMaxWidth(900);
        newStage.showAndWait();
    }

    @FXML
    private Button buttonDeleteApplication;

    public void deleteApplication(ActionEvent actionEvent) {
        buttonDeleteApplication.setOnAction(e -> { deleteButtonClicked(); });
    }

    private int deleteButtonClicked() {
        ObservableList<Application> applicationSelected, allApplication;
        allApplication = applicationTable.getItems();
        if(allApplication.size()==0)return 0;
        applicationSelected = applicationTable.getSelectionModel().getSelectedItems();
            for (Application application : applications) {
                if(application.getRegistrationNumber().equals(applicationSelected.get(0).getRegistrationNumber())&&application.getVinNumber().equals(applicationSelected.get(0).getVinNumber())){
                    applications.remove(application);
                    break;
                }
            }
        applicationSelected.forEach(allApplication::remove);
        return 0;
    }
    @FXML
    private Button buttonSave;

    public void saveApplications(ActionEvent actionEvent) {
        buttonSave.setOnAction(event -> {
            try {
                saveApplicationInformation(actionEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void saveApplicationInformation(ActionEvent actionEvent) throws IOException {
        Client admin=new Client();
        admin.sendMenuNumberServer(12);
        admin.sendApplicationNumberServer(applications.size());
        for(Application application:applications) {
            admin.sendApplicationInformation(application);
        }
        ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).close();
        successSaving();
        admin.sendMenuNumberServer(10);
    }

    @FXML
    private TextField textFieldSearch;

    public void search(ActionEvent actionEvent) {
        applicationDataSearch.clear();
        applicationsSearch.clear();
        int i=0;
        for(Application application:applications){
        if(application.getSurname().equalsIgnoreCase(textFieldSearch.getText())){
            applicationDataSearch.add(new Application(application.getName(),application.getSurname(),application.getYearRelease(),application.getCarBrand(),application.getCarModel(),application.getVinNumber(),application.getRegistrationNumber()));
        applicationsSearch.add(new Application(application.getName(),application.getSurname(),application.getYearRelease(),application.getCarBrand(),application.getCarModel(),application.getVinNumber(),application.getRegistrationNumber()));
       applicationsSearch.get(i).setApplicationNumber(applications.indexOf(application));
       i++;
        }
        }
        if(applicationDataSearch.size()==0) noData();
        else {
            applicationTable.setItems(applicationDataSearch);
        }
        textFieldSearch.clear();
    }
    @FXML
    private Button buttonShowAllApplication;
    public void showAllApplication(ActionEvent actionEvent) {
        buttonShowAllApplication.setOnAction(e -> {
            applicationTable.setItems(applicationData);
        });
    }

    @FXML
    private Button buttonExitShowApplications;
    public void exitShowApplications(ActionEvent actionEvent) {
        buttonExitShowApplications.setOnAction(event -> {
            ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).close();
        });
    }

}
