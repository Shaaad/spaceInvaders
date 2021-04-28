package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Asteroid extends Rectangle {
    final static int width = 20;
    final static int gamePaneWidth = 300;

    public Asteroid() {
        super();
        super.setHeight(20);
        super.setWidth(20);
        super.setFill(Color.RED);
        Random random = new Random();
        super.setX(random.nextInt(gamePaneWidth / width) * width);
        super.setY(random.nextInt(1));
    }
}
