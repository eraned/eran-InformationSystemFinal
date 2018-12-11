package sample.View;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class VacationDetailesView extends View {
    public TextField UserName;
    public TextField AirLineCompany;
    public TextField StartDate;
    public TextField EndDate;
    public TextField TicketNumbers;
    public TextField CountryName;
    public CheckBox IsIncludeReturnFlight;
    public ComboBox TicketType;
    public CheckBox IsIncludeAccommodation;
    public TextField AccommodationName;
    public TextField Price;
    public javafx.scene.text.Text badTicNum;
    public javafx.scene.text.Text badRank;



    public void setDetailes(String UserName,String AirLineCompany,String StartDate,String EndDate,
                            String TicketNumbers,String CountryName,boolean IsIncludeReturnFlight,
                            String TicketType,boolean IsIncludeAccommodation,String AccommodationName,String Price ){

        this.UserName.setText(UserName);
        this.AirLineCompany.setText(AirLineCompany);
        this.StartDate.setText(StartDate);
        this.EndDate.setText(EndDate);
        this.TicketNumbers.setText(TicketNumbers);
        this.CountryName.setText(CountryName);
        this.IsIncludeReturnFlight.setSelected(IsIncludeReturnFlight);
        this.TicketType.getItems().add(TicketType);
        this.IsIncludeAccommodation.setSelected(IsIncludeAccommodation);
        this.AccommodationName.setText(AccommodationName);
        this.Price.setText(Price);


    }
}