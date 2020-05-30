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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static sample.InformationWindows.successSaving;

public class ShowUsersInterface {

    private ObservableList<User> usersData = FXCollections.observableArrayList();

    @FXML
    private TableView<User> tableUsers;
    @FXML
    private TableColumn<User, String> columnName;
    @FXML
    private TableColumn<User, String> columnSurname;
    @FXML
    private TableColumn<User, String> columnLogin;
    @FXML
    public void initialize() throws IOException {
       initData();
        columnSurname.setCellValueFactory(new PropertyValueFactory<User, String>("surname"));
        columnName.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
        columnLogin.setCellValueFactory(new PropertyValueFactory<User, String>("login"));
        tableUsers.setItems(usersData);
    }
    @FXML
    private Button buttonDelete;
    public void deleteRow(ActionEvent actionEvent){
        buttonDelete.setOnAction(e -> { deleteButtonClicked(); });
    }

    private List<User> users=new ArrayList();

private int deleteButtonClicked() {
    ObservableList<User> userSelected, allUser;
    allUser = tableUsers.getItems();
    if(allUser.size()==0)return 0;
    int j = 0;
    for (User user : allUser) {
        user.setUserNumber(j);
        j++;
    }
    userSelected = tableUsers.getSelectionModel().getSelectedItems();
    for(User user:users){
        if(user.getLogin().equals(userSelected.get(0).getLogin())) {
            users.remove(user);
            break;
        }
    }
    userSelected.forEach(allUser::remove);
    return 0;
}
    private void initData() throws IOException {
        Client admin=new Client();
        User user;
        admin.sendMenuNumberServer(5);
        user=admin.setUserNumberServer();
        int k=user.getUserNumber();
        if(user.getUserNumber()!=0) {
            for (int i = 0; i < k; i++) {
                user=admin.setUserNameSurnameLoginPasswordServer();
                usersData.add(new User(user.getSurname(),user.getName(), user.getLogin()));
                users.add(new User( user.getSurname(),user.getName(), user.getLogin()));
                users.get(i).setPassword(user.getPassword());
            }
            Collections.sort(usersData);
            Collections.sort(users);
            tableUsers.setItems(usersData);
        }
        admin.sendMenuNumberServer(10);
}
@FXML
private Button buttonSave;
    public void save(ActionEvent actionEvent) {
        buttonSave.setOnAction(event -> {
            try {
                saveUsersInformation(actionEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    private void saveUsersInformation(ActionEvent actionEvent) throws IOException {
       Client admin=new Client();
        admin.sendMenuNumberServer(6);
        admin.sendUsersNumber(users.size());
        for(User user:users) {
            admin.sendUserInformation(user);
        }
        ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).close();
        successSaving();
        admin.sendMenuNumberServer(10);
    }

    @FXML
private Button buttonExitShowUsers;
    public void exitShowUsers(ActionEvent actionEvent) {
        buttonExitShowUsers.setOnAction(event -> {
            ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).close();
        });
    }
}
