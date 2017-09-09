package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.IOException;

// Контроллер игровой сцены
public class Controller {
    Pane root;
    Main main;
    boolean waitForSwap = false;
    Part partForSwap;
    int swapCount = 0;
    @FXML
    private Label Score;
    @FXML
    private ImageView thumb;
    @FXML
    private Button menu;

    @FXML
    public void link(Main main,Pane root, int number) {
        this.root = root;
        this.main = main;
        thumb.setImage(new Image("file:images/thumbnails/" + number + ".jpg"));
        root.getChildren().stream().forEach(N -> { if (N instanceof Part) {
            Part rectangle = (Part) N;
            rectangle.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (!waitForSwap) {
                        partForSwap = (Part) event.getSource();
                        partForSwap.setStroke(Color.RED);
                        partForSwap.setStrokeWidth(5);
                        waitForSwap = true;
                    } else if (waitForSwap && partForSwap != null && partForSwap != event.getSource()) {
                        Part part = (Part) event.getSource();
                        double tmpX = partForSwap.getX();
                        double tmpY = partForSwap.getY();
                        partForSwap.setCoords(part.getX(), part.getY());
                        part.setCoords(tmpX, tmpY);
                        waitForSwap = false;
                        partForSwap.setStroke(Color.YELLOW);
                        partForSwap.setStrokeWidth(1);
                        partForSwap = null;
                        swapCount++;
                        Score.setText("Ходов: " + swapCount);
                        if (checkPos()) {
                            root.getChildren().stream().forEach(N -> { if (N instanceof Part) {
                                Part p = (Part) N;
                                p.setStroke(Color.TRANSPARENT);
                            }});
                            main.win();
                        }
                    }
                    }
                });
            }
        });
        menu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    main.showMenu();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private boolean checkPos() {
        return root.getChildren().stream().allMatch(N -> { if (N instanceof Part) {
           Part part = (Part) N;
           return part.isRightPlace();
        } return true; } );
    }
}
