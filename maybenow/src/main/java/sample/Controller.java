package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import sample.View.Main;
import sample.Model.Model;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Controller {
    private HashMap<String, Parent> screens = new HashMap<>();
    private Model model;

    public Controller(Model model) {
        this.model = model;
    }

    public void loadScreen(String source) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(source));
            Parent loadScreen = loader.load();
            screens.put(source, loadScreen);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void initScreen() {
        Scene scene = new Scene(screens.get(Main.MAIN_MENU_SCREEN));
        Main.theStage.setScene(scene);
        Main.theStage.show();
        Main.theStage.getScene().setRoot(screens.get(Main.MAIN_MENU_SCREEN));
    }

    public void setScreen(String name) {
        Main.theStage.getScene().setRoot(screens.get(name));
    }

    public boolean login(String userName, String password) {
        return model.login(userName, password);
    }

    public boolean registerUser(String userName, String password, LocalDate date, String firstName, String lastName, String city) {
        return model.RegisterUser(userName, password, date, firstName, lastName, city);
    }

    public boolean updateUser(String userName, String password, String birth, String firstName, String lastName, String city) {
        return model.updateUser(userName, password, birth, firstName, lastName, city);
    }

    public boolean AddVacation(String UserName,String AirlineCompany,LocalDate Startdate,LocalDate Enddate,String TicketNum,String country,boolean IsIncludeReturnFlight,String TicketType,boolean IsIncludeaccommodation,String Nameaccommodation,String Price){
        return model.AddVacation(UserName, AirlineCompany, Startdate, Enddate, TicketNum, country, IsIncludeReturnFlight, TicketType, IsIncludeaccommodation, Nameaccommodation, Price);
    }

    public void setCurrentUser(String userName) {
        model.setCurrentUser(userName);
    }

    public HashMap<String, String> searchUser(String userName) {
        return model.searchUser(userName);
    }

    public void getInfo(String userName) {
        model.getInfo(userName);
    }
    public HashMap<String, String> getUserInfo() {
        return model.getUserInfo();
    }

    public boolean findUser(String userName) {
        return model.findUser(userName);
    }

    public boolean AddPayment(String vacationID, String CardOwner,String CreditCardNum ,LocalDate Validation){
        return model.AddPayment(vacationID, CardOwner, CreditCardNum, Validation);
    }
    public String getCurrentUser() {
        return model.getCurrentUser();
    }

    public void DeleteUser() {
        model.DeleteUser();
    }
    public HashMap<String, String> getVacationDetalies(String VacationID){
        return model.GetVacationDetalies(VacationID);
    }

    public ArrayList<String> getVacationResults(String text) {
        return model.getVacationResults(text);
    }
}