package sample.View;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import sample.Controller;

import java.sql.SQLException;

public class MainMenuView extends View {
    public TextField userName;
    public PasswordField password;
    public Text incorrect;
    public Button signup;

    public void login() throws SQLException {
        incorrect.setVisible(false);
        if (controller.login(userName.getText(), password.getText())) {
            controller.setScreen(Main.OPERATION_SCREEN);
            controller.setCurrentUser(userName.getText());
            controller.getInfo(userName.getText());
        } else
            incorrect.setVisible(true);
    }

    public void signUp(ActionEvent actionEvent) {
        controller.setScreen(Main.REGISTRATION_SCREEN);
    }



    public void SearchvacationNoRegisteration(){
        controller.setScreen(Main.Search_vacation);
    }
}