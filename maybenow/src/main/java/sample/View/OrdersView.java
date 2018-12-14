package sample.View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;

public class OrdersView extends View {

    public Text noVacationsText;
    public ScrollPane scrollpane1;
    public ScrollPane scrollpanelOther;

    public void init() {
        ArrayList<String> vacationsUserIsSelling = controller.getVacationsUserIsSelling(controller.getCurrentUser());
        ArrayList<String> vacationsUserIsInterested = controller.getVacationsUserIsInterested(controller.getCurrentUser());
        VBox root = new VBox();
        VBox rootOther = new VBox();
        scrollpane1.setContent(root);
        scrollpanelOther.setContent(rootOther);
        root.setSpacing(10);
        for (String id :
                vacationsUserIsSelling) {
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

            Button b2 = new Button("Approve");
            b2.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        controller.approveSale(id);
                        Alert error = new Alert(Alert.AlertType.INFORMATION);
                        error.setTitle("Sold!");
                        error.setContentText("The vacation is sold to the user!");
                        error.show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            b1.setStyle("-fx-background-color: #d6a900");
            b2.setStyle("-fx-background-color: #d6a900");
            Label l1 = new Label("Vacation Id: " + id + "   ");
            Label l2 = new Label("   ");
            HBox hbox = new HBox(l1, b1, l2, b2);
            root.getChildren().add(hbox);
            root.setSpacing(10);
        }
        for (String id :
                vacationsUserIsInterested) {
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
            b1.setStyle("-fx-background-color: #d6a900");
            Label l1 = new Label("Vacation Id: " + id + "   ");
            Label l2 = new Label("   ");
            HBox hbox = new HBox(l1, b1, l2);
            rootOther.getChildren().add(hbox);
            rootOther.setSpacing(10);
        }
    }

    public void back() {
        VBox root = new VBox();
        scrollpane1.setContent(root);
        scrollpanelOther.setContent(root);
        controller.setScreen(Main.OPERATION_SCREEN);
    }

    private boolean CastStringToBoolean(String str) {
        if (str != null && str.equals("Yes"))
            return true;
        else
            return false;
    }
}
