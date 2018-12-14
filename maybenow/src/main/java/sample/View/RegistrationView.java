package sample.View;

import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.time.LocalDate;

public class RegistrationView extends View {
    public TextField userName;
    public PasswordField password;
    public DatePicker birth;
    public TextField firstName;
    public TextField lastName;
    public TextField city;
    public javafx.scene.text.Text badage;
    public javafx.scene.text.Text userNameError;
    public javafx.scene.text.Text passWordError;
    public javafx.scene.text.Text firstNameError;
    public javafx.scene.text.Text lastNameError;
    public javafx.scene.text.Text cityError;

    public void RegisterUser() throws SQLException {

        if(!validation()){return;}
        LocalDate date = birth.getValue();
        if (controller.registerUser(userName.getText(), password.getText(), date, firstName.getText(), lastName.getText(), city.getText())) {
            System.out.println("Register User successful!!");
            badage.setVisible(false);
            controller.setCurrentUser(userName.getText());
            controller.getInfo(userName.getText());
            InitFields();
            userNameError.setVisible(false);
            controller.setScreen(Main.OPERATION_SCREEN);

        } else {
            userNameError.setVisible(true);
            badage.setVisible(false);
        }
    }

    public void back(ActionEvent actionEvent) {
        InitFields();
        controller.setScreen(Main.MAIN_MENU_SCREEN);
    }

    public void InitFields() {
        userName.setText("");
        password.setText("");
        firstName.setText("");
        lastName.setText("");
        city.setText("");

        userNameError.setVisible(false);
        passWordError.setVisible(false);
        firstNameError.setVisible(false);
        lastNameError.setVisible(false);
        cityError.setVisible(false);
        badage.setVisible(false);
    }

    private boolean validation() {

        LocalDate date = birth.getValue();
        int year = 2018;
        boolean result = true;
        if (date != null) {
            year = date.getYear();
        }
        if (2018 - year < 18) {
            badage.setVisible(true);
            result= false;
        }
        if (userName.getText().equals("")) {
            result = false;
            userNameError.setVisible(true);
        } else
            userNameError.setVisible(false);

        if (password.getText().equals("")) {
            result = false;
            passWordError.setVisible(true);
        } else
            passWordError.setVisible(false);

        if (firstName.getText().equals("")) {
            result = false;
            firstNameError.setVisible(true);
        } else
            firstNameError.setVisible(false);

        if (lastName.getText().equals("")) {
            result = false;
            lastNameError.setVisible(true);
        } else
            lastNameError.setVisible(false);

        if (city.getText().equals("")) {
            result = false;
            cityError.setVisible(true);
        } else
            cityError.setVisible(false);

        return result;
    }


}