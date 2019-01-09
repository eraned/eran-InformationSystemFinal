package sample.Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import sample.View.Main;
import sample.Model.Model;

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
        if (name.equals(Main.ORDERS_SCREEN)) {
            Main.theStage.setHeight(615);
        } else {
            Main.theStage.setHeight(430);
        }
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

    public boolean AddVacation(String UserName, String AirlineCompany, LocalDate Startdate, LocalDate Enddate, String TicketNum, String country, boolean IsIncludeReturnFlight, String TicketType, boolean IsIncludeaccommodation, String Nameaccommodation, String Price, String status, String interested) {
        return model.AddVacation(UserName, AirlineCompany, Startdate, Enddate, TicketNum, country, IsIncludeReturnFlight, TicketType, IsIncludeaccommodation, Nameaccommodation, Price, status, interested);
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

    public boolean AddPayment(String vacationID, String CardOwner, String CreditCardNum, LocalDate Validation) {
        return model.AddPayment(vacationID, CardOwner, CreditCardNum, Validation);
    }

    public String getCurrentUser() {
        return model.getCurrentUser();
    }

    public void DeleteUser() {
        model.DeleteUser();
    }

    public HashMap<String, String> getVacationDetails(String VacationID) {
        return model.GetVacationDetalies(VacationID);
    }

    public ArrayList<String> getVacationResults(String text) {
        return model.getVacationResults(text);
    }

    public void addInterestedCash(String vacationId, String userName) {
        model.addInterestedCash(vacationId, userName);
    }
    public void addInterestedTrade(String vacationId, String userName,String offer) {
        model.addInterestedTrade(vacationId, userName,offer);
    }

    public ArrayList<String> getVacationsUserIsSellingApproval(String username, boolean cash) {
        return model.getVacationsUserIsSellingApproval(username, cash);
    }

    public ArrayList<String> getVacationsUserIsSelling() {
        return model.getVacationsUserIsSelling(getCurrentUser());
    }

    public ArrayList<String> getVacationsUserIsInterested(String username, boolean cash) {
        return model.getVacationsUserIsInterested(username, cash);
    }

    public void approveSale(String id) {
        model.approveSale(id);

    }

    public void rejectOffer(String id) {
        model.rejectOffer(id);
    }

    public void approveTrade(String id) {
        model.approveTrade(id);
    }
}