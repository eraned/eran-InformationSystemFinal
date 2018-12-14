package sample.View;

import javafx.application.Application;
import javafx.stage.Stage;
import sample.Controller.Controller;
import sample.Model.Model;

public class Main extends Application {
    public static Stage theStage;
    public static final String MAIN_MENU_SCREEN = "MainMenu.fxml";
    public static final String REGISTRATION_SCREEN = "Registration.fxml";
    public static final String UPDATE_SCREEN = "Update.fxml";
    public static final String OPERATION_SCREEN = "Operation.fxml";
    public static final String SEARCH_SCREEN = "Search.fxml";
    public static final String ADD_vacation = "AddVaction.fxml";
    public static final String  Search_vacation="SearchVacation.fxml";
    public static final String  ORDERS_SCREEN="Orders.fxml";
    @Override
    public void start(Stage primaryStage) {
        Model model = new Model();
        theStage = primaryStage;
        Controller controller = new Controller(model);
        View view = new View();
        view.setController(controller);
        controller.loadScreen(MAIN_MENU_SCREEN);
        controller.loadScreen(REGISTRATION_SCREEN);
        controller.loadScreen(UPDATE_SCREEN);
        controller.loadScreen(OPERATION_SCREEN);
        controller.loadScreen(SEARCH_SCREEN);
        controller.loadScreen(ADD_vacation);
        controller.loadScreen(Search_vacation);
        controller.loadScreen(ORDERS_SCREEN);
        controller.initScreen();
        theStage.setTitle("Vacation4U");
    }

    public static void main(String[] args) {
        launch(args);
    }
}