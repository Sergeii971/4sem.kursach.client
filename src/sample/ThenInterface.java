package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static sample.Check.checkMurk;
import static sample.Check.isNumeric;
import static sample.InformationWindows.lineCheckError;
import static sample.InformationWindows.variantNumberError;

public class ThenInterface {
    @FXML
    private TableView tableService;
    private ObservableList<Method> serviceObservableList = FXCollections.observableArrayList();
    @FXML
    private TableColumn columnService;
    @FXML
    private TableColumn column1;
    @FXML
    private TableColumn column2;
    @FXML
    private TableColumn column3;
    @FXML
    private TableColumn column4;
    @FXML
    private TableColumn column5;
    @FXML
    private TableColumn columnPrice;
    List<Method>services;
    private int number;
    @FXML
    public void initialize() throws IOException {
        services=new ArrayList();
        number=0;
        serviceSelected= FXCollections.observableArrayList();
        columnService.setCellValueFactory(new PropertyValueFactory<Method, String>("service"));
        column1.setCellValueFactory(new PropertyValueFactory<Method, String>("column1"));
        column2.setCellValueFactory(new PropertyValueFactory<Method, String>("column2"));
        column3.setCellValueFactory(new PropertyValueFactory<Method, String>("column3"));
        column4.setCellValueFactory(new PropertyValueFactory<Method, String>("column4"));
        column5.setCellValueFactory(new PropertyValueFactory<Method, String>("column5"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<Method, String>("price"));
    }
    @FXML
    private Button buttonChange;
    @FXML
    private Button buttonDelete;
    @FXML
    private TextField textFieldService;
    @FXML
    private TextField textField1;
    @FXML
    private TextField textField2;
    @FXML
    private TextField textField3;
    @FXML
    private TextField textField4;
    @FXML
    private TextField textField5;
    @FXML
    private TextField textFieldPrice;

    private ObservableList<Method>serviceSelected;
    public void change(ActionEvent actionEvent) {
        buttonChange.setOnAction(event -> {
            serviceSelected = tableService.getSelectionModel().getSelectedItems();
            if(serviceSelected.size()!=0) {
                textFieldService.appendText(serviceSelected.get(0).getService());
                textField1.appendText(serviceSelected.get(0).getColumn1());
                textField2.appendText(serviceSelected.get(0).getColumn2());
                textField3.appendText(serviceSelected.get(0).getColumn3());
                textField4.appendText(serviceSelected.get(0).getColumn4());
                textField5.appendText(serviceSelected.get(0).getColumn5());
                textFieldPrice.appendText(serviceSelected.get(0).getPrice());
            }});
    }
    @FXML
    private Button buttonSave;
    public void save(ActionEvent actionEvent) {
        buttonSave.setOnAction(event -> {
            if(serviceSelected.size()==0)
                if(checkMurk(textField1.getText())&&checkMurk(textField2.getText()) && checkMurk(textField3.getText()) &&checkMurk(textField4.getText()) &&checkMurk(textField5.getText())&&isNumeric(textFieldPrice.getText())) {
            serviceObservableList.add(new Method(textFieldService.getText(),textField1.getText(), textField2.getText(), textField3.getText(), textField4.getText(), textField5.getText(),textFieldPrice.getText()));
            services.add(new Method(textFieldService.getText(),textField1.getText(), textField2.getText(), textField3.getText(), textField4.getText(), textField5.getText(),textFieldPrice.getText()));
            number++;
            tableService.setItems(serviceObservableList);
                    textFieldClear();
                } else lineCheckError();
            if(checkMurk(textField1.getText())&&checkMurk(textField2.getText()) && checkMurk(textField3.getText()) &&checkMurk(textField4.getText()) &&checkMurk(textField5.getText())&&isNumeric(textFieldPrice.getText())) {
                ObservableList<Method> allService;
                allService = tableService.getItems();
                int i=0;
                for(Method service:allService){
                    if(service.equals(serviceSelected.get(0))){
                        services.remove(i);
                        services.add(i,new Method(textFieldService.getText(),textField1.getText(), textField2.getText(), textField3.getText(), textField4.getText(), textField5.getText(),textFieldPrice.getText()));
                        serviceObservableList.remove(i);
                        serviceObservableList.add(i,new Method(textFieldService.getText(),textField1.getText(), textField2.getText(), textField3.getText(), textField4.getText(), textField5.getText(),textFieldPrice.getText()));
                        tableService.setItems(serviceObservableList);
                        textFieldClear();
                        serviceSelected=FXCollections.observableArrayList();
                        break;
                    }
                    i++;
                }
            } });
    }
    private void textFieldClear(){
        textFieldService.clear();
        textField1.clear();
        textField2.clear();
        textField3.clear();
        textField4.clear();
        textField5.clear();
        textFieldPrice.clear();
    }
    public void deleteService(ActionEvent actionEvent) {
        buttonDelete.setOnAction(e -> { deleteButtonClicked(); });
    }
    private void deleteButtonClicked() {
        ObservableList<Method> serviceSelected, allService;
        allService = tableService.getItems();
        serviceSelected = tableService.getSelectionModel().getSelectedItems();
        int i=0;
        for(Method service:allService){
            if(service.equals(serviceSelected.get(0))){
                services.remove(i);
                number--;
                break;
            }
            i++;
        }
        serviceSelected.forEach(allService::remove);

    }
    @FXML
    private Button buttonShowResults;
    public void showResults(ActionEvent actionEvent) {
        buttonShowResults.setOnAction(event -> {
           if(number>1){
            try {
                Client admin=new Client();
                admin.sendMenuNumberServer(13);
                admin.sendMethodInformation(Integer.toString(services.size())+"\n");
                for(Method service:services) {
                    admin.sendMenuNumberServer(13);
                    admin.sendMethodInformation(service.getService()+"\n"+service.toString()+service.getPrice()+"\n");
                }
                admin.sendMenuNumberServer(10);
                ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else variantNumberError();
        });
    }
    @FXML
    private Button buttonExitThen;
    public void exitRespondApplication(ActionEvent actionEvent) {
        buttonExitThen.setOnAction(event -> {
            ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).close();
        });
    }

}
