package sample.View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;

public class SearchVacationView extends View {
    public Button SearchButton;
    public TextField CountryName;
    public ScrollPane scrollpane;
    public Text noVacationsText;

    private boolean CastStringToBoolean(String str) {
        if (str != null && str.equals("Yes"))
            return true;
        else
            return false;
    }

    public void back() {
        CountryName.setText("");
        VBox root = new VBox();
        scrollpane.setContent(root);
        if (controller.getCurrentUser() != null && !controller.getCurrentUser().equals("")) {
            controller.setScreen(Main.OPERATION_SCREEN);
        } else
            controller.setScreen(Main.MAIN_MENU_SCREEN);
    }

    public void Search() {
        VBox root = new VBox();
        HBox hbox;
        init();
        ArrayList<String> vacationsIdForSpecificCountry = controller.getVacationResults(CountryName.getText().toUpperCase());
        if(vacationsIdForSpecificCountry.size()==0){
            noVacationsText.setVisible(true);
            scrollpane.setVisible(false);
            return;
        }
        else {
            noVacationsText.setVisible(false);
            scrollpane.setVisible(true);
        }
        scrollpane.setContent(root);
        root.setSpacing(10);
        for (String id :
                vacationsIdForSpecificCountry) {
            Button b1 = new Button("details");
            b1.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        Stage detailsWindow;
                        detailsWindow = new Stage();
                        detailsWindow.initModality(Modality.APPLICATION_MODAL);
                        detailsWindow.setTitle("Details");
                        FXMLLoader loader2 = new FXMLLoader(getClass().getClassLoader().getResource("VacationDetails.fxml"));
                        Parent loadScreen = loader2.load();
                        Scene scene = new Scene(loadScreen, 630, 400);
                        detailsWindow.setScene(scene);
                        VacationDetailesView VacationDetailcontroller2 = loader2.getController();
                        HashMap<String, String> Detailes = controller.getVacationDetalies(id);
                        VacationDetailcontroller2.setDetailes(Detailes.get("UserName"), Detailes.get("airlinecompany")
                                , Detailes.get("StartDate"), Detailes.get("EndDate"), Detailes.get("TicketNumber"), Detailes.get("StateName"), (boolean) CastStringToBoolean(Detailes.get("IsIncludeReturnFlight")),
                                Detailes.get("TicketType"), (boolean) CastStringToBoolean(Detailes.get("IsIncludeRoomaccommodation")), Detailes.get("Nameaccommodation"), Detailes.get("Price"));
                        detailsWindow.show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            Button b2 = new Button("Purchase");
            b2.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        if (controller.getCurrentUser() == null || controller.getCurrentUser().equals("")) {
                            Alert error = new Alert(Alert.AlertType.INFORMATION);
                            error.setTitle("Open To Register Users");
                            error.setHeaderText("please Go back To Main menu in order to connect Or sign up");
                            error.setContentText("The action is allowed to registers users");
                            error.show();
                            return;
                        }
                        Stage PurchaseWindow;
                        PurchaseWindow = new Stage();
                        PurchaseWindow.initModality(Modality.APPLICATION_MODAL);
                        PurchaseWindow.setTitle("Purchase");
                        FXMLLoader loader3 = new FXMLLoader(getClass().getClassLoader().getResource("PurchaseVacation.fxml"));
                        Parent loadScreen = loader3.load();
                        Scene scene = new Scene(loadScreen, 500, 400);
                        PurchaseWindow.setScene(scene);
                        PurchaseVacationView VacationPurchasecontroller2 = loader3.getController();
                        VacationPurchasecontroller2.setParameters(id, controller.getCurrentUser());
                        PurchaseWindow.show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            b1.setStyle("-fx-background-color: #d6a900");
            b2.setStyle("-fx-background-color: #d6a900");
            Label l1 = new Label("Vacation Id: " + id + "   ");
            Label l2 = new Label("   ");
            hbox = new HBox(l1, b1, l2, b2);
            root.getChildren().add(hbox);
            root.setSpacing(10);
        }
    }

    public void init() {
        noVacationsText.setVisible(false);
    }
}
