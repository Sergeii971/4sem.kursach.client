package sample;

import javafx.scene.control.Alert;

public class InformationWindows {
    public static void authorizationError(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ошибка авторизации");
        alert.setHeaderText(null);
        alert.setContentText("Не удалось войти в систему.\n" +
                "1.Пожалуйста, убедитесь, что вы ввели свой логин и пароль правильно.\n" +
                "2.Проверьте, не нажата ли клавиша Caps Lock.\n" +
                "3.Убедитесь, что выбран правильный язык.\n");
        alert.showAndWait();
    }
    public static void lineNullError(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText(null);
        alert.setContentText("\nВы не заполнили все поля!!!");
        alert.showAndWait();
    }
    public static void inputOldLoginPasswordError(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText(null);
        alert.setContentText("\nнеправильный старый логин или пароль");
        alert.showAndWait();
    }
    public static void successSaving(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText(null);
        alert.setContentText("\nДанные успешно сохранены");
        alert.showAndWait();
    }
    public static void lineCheckError(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText(null);
        alert.setContentText("\nошибка ввода!!!\n проверьте свои данные");
        alert.showAndWait();
    }
    public static void noData(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText(null);
        alert.setContentText("\nДанных не найдено");
        alert.showAndWait();
    }
    public static void loginRepeatError(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText(null);
        alert.setContentText("\nтакой логин уже существует, выберете другой");
        alert.showAndWait();
    }
    public static void expertsError(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText(null);
        alert.setContentText("\nэксперт не может выставлять оценку сам себе");
        alert.showAndWait();
    }
    public static void expertsNumberError(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText(null);
        alert.setContentText("\nэкспертов всего 5");
        alert.showAndWait();
    }
    public static void variantNumberError(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText(null);
        alert.setContentText("\nдля выбора необходимо 2 и более варианта");
        alert.showAndWait();
    }
    public static void theBestVariant(String best){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText(null);
        alert.setContentText("\nпервонеобходимая услуга: "+best);
        alert.showAndWait();
    }
}
