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
import java.util.Scanner;

import static sample.InformationWindows.theBestVariant;

public class ResultInterface {
    public TextField textFieldDck1;
    public TextField textFieldDck2;
    public TextField textFieldDck3;
    public TextField textFieldDck4;
    public TextField textFieldDck5;
    private ObservableList<Method> serviceObservableList = FXCollections.observableArrayList();
    @FXML
    private TableView tableResult;
    @FXML
    private TableColumn columnService;
    @FXML
    private TableColumn columnVariantNumber;
    @FXML
    private TextField textFieldR2;
    @FXML
    private TextField textFieldR3;
    @FXML
    private TextField textFieldR4;
    @FXML
    private TextField textFieldR5;
    @FXML
    private TextField textFieldR1;
    @FXML
    private TextField textFieldD1;
    @FXML
    private TextField textFieldD2;
    @FXML
    private TextField textFieldD3;
    @FXML
    private TextField textFieldD4;
    @FXML
    private TextField textFieldD5;
    @FXML
    public void initialize() throws IOException {
        initData();
        columnService.setCellValueFactory(new PropertyValueFactory<Method, String>("service"));
        columnVariantNumber.setCellValueFactory(new PropertyValueFactory<Method, String>("ck"));
    }
    String service1="";
    String price1="";
    private void initData() throws IOException {
        Client admin=new Client();
        admin.sendMenuNumberServer(14);
        Method result=new Method();
        result.setRj(admin.getResult());
        Scanner scanner=new Scanner(result.getRj());
        textFieldR1.appendText(String.format("%.2f",Double.parseDouble(scanner.nextLine())));
        textFieldR2.appendText(String.format("%.2f",Double.parseDouble(scanner.nextLine())));
        textFieldR3.appendText(String.format("%.2f",Double.parseDouble(scanner.nextLine())));
        textFieldR4.appendText(String.format("%.2f",Double.parseDouble(scanner.nextLine())));
        textFieldR5.appendText(String.format("%.2f",Double.parseDouble(scanner.nextLine())));
        result.setDRj(admin.getResult());
        scanner=new Scanner(result.getDRj());
        textFieldD1.appendText(String.format("%.4f", Double.parseDouble(scanner.nextLine())));
        textFieldD2.appendText(String.format("%.4f",Double.parseDouble( scanner.nextLine())));
        textFieldD3.appendText(String.format("%.4f", Double.parseDouble(scanner.nextLine())));
        textFieldD4.appendText(String.format("%.4f", Double.parseDouble(scanner.nextLine())));
        textFieldD5.appendText(String.format("%.4f",Double.parseDouble( scanner.nextLine())));
        result.setDCk(admin.getResult());
        scanner=new Scanner(result.getDCk());
        textFieldDck1.appendText(String.format("%.4f",Double.parseDouble(scanner.nextLine())));
        textFieldDck2.appendText(String.format("%.4f",Double.parseDouble(scanner.nextLine())));
        textFieldDck3.appendText(String.format("%.4f",Double.parseDouble(scanner.nextLine())));
        textFieldDck4.appendText(String.format("%.4f",Double.parseDouble(scanner.nextLine())));
        textFieldDck5.appendText(String.format("%.4f",Double.parseDouble(scanner.nextLine())));
        result.setService(admin.getResult());
        scanner=new Scanner(result.getService());
        result.setPrice(admin.getResult());
        Scanner scanner3=new Scanner(result.getPrice());
        result.setCk(admin.getResult());
        Scanner scanner1=new Scanner(result.getCk());
        admin.sendMenuNumberServer(10);
        double best=0.0;
        String ck="";
        String service="";
        String price="";
        while(scanner.hasNextLine()) {
            service=scanner.nextLine();
            price=scanner3.nextLine();
            ck=scanner1.nextLine();
            if(best<Double.parseDouble(ck)) {
                best = Double.parseDouble(ck);
                service1=service;
                price1=price;
                System.out.println(price1);
            }
            serviceObservableList.add(new Method(service,String.format("%.2f",Double.parseDouble(ck))));
        }
        tableResult.setItems(serviceObservableList);
    }
    int flag=0;
    @FXML
    private Button buttonSendUserResult;
    public void sendUserResult(ActionEvent actionEvent) {
        buttonSendUserResult.setOnAction(event -> {
            if(flag==0) {
                try {
                    Client admin1 = new Client();
                    admin1.sendMenuNumberServer(17);
                    admin1.sendMethodInformation(service1);
                    admin1.sendMethodInformation(price1);
                    admin1.sendMenuNumberServer(10);

                    flag=1;
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } });
    }
    @FXML
    private Button buttonСonclusion;
    public void showConclusion(ActionEvent actionEvent) {
        buttonСonclusion.setOnAction(event -> {
            theBestVariant(service1);
        });
    }
    @FXML
    private Button buttonOk;
    public void exitResultInterface(ActionEvent actionEvent) {
        buttonOk.setOnAction(event -> {
            ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).close();
        });
    }

}
