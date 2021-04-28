package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Ship extends Rectangle {

    final static int width = 20;
    final static int gamePaneWidth = 300;
    final static int gamePaneHeight = 300;

    public Ship() {
        super();
        super.setHeight(20);
        super.setWidth(20);
        super.setFill(Color.BLUE);
        super.setX(((gamePaneWidth - width) / 2));
        super.setY(gamePaneHeight - width);
    }
}
