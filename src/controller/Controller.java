package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import model.DirectionEnum;
import model.SpaceGame;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    SpaceGame spaceGame;

    @FXML
    private Pane gamePane;

    @FXML
    private TextField timeId;

    public Controller() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        spaceGame = new SpaceGame(gamePane, timeId);
        spaceGame.start();
    }

    public void handleKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT:
            case KP_LEFT:
                spaceGame.moveShip(DirectionEnum.LEFT);
                break;
            case RIGHT:
            case KP_RIGHT:
                spaceGame.moveShip(DirectionEnum.RIGHT);
                break;
            default:
                break;
        }
    }

}
