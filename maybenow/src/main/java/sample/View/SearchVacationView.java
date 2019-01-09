package sample.View;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.*;

public class SearchVacationView extends View {
    public Button SearchButton;
    public TextField CountryName;
    public ScrollPane scrollpane;
    public Text noVacationsText;
    private FXMLLoader loader;
    private javafx.scene.control.ListView<String> usersVacationsListView;

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
        if (vacationsIdForSpecificCountry.size() == 0) {
            noVacationsText.setVisible(true);
            scrollpane.setVisible(false);
            return;
        } else {
            noVacationsText.setVisible(false);
            scrollpane.setVisible(true);
        }
        scrollpane.setContent(root);
        root.setSpacing(10);
        for (String id :
                vacationsIdForSpecificCountry) {
            HashMap<String, String> Detailes = controller.getVacationDetails(id);
            Button b1 = new Button("details");
            b1.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        Stage detailsWindow;
                        detailsWindow = new Stage();
                        detailsWindow.initModality(Modality.APPLICATION_MODAL);
                        detailsWindow.setTitle("Details");
                        loader = new FXMLLoader(getClass().getClassLoader().getResource("VacationDetails.fxml"));
                        Parent loadScreen = loader.load();
                        Scene scene = new Scene(loadScreen, 630, 400);
                        detailsWindow.setScene(scene);
                        VacationDetailesView VacationDetailcontroller2 = loader.getController();
                        ;
                        VacationDetailcontroller2.setDetails(Detailes.get("UserName"), Detailes.get("airlinecompany")
                                , Detailes.get("StartDate"), Detailes.get("EndDate"), Detailes.get("TicketNumber"), Detailes.get("StateName"), (boolean) CastStringToBoolean(Detailes.get("IsIncludeReturnFlight")),
                                Detailes.get("TicketType"), (boolean) CastStringToBoolean(Detailes.get("IsIncludeRoomaccommodation")), Detailes.get("Nameaccommodation"), Detailes.get("Price"));
                        detailsWindow.show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            Button b2 = new Button("Cash");
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
                        controller.addInterestedCash(id, controller.getCurrentUser());
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Buy offer");
                        alert.setHeaderText(null);
                        alert.setContentText("Successfully sent a buy offer to the seller, after paying him with cash he will confirm it and finish the sale.");

                        alert.showAndWait();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            Button b3 = new Button("Trade");
            b3.setOnAction(new EventHandler<ActionEvent>() {
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
                        Stage stage = new Stage();
                        usersVacationsListView = new ListView<>();
                        usersVacationsListView.getSelectionModel().selectedItemProperty()
                                .addListener(new ChangeListener<String>() {
                                    public void changed(
                                            ObservableValue<? extends String> observable,
                                            String oldValue, String newValue) {
                                        tradeVacation(id, newValue);
                                        stage.close();
                                        Alert error = new Alert(Alert.AlertType.INFORMATION);
                                        error.setTitle("Sent offer!");
                                        error.setContentText("The offer was sent the user!");
                                        error.show();
                                    }
                                });
                        ArrayList<String> usersVacations = controller.getVacationsUserIsSelling();
                        for (int i = 0; i < usersVacations.size(); i++) {
                            usersVacationsListView.getItems().add(usersVacations.get(i));
                        }
                        stage.setTitle("Please select which vacation you want to trade");
                        Scene scene = new Scene(new Group(), 420, 450);
                        stage.initModality(Modality.APPLICATION_MODAL);
                        final VBox vBox = new VBox();
                        vBox.setSpacing(5);
                        vBox.setMinSize(420, 450);
                        vBox.setStyle("-fx-background-color: #d6a900");
                        vBox.setPadding(new Insets(0, 0, 0, 0));
                        usersVacationsListView.setStyle("-fx-control-inner-background:  #d6a900;-fx-background-insets: 10 ;");
                        vBox.getChildren().addAll(usersVacationsListView);
                        vBox.setAlignment(Pos.CENTER);
                        Group group = ((Group) scene.getRoot());
                        group.getChildren().addAll(vBox);
                        stage.setScene(scene);
                        stage.show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            if (!Detailes.get("UserName").equals(controller.getCurrentUser())) {
                b1.setStyle("-fx-background-color: #d6a900");
                b2.setStyle("-fx-background-color: #d6a900");
                b3.setStyle("-fx-background-color: #d6a900");
                Label l1 = new Label("Vacation Id: " + id + "   ");
                hbox = new HBox(l1, b1, new Label("   "), b2, new Label("   "), b3);
                root.getChildren().add(hbox);
                root.setSpacing(10);
            }
        }
    }

    private void tradeVacation(String wantedId, String toTradeVacation) {
        String toTradeId = toTradeVacation.substring(0, toTradeVacation.indexOf(',') - 1);
        controller.addInterestedTrade(wantedId, controller.getCurrentUser(), toTradeId);
    }

    public void init() {
        noVacationsText.setVisible(false);
    }
}
