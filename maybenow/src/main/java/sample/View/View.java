package sample.View;

import sample.Controller.Controller;

public class View {
    protected static Controller controller;

    public void setController(Controller controller) {
        View.controller = controller;
    }
}
