package sample.View;

import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class AddVacationView extends View {

    public TextField AirLineCompany;
    public DatePicker StartDate;
    public DatePicker EndDate;
    public TextField TicketNumbers;
    public TextField CountryName;
    public CheckBox IsIncludeReturnFlight;
    public ComboBox TicketType;
    public CheckBox IsIncludeAccommodation;
    public TextField AccommodationName;
    public TextField Price;
    public javafx.scene.text.Text badTicNum;
    public javafx.scene.text.Text priceError;
    public Text companyError;
    public Text startDateError;
    public Text endDateError;
    public Text countryNameError;
    public Text ticketTypeError;

    public void CreateVacation() {
        if (!validation())
            return;
        controller.AddVacation(controller.getCurrentUser(), AirLineCompany.getText(), StartDate.getValue(), EndDate.getValue(), TicketNumbers.getText(),
                CountryName.getText(), IsIncludeReturnFlight.isSelected(), TicketType.getSelectionModel().getSelectedItem().toString(), IsIncludeAccommodation.isSelected(),
                AccommodationName.getText(), Price.getText());

        Alert Sucess = new Alert(Alert.AlertType.INFORMATION);
        Sucess.setTitle("vacation Added Succesfully!");
        Sucess.setHeaderText("vacation Added Succesfully");
        Sucess.show();
        init();
        back();
    }

    private boolean validation() {
        boolean validation = true;
        if (AirLineCompany.getText().equals("")) {
            companyError.setVisible(true);
            validation = false;
        }
        if (StartDate.getValue() == null) {
            startDateError.setVisible(true);
            validation = false;
        }
        if (EndDate.getValue() == null) {
            endDateError.setVisible(true);
            validation = false;
        }
        if(!CheckNumber(TicketNumbers.getText())){
            badTicNum.setVisible(true);
            validation=false;
        }
        if(CountryName.getText().equals("")){
            countryNameError.setVisible(true);
            validation=false;
        }
        if(!CheckNumber(Price.getText())){
            priceError.setVisible(true);
            validation=false;
        }
        if(TicketType.getValue()==null){
            countryNameError.setVisible(true);
            validation=false;
        }
        return validation;
    }

    public void back() {
        controller.setScreen(Main.OPERATION_SCREEN);
        init();
    }


    /*Checking if a string is a number */
    private boolean CheckNumber(String num) {
        if(num.length()==0)
            return false;

        for (int i = 0; i < num.length(); i++) {
            if (!Character.isDigit(num.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public void init() {
        AirLineCompany.setText("");
        TicketNumbers.setText("");
        CountryName.setText("");
        AccommodationName.setText("");
        Price.setText("");
        badTicNum.setVisible(false);
        priceError.setVisible(false);
        badTicNum.setVisible(false);
        priceError.setVisible(false);
        companyError.setVisible(false);
        startDateError.setVisible(false);
        endDateError.setVisible(false);
        countryNameError.setVisible(false);
        ticketTypeError.setVisible(false);
        TicketType.getItems().removeAll(TicketType.getItems());
        TicketType.getItems().addAll("Adult", "Kid", "Baby");
    }
}
