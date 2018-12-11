package sample.View;


import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.util.Optional;

public class OperationView extends View {

    public Button UpdateAccount;
    public Button Logout;
    public Button DeleteAccount;
    public Button SearchAccount;
    public Text welcome;
    public Button AddVacation;

    public void UpdateAccount(ActionEvent actionEvent) {
        controller.setScreen(Main.UPDATE_SCREEN);
    }

    public void SearchAccount(ActionEvent actionEvent) {
        controller.setScreen(Main.SEARCH_SCREEN);
    }

    public void Logout(ActionEvent actionEvent) {

        controller.setScreen(Main.MAIN_MENU_SCREEN);
        controller.setCurrentUser("");
    }

    public void DeleteAccount(ActionEvent actionEvent) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Warning!");
        alert.setHeaderText("");
        alert.setContentText("Are you sure you want to delete your account?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            controller.DeleteUser();
            controller.setScreen(Main.MAIN_MENU_SCREEN);
        } else {
            // do nothing..
        }

    }

    public void setWelcomeMessage(MouseEvent mouseEvent) {
        welcome.setText("Welcome "+controller.getUserInfo().get("firstName")+"!");
    }

    public void AddVaction(){
        controller.setScreen(Main.ADD_vacation

        );
    }

    public void SearchVacation(){

        controller.setScreen(Main.Search_vacation);

    }
}
