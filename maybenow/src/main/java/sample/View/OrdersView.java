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
    public ScrollPane scrollpanelCash;
    public ScrollPane scrollpanelOtherCash;
    public ScrollPane scrollpanelTrade;
    public ScrollPane scrollpanelOtherTrade;

    public void init() {
        ArrayList<String> vacationsUserIsSellingCash = controller.getVacationsUserIsSellingApproval(controller.getCurrentUser(), true);
        ArrayList<String> vacationsUserIsInterestedCash = controller.getVacationsUserIsInterested(controller.getCurrentUser(), true);
        VBox rootCash = new VBox();
        VBox rootOtherCash = new VBox();
        scrollpanelCash.setContent(rootCash);
        scrollpanelOtherCash.setContent(rootOtherCash);
        rootCash.setSpacing(10);
        for (String id :
                vacationsUserIsSellingCash) {
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
                        HashMap<String, String> Detailes = controller.getVacationDetails(id);
                        VacationDetailcontroller2.setDetails(Detailes.get("UserName"), Detailes.get("airlinecompany")
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
            Button b3 = new Button("Decline");
            b3.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        controller.rejectOffer(id);
                        Alert error = new Alert(Alert.AlertType.INFORMATION);
                        error.setTitle("Declined!");
                        error.setContentText("The offer was rejected!");
                        error.show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            b1.setStyle("-fx-background-color: #d6a900");
            b2.setStyle("-fx-background-color: #d6a900");
            b3.setStyle("-fx-background-color: #d6a900");
            Label l1 = new Label("Vacation Id: " + id + "   ");
            HBox hbox = new HBox(l1, b1, new Label("   "), b2, new Label("   "), b3);
            rootCash.getChildren().add(hbox);
            rootCash.setSpacing(10);
        }
        for (String id :
                vacationsUserIsInterestedCash) {
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
                        HashMap<String, String> Detailes = controller.getVacationDetails(id);
                        VacationDetailcontroller2.setDetails(Detailes.get("UserName"), Detailes.get("airlinecompany")
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
            rootOtherCash.getChildren().add(hbox);
            rootOtherCash.setSpacing(10);
        }
        /////// TRADES ///////
        ArrayList<String> vacationsUserIsSellingTrade = controller.getVacationsUserIsSellingApproval(controller.getCurrentUser(), false);
        ArrayList<String> vacationsUserIsInterestedTrade = controller.getVacationsUserIsInterested(controller.getCurrentUser(), false);
        VBox rootTrade = new VBox();
        VBox rootOtherTrade = new VBox();
        scrollpanelTrade.setContent(rootTrade);
        scrollpanelOtherTrade.setContent(rootOtherTrade);
        rootTrade.setSpacing(10);
        for (String id :
                vacationsUserIsSellingTrade) {
            Button b1 = new Button("Yours");
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
                        HashMap<String, String> Detailes = controller.getVacationDetails(id);
                        VacationDetailcontroller2.setDetails(Detailes.get("UserName"), Detailes.get("airlinecompany")
                                , Detailes.get("StartDate"), Detailes.get("EndDate"), Detailes.get("TicketNumber"), Detailes.get("StateName"), (boolean) CastStringToBoolean(Detailes.get("IsIncludeReturnFlight")),
                                Detailes.get("TicketType"), (boolean) CastStringToBoolean(Detailes.get("IsIncludeRoomaccommodation")), Detailes.get("Nameaccommodation"), Detailes.get("Price"));
                        detailsWindow.show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            Button b2 = new Button("Offer");
            b2.setOnAction(new EventHandler<ActionEvent>() {
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
                        HashMap<String, String> toSell = controller.getVacationDetails(id);
                        HashMap<String, String> Detailes = controller.getVacationDetails(toSell.get("Offer"));
                        VacationDetailcontroller2.setDetails(Detailes.get("UserName"), Detailes.get("airlinecompany")
                                , Detailes.get("StartDate"), Detailes.get("EndDate"), Detailes.get("TicketNumber"), Detailes.get("StateName"), (boolean) CastStringToBoolean(Detailes.get("IsIncludeReturnFlight")),
                                Detailes.get("TicketType"), (boolean) CastStringToBoolean(Detailes.get("IsIncludeRoomaccommodation")), Detailes.get("Nameaccommodation"), Detailes.get("Price"));
                        detailsWindow.show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            Button b3 = new Button("Approve");
            b3.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        controller.approveTrade(id);
                        Alert error = new Alert(Alert.AlertType.INFORMATION);
                        error.setTitle("Sold!");
                        error.setContentText("The was traded with the user !");
                        error.show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            Button b4 = new Button("Decline");
            b4.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        controller.rejectOffer(id);
                        Alert error = new Alert(Alert.AlertType.INFORMATION);
                        error.setTitle("Declined!");
                        error.setContentText("The offer was rejected!");
                        error.show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            b1.setStyle("-fx-background-color: #d6a900");
            b2.setStyle("-fx-background-color: #d6a900");
            b3.setStyle("-fx-background-color: #d6a900");
            b4.setStyle("-fx-background-color: #d6a900");
            Label l1 = new Label("Vacation Id: " + id + "   ");
            HBox hbox = new HBox(l1, b1, new Label("   "), b2, new Label("   "), b3, new Label("   "), b4);
            rootTrade.getChildren().add(hbox);
            rootTrade.setSpacing(10);
        }
        for (String id :
                vacationsUserIsInterestedTrade) {
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
                        HashMap<String, String> Detailes = controller.getVacationDetails(id);
                        VacationDetailcontroller2.setDetails(Detailes.get("UserName"), Detailes.get("airlinecompany")
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
            rootOtherTrade.getChildren().add(hbox);
            rootOtherTrade.setSpacing(10);
        }
    }

    public void back() {
        VBox root = new VBox();
        scrollpanelCash.setContent(root);
        scrollpanelOtherCash.setContent(root);
        controller.setScreen(Main.OPERATION_SCREEN);
    }

    private boolean CastStringToBoolean(String str) {
        if (str != null && str.equals("Yes"))
            return true;
        else
            return false;
    }
}
