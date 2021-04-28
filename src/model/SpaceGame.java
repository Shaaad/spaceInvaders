package model;

import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static java.lang.System.currentTimeMillis;

public class SpaceGame {

    private Pane gamePane;
    private TextField gameTime;
    Timer timer;
    private Ship ship;
    private List<Asteroid> asteroidList;
    long startTime;

    public SpaceGame(Pane gamePane, TextField gameTime) {
        this.gamePane = gamePane;
        this.gameTime = gameTime;
        ship = new Ship();
        asteroidList = new ArrayList<>();
    }

    public void start() {
        startTime = currentTimeMillis();

//        draw asteroids
        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> drawAsteroids());
            }
        };
        timer.schedule(timerTask, 500, 500);

//        draw ship
        drawShip();
    }

    private void drawAsteroids() {
        //update game time
        updateGameTime();

        //move asteroids down by 1 line
        moveAsteroids();

        //check for game over
        checkGameOver();

        //draw new asteroid
        addAsteroid();
    }

    private void moveAsteroids() {
        Iterator<Asteroid> i = asteroidList.iterator();
        while (i.hasNext()) {
            Asteroid asteroid = i.next();
            if (moveAcceptable(asteroid, DirectionEnum.DOWN)) {
                asteroid.setY(asteroid.getY() + asteroid.width);
                asteroid.relocate(asteroid.getX(), asteroid.getY());
            } else {
                i.remove();
                gamePane.getChildren().remove(asteroid);
            }
        }
    }

    private void addAsteroid() {
        Asteroid asteroid = new Asteroid();
        asteroidList.add(asteroid);
        gamePane.getChildren().add(asteroid);
    }

    private void drawShip() {
        gamePane.getChildren().add(ship);
    }

    private void checkGameOver() {
        for (Asteroid asteroid : asteroidList) {
            if (asteroid.getX() == ship.getX() && asteroid.getY() == ship.getY()) {
                stopGame();
            }
        }
    }

    public void moveShip(DirectionEnum direction) {
        if (direction.equals(DirectionEnum.LEFT)) {
            if (moveAcceptable(ship, DirectionEnum.LEFT)) {
                ship.setX(ship.getX() - Ship.width);
            }
        } else if (direction.equals(DirectionEnum.RIGHT)) {
            if (moveAcceptable(ship, DirectionEnum.RIGHT)) {
                ship.setX(ship.getX() + Ship.width);
            }
        }
        ship.relocate(ship.getX(), ship.getY());
        checkGameOver();
    }

    public boolean moveAcceptable(Rectangle element, DirectionEnum direction) {
        final Bounds bounds = gamePane.getLayoutBounds();
        if (DirectionEnum.LEFT.equals(direction)) {
            return (element.getX() >= bounds.getMinX() + Ship.width);
        } else if (DirectionEnum.RIGHT.equals(direction)) {
            return (element.getX() < bounds.getMaxX() - Ship.width);
        } else if (DirectionEnum.DOWN.equals(direction)) {
            return (element.getY() < bounds.getMaxY() - Ship.width);
        } else
            return false;
    }

    private void updateGameTime() {
        long passedTimeInMilliseconds = currentTimeMillis() - startTime;
        long passedTimeInSeconds = TimeUnit.MILLISECONDS.toSeconds(passedTimeInMilliseconds);
        gameTime.setText(String.valueOf(passedTimeInSeconds));
    }

    public void stopGame() {
        Text text = new Text("GAME OVER");
        text.setFont(new Font(40));
        text.setFill(Color.RED);
        text.setTextAlignment(TextAlignment.CENTER);
        text.setLayoutY(gamePane.getPrefHeight() / 2);
        text.setWrappingWidth(gamePane.getPrefWidth());
        gamePane.getChildren().add(text);
        timer.cancel();
    }
}
