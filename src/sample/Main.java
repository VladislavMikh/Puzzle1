package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


public class Main extends Application {

    final int ratio = 240;
    final int halfRatio = ratio / 2;

    Stage primary;
    Pane inGame;
    Pane menu;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primary = primaryStage;
        primaryStage.setTitle("Puzzle");

        showMenu();

        primaryStage.show();
    }

    private void setSplittedImage(int number) {
        Image image = new Image("file:images/" + number + ".jpg");
        PixelReader reader = image.getPixelReader();
        List<Part> parts = new LinkedList<>();
        // Сеттинг. Разрезает картинку на квадраты
        for (int i = 0; i < (1920 / ratio); i++) {
            for (int j = 0; j < (1200 / ratio); j++) {
                WritableImage slice = new WritableImage(reader, ratio * i, ratio * j, ratio, ratio);
                Part rectangle = new Part(halfRatio * i - halfRatio / 2, halfRatio * j - halfRatio / 2, ratio, ratio,
                        halfRatio * i - halfRatio / 2, halfRatio * j - halfRatio / 2);
                rectangle.setFill(new ImagePattern(slice));
                rectangle.setStroke(Color.YELLOW);
                rectangle.setScaleX(0.5);
                rectangle.setScaleY(0.5);
                parts.add(rectangle);
            }
        }
        // Перемешивает квадраты, расставляет их
        for (int i = 0; i < (1920 / ratio); i++) {
            for (int j = 0; j < (1200 / ratio); j++) {
                Random random = new Random();
                int index = random.nextInt(parts.size());
                Part part = parts.remove(index);
                part.setCoords(halfRatio * i - halfRatio / 2, halfRatio * j - halfRatio / 2);
                inGame.getChildren().add(part);
            }
        }
    }
    // Описание 3х сцен. Меню, игровая сцена, сцена выйгрыша
    public void showMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
        Pane menu = loader.load();
        this.menu = menu;
        MenuController controller = loader.getController();
        controller.link(this);
        primary.setScene(new Scene(menu));
    }

    public void startGame(int number) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Pane inGame = loader.load();
        this.inGame = inGame;
        Controller controller = loader.getController();
        setSplittedImage(number);
        controller.link(this, inGame, number);
        primary.setScene(new Scene(inGame, 1060, 600));
    }

    public void win() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("win.fxml"));
            Pane page = loader.load();
            SimpleController controller = loader.getController();
            Stage dialog = new Stage();
            controller.link(dialog);
            dialog.initModality(Modality.WINDOW_MODAL);
            dialog.initStyle(StageStyle.UNDECORATED);
            dialog.initOwner(primary);
            Scene scene = new Scene(page);
            dialog.setScene(scene);
            dialog.setResizable(false);
            dialog.showAndWait();
            showMenu();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        launch(args);
    }
}
