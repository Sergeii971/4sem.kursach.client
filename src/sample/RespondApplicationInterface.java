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

import static sample.Check.checkMurk;
import static sample.InformationWindows.*;

public class RespondApplicationInterface{
    private ObservableList<Method> experts = FXCollections.observableArrayList();
    @FXML
    private TableView<Method> tableExpert;
    public TableColumn<Method,String> columnName;
    public TableColumn<Method,String> column1;
    public TableColumn<Method,String> column2;
    public TableColumn<Method,String> column3;
    public TableColumn<Method,String> column4;
    public TableColumn<Method,String> column5;

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
    public void initialize() throws IOException {
        number=1;
        expertSelected= FXCollections.observableArrayList();
        flag=0;
        expertMurks =new ArrayList();
        columnName.setCellValueFactory(new PropertyValueFactory<Method, String>("name"));
        column1.setCellValueFactory(new PropertyValueFactory<Method, String>("column1"));
        column2.setCellValueFactory(new PropertyValueFactory<Method, String>("column2"));
        column3.setCellValueFactory(new PropertyValueFactory<Method, String>("column3"));
        column4.setCellValueFactory(new PropertyValueFactory<Method, String>("column4"));
        column5.setCellValueFactory(new PropertyValueFactory<Method, String>("column5"));
    }
    private int number;
    List<Method> expertMurks;
    int flag;
    @FXML
    private Button buttonAdd;
    public void add(ActionEvent actionEvent) {
        buttonAdd.setOnAction(event -> {
            if(flag==0) {
                switch (number) {
                    case 1:
                        if (textField1.getLength() == 0) {
                            if (checkMurk(textField2.getText()) && checkMurk(textField3.getText()) && checkMurk(textField4.getText()) && checkMurk(textField5.getText())) {
                                experts.add(new Method("Э1", "--------------------", textField2.getText(), textField3.getText(), textField4.getText(), textField5.getText()));
                                expertMurks.add(new Method("--------------------", textField2.getText(), textField3.getText(), textField4.getText(), textField5.getText()));
                                number++;
                                tableExpert.setItems(experts);
                                textFieldClear();
                            } else lineCheckError();
                        } else expertsError();
                        break;
                    case 2:
                        if (textField2.getLength() == 0) {
                            if (checkMurk(textField1.getText()) && checkMurk(textField3.getText()) && checkMurk(textField4.getText()) && checkMurk(textField5.getText())) {
                                experts.add(new Method("Э2", textField1.getText(), "--------------------", textField3.getText(), textField4.getText(), textField5.getText()));
                                expertMurks.add(new Method(textField1.getText(), "--------------------", textField3.getText(), textField4.getText(), textField5.getText()));
                                number++;
                                tableExpert.setItems(experts);
                                textFieldClear();
                            } else lineCheckError();
                        } else expertsError();
                        break;
                    case 3:
                        if (textField3.getLength() == 0) {
                            if (checkMurk(textField1.getText()) && checkMurk(textField2.getText()) && checkMurk(textField4.getText()) && checkMurk(textField5.getText())) {
                                experts.add(new Method("Э3", textField1.getText(), textField2.getText(), "--------------------", textField4.getText(), textField5.getText()));
                                expertMurks.add(new Method(textField1.getText(), textField2.getText(), "--------------------", textField4.getText(), textField5.getText()));
                                number++;
                                tableExpert.setItems(experts);
                                textFieldClear();
                            } else lineCheckError();
                        } else expertsError();
                        break;
                    case 4:
                        if (textField4.getLength() == 0) {
                            if (checkMurk(textField1.getText()) && checkMurk(textField2.getText()) && checkMurk(textField3.getText()) && checkMurk(textField5.getText())) {
                                experts.add(new Method("Э4", textField1.getText(), textField2.getText(), textField3.getText(), "--------------------", textField5.getText()));
                                expertMurks.add(new Method(textField1.getText(), textField2.getText(), textField3.getText(), "--------------------", textField5.getText()));
                                number++;
                                tableExpert.setItems(experts);
                                textFieldClear();
                            } else lineCheckError();
                        } else expertsError();
                        break;
                    case 5:
                        if (textField5.getLength() == 0) {
                            if (checkMurk(textField1.getText()) && checkMurk(textField2.getText()) && checkMurk(textField3.getText()) && checkMurk(textField4.getText())) {
                                experts.add(new Method("Э5", textField1.getText(), textField2.getText(), textField3.getText(), textField4.getText(), "--------------------"));
                                expertMurks.add(new Method(textField1.getText(), textField2.getText(), textField3.getText(), textField4.getText(), "--------------------"));
                                number++;
                                tableExpert.setItems(experts);
                                textFieldClear();
                            } else lineCheckError();
                        } else expertsError();
                        break;
                    default:
                        expertsNumberError();
                        break;
                }
            }
            else
            saveChanging();
        });
    }
    private void saveChanging(){
        int number1=Character.digit(expertSelected.get(0).getName().charAt(1),10);
        if(!checkChangeData(number1))  expertsNumberError();
        else {
            if (textField1.getLength() == 0) {
                experts.remove(number1 - 1);
                experts.add(number1 - 1, new Method("Э1", "--------------------", textField2.getText(), textField3.getText(), textField4.getText(), textField5.getText()));
                expertMurks.remove(number1 - 1);
                expertMurks.add(number1 - 1, new Method("--------------------", textField2.getText(), textField3.getText(), textField4.getText(), textField5.getText()));
            }
            if (textField2.getLength() == 0) {
                experts.remove(number1 - 1);
                experts.add(number1 - 1, new Method("Э2", textField1.getText(), "--------------------", textField3.getText(), textField4.getText(), textField5.getText()));
                expertMurks.remove(number1 - 1);
                expertMurks.add(number1 - 1, new Method(textField1.getText(), "--------------------", textField3.getText(), textField4.getText(), textField5.getText()));
            }
            if (textField3.getLength() == 0) {
                experts.remove(number1 - 1);
                experts.add(number1 - 1, new Method("Э3", textField1.getText(), textField2.getText(), "--------------------", textField4.getText(), textField5.getText()));
                expertMurks.remove(number1 - 1);
                expertMurks.add(number1 - 1, new Method(textField1.getText(), textField2.getText(), "--------------------", textField4.getText(), textField5.getText()));
            }
            if (textField4.getLength() == 0) {
                experts.remove(number1 - 1);
                experts.add(number1 - 1, new Method("Э4", textField1.getText(), textField2.getText(), textField3.getText(), "--------------------", textField5.getText()));
                expertMurks.remove(number1 - 1);
                expertMurks.add(number1 - 1, new Method(textField1.getText(), textField2.getText(), textField3.getText(), "--------------------", textField5.getText()));
            }
            if (textField5.getLength() == 0) {
                experts.remove(number1 - 1);
                experts.add(number1 - 1, new Method("Э5", textField1.getText(), textField2.getText(), textField3.getText(), textField4.getText(), "--------------------"));
                expertMurks.remove(number1 - 1);
                expertMurks.add(number1 - 1, new Method(textField1.getText(), textField2.getText(), textField3.getText(), textField4.getText(), "--------------------"));
            }
            tableExpert.setItems(experts);
            textFieldClear();
            flag=0;
            expertSelected=FXCollections.observableArrayList();
        }
    }
    private void textFieldClear(){
        textField1.clear();
        textField2.clear();
        textField3.clear();
        textField4.clear();
        textField5.clear();
    }
    @FXML
    private Button buttonThen;
    public void then(ActionEvent actionEvent) {
        buttonThen.setOnAction(event -> {
            if(number>5){
            try {
                for(Method expert:expertMurks){
                    if(expert.getColumn1()=="--------------------")expert.setColumn1("0");
                    if(expert.getColumn2()=="--------------------")expert.setColumn2("0");
                    if(expert.getColumn3()=="--------------------")expert.setColumn3("0");
                    if(expert.getColumn4()=="--------------------")expert.setColumn4("0");
                    if(expert.getColumn5()=="--------------------")expert.setColumn5("0");
                }
                thenInterface();
                Client admin=new Client();
                admin.sendMenuNumberServer(15);
                if(admin.getMethodInformationSize()!=1)
                    for (Method expert : expertMurks) {
                        Thread.sleep(100);
                        admin.sendMenuNumberServer(13);
                        admin.sendMethodInformation(expert.toString());
                    }
                admin.sendMenuNumberServer(10);
                ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).close();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
            }
            else lineNullError();
        });
    }

    @FXML
    private Button buttonChange;
    private ObservableList<Method>expertSelected;
    public void change(ActionEvent actionEvent) {
        buttonChange.setOnAction(event -> {
            expertSelected = tableExpert.getSelectionModel().getSelectedItems();
            if(expertSelected.size()!=0) {
                flag=1;
                textField1.appendText(expertSelected.get(0).getColumn1());
                textField2.appendText(expertSelected.get(0).getColumn2());
                textField3.appendText(expertSelected.get(0).getColumn3());
                textField4.appendText(expertSelected.get(0).getColumn4());
                textField5.appendText(expertSelected.get(0).getColumn5());
                if (textField1.getText().equals("--------------------")) textField1.clear();
                if (textField2.getText().equals("--------------------")) textField2.clear();
                if (textField3.getText().equals("--------------------")) textField3.clear();
                if (textField4.getText().equals("--------------------")) textField4.clear();
                if (textField5.getText().equals("--------------------")) textField5.clear();
            }});
    }
    private boolean checkChangeData(int number1){

        switch (number1) {
            case 1:
                if (textField1.getLength() == 0) {
                    if (checkMurk(textField2.getText()) && checkMurk(textField3.getText()) && checkMurk(textField4.getText()) && checkMurk(textField5.getText())) {
                        return true;
                    }else {
                        lineCheckError();
                        return false; }
                } else {
                    expertsError();
                    return false; }
            case 2:
                if (textField2.getLength() == 0) {
                    if (checkMurk(textField1.getText()) && checkMurk(textField3.getText()) && checkMurk(textField4.getText()) && checkMurk(textField5.getText())) {
                        return true;
                    } else {
                        lineCheckError();
                        return false; }
                } else {
                    expertsError();
                    return false; }
            case 3:
                if (textField3.getLength() == 0) {
                    if (checkMurk(textField1.getText()) && checkMurk(textField2.getText()) && checkMurk(textField4.getText()) && checkMurk(textField5.getText())) {
                        return true;
                    } else {
                        lineCheckError();
                        return false; }
                } else {
                    expertsError();
                    return false; }
            case 4:
                if (textField4.getLength() == 0) {
                    if (checkMurk(textField1.getText()) && checkMurk(textField2.getText()) && checkMurk(textField3.getText()) && checkMurk(textField5.getText())) {
                        return true;
                    } else {
                        lineCheckError();
                        return false; }
                } else {
                    expertsError();
                    return false; }
            case 5:
                if (textField5.getLength() == 0) {
                    if (checkMurk(textField1.getText()) && checkMurk(textField2.getText()) && checkMurk(textField3.getText()) && checkMurk(textField4.getText())) {
                        return true;
                    } else {
                        lineCheckError();
                    return false; }
                } else {
                    expertsError();
                return false; }
        }
        return false;
    }
    private void thenInterface() throws IOException {
        Stage newStage = new Stage();
        AnchorPane anchorPanePopup = (AnchorPane)  FXMLLoader.load(getClass().getResource("Fxml/then.fxml"));
        Scene scene = new Scene(anchorPanePopup);
        newStage.setScene(scene);
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.setTitle("");
        newStage.setMaxHeight(730);
        newStage.setMaxWidth(900);
        newStage.showAndWait();
    }
    @FXML
    private Button buttonExitRespondApplication;
    public void exitRespondApplication(ActionEvent actionEvent) {
        buttonExitRespondApplication.setOnAction(event -> {

            ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).close();
        });
    }
}
