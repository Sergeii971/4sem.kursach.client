package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import static sample.AuthorisationInterface.Answers;
import static sample.InformationWindows.noData;

public class WatchAnswersInterface {
    private ObservableList<Method> answers = FXCollections.observableArrayList();
    @FXML
    private TableView tableAnswers;
    @FXML
    private TableColumn date;
    @FXML
    private TableColumn need;
    @FXML
    private TableColumn price;
    @FXML
    public void initialize() throws IOException {
        initData();
        date.setCellValueFactory(new PropertyValueFactory<Method, String>("date"));
        need.setCellValueFactory(new PropertyValueFactory<Method, String>("service"));
        price.setCellValueFactory(new PropertyValueFactory<Method, String>("price"));
    }
    private void initData() throws IOException {
        Client admin=new Client();
        FileReader fileReader=new FileReader(Answers);
        Scanner scanner=new Scanner(fileReader);
        admin.sendMenuNumberServer(18);
        admin.sendLogin(scanner.nextLine());
        int number=Integer.parseInt(admin.getAnswerData());
        if(number!=0) {
            for (int i = 0; i < number; i++) {
                Method answer = new Method();
                answer.setDate(admin.getAnswerData());
                answer.setService(admin.getAnswerData());
                answer.setPrice(admin.getAnswerData());
                answers.add(answer);
                tableAnswers.setItems(answers);
            }
        }
        admin.sendMenuNumberServer(10);
    }
    @FXML
    private Button buttonExitWatchAnswers;

    public void exitWatchAnswers(ActionEvent actionEvent) {
        buttonExitWatchAnswers.setOnAction(event -> {
            ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).close();
        });
    }
}
