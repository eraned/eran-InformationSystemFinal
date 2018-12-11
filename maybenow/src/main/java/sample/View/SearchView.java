package sample.View;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import sample.Model.Model;

public class SearchView extends View {
    public TextField userName;
    public TextField birth;
    public TextField firstName;
    public TextField lastName;
    public TextField city;

    public void back(ActionEvent actionEvent) {
        controller.setScreen(Main.OPERATION_SCREEN);
    }

    public void search(ActionEvent event) {
        if (!controller.searchUser(userName.getText()).isEmpty()) {
            userName.setText(controller.getUserInfo().get("username"));
            birth.setText(controller.getUserInfo().get("birth"));
            firstName.setText(controller.getUserInfo().get("firstName"));
            lastName.setText(controller.getUserInfo().get("lastName"));
            city.setText(controller.getUserInfo().get("city"));
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("User not found");
            alert.setHeaderText(null);
            alert.setContentText("The user you searched for does not exist Please try again");
            alert.showAndWait();
        }
    }

    public void init(MouseEvent mouseEvent) {
        userName.setText("");
        birth.setText("");
        firstName.setText("");
        lastName.setText("");
        city.setText("");
    }
}