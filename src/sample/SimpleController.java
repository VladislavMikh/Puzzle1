package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
// Контроллер сцены выйгрыша
public class SimpleController {

    @FXML
    private Rectangle Button;
    @FXML
    private Label Text;
    private Stage stage;

    public void link(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void initialize() {
        Button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.close();
            }
        });
        Text.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.close();
            }
        });
    }
}
