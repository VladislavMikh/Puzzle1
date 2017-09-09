package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
// Контроллер сцены мосновного меню
public class MenuController {

    @FXML
    private ImageView one;
    @FXML
    private ImageView two;
    @FXML
    private ImageView three;
    @FXML
    private ImageView four;
    @FXML
    private ImageView five;
    @FXML
    private ImageView six;
    @FXML
    private ImageView test;

    private Map<ImageNum, ImageView> buttons = new HashMap<>();
    private Main main;

    @FXML
    public void initialize() {
        buttons.put(ImageNum.ONE, one);
        buttons.put(ImageNum.TWO, two);
        buttons.put(ImageNum.THREE, three);
        buttons.put(ImageNum.FOUR, four);
        buttons.put(ImageNum.FIVE, five);
        buttons.put(ImageNum.SIX, six);
        buttons.put(ImageNum.TEST, test);
    }

    public void link(Main main) {
        this.main = main;
        for (Map.Entry<ImageNum, ImageView> entry : buttons.entrySet()) {
            entry.getValue().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    try {
                        main.startGame(entry.getKey().number);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    enum ImageNum {
        ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), TEST(0);

        public int number;

        ImageNum(int number) {
            this.number = number;
        }
    }
}
