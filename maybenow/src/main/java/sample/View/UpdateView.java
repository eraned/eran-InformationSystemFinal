package sample.View;

import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;


public class UpdateView extends View {

    public TextField userName;
    public PasswordField password;
    public TextField firstName;
    public TextField lastName;
    public TextField city;
    public Text passWordError;
    public Text birthDateError;
    public Text firstNameError;
    public Text lastNameError;
    public Text cityError;
    public Text userNameError;
    public Text updateSucc;
    public TextField birth;

    public void back(ActionEvent actionEvent) {
        controller.setScreen(Main.OPERATION_SCREEN);
    }

    public void init(MouseEvent mouseEvent) {
        controller.getInfo(controller.getCurrentUser());
        userName.setText(controller.getUserInfo().get("username"));
        password.setText(controller.getUserInfo().get("password"));
        firstName.setText(controller.getUserInfo().get("firstName"));
        lastName.setText(controller.getUserInfo().get("lastName"));
        city.setText(controller.getUserInfo().get("city"));
        birth.setText(controller.getUserInfo().get("birth"));
        resetErrors();
    }

    public void updateInfo(ActionEvent actionEvent) {
        resetErrors();
        if (validation()) {
            controller.updateUser(userName.getText(), password.getText(), birth.getText(), firstName.getText(), lastName.getText(), city.getText());
            updateSucc.setVisible(true);
        }
    }

    private boolean validation() {
        boolean result = true;
        if (userName.getText().equals("")) {
            result = false;
            userNameError.setVisible(true);
        }
        if (password.getText().equals("")) {
            result = false;
            passWordError.setVisible(true);
        }
        if (firstName.getText().equals("")) {
            result = false;
            firstNameError.setVisible(true);
        }
        if (lastName.getText().equals("")) {
            result = false;
            lastNameError.setVisible(true);
        }
        if (city.getText().equals("")) {
            result = false;
            cityError.setVisible(true);
        }
        if (birth.getText().equals("")) {
            result = false;
            birthDateError.setVisible(true);
        }
        if (controller.findUser(userName.getText()) && !controller.getCurrentUser().equals(userName.getText())) {
            result = false;
            userNameError.setVisible(true);
        }
        return result;
    }

    private void resetErrors() {
        userNameError.setVisible(false);
        passWordError.setVisible(false);
        birthDateError.setVisible(false);
        firstNameError.setVisible(false);
        lastNameError.setVisible(false);
        cityError.setVisible(false);
        updateSucc.setVisible(false);
    }

}