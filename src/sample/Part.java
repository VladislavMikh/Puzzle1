package sample;

import javafx.scene.shape.Rectangle;
// Класс для хранения свойств квадратов, на которые разбита картинка
public class Part extends Rectangle {
    final double originalX;
    final double originalY;

    public Part(double x, double y, double width, double height, double originalX, double originalY) {
        super(x, y, width, height);
        this.originalX = originalX;
        this.originalY = originalY;
    }
    public void setCoords(double x, double y) {
        this.setX(x);
        this.setY(y);
    }
    public boolean isRightPlace() {
        return (this.getX() == originalX && this.getY() == originalY);
    }
}
