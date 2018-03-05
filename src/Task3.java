import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Task3 extends Application {

    private static final double WIDTH = 800;                // Ширина окна
    private static final double HEIGHT = 800;               // Высота окна
    private static final double X_OFFSET = 25;
    private static final double Y_OFFSET = 50;
    private static Random rnd = new Random();

    private static ArrayList<String> listOfURL = new ArrayList<>();

    private static void fileLoader(String path) {
        try {
            FileInputStream stream = new FileInputStream(path);
            Scanner scanner = new Scanner(stream);
            while (scanner.hasNext()) {
                listOfURL.add(scanner.nextLine());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void show(Pane root) {
        ExecutorService pool = Executors.newFixedThreadPool(25);
        int square = 5;
        int width = 145;
        int step = 150;
        int end = step * square;
        ArrayList<Future<ImageView>> list = new ArrayList<>();

        for (int y = 0; y < end; y += step) {
            for (int x = 0; x < end; x += step) {
                final int fx = x;
                final int fy = y;
                pool.submit(() -> {
                    Image image = new Image(listOfURL.get(rnd.nextInt(50)));
                    ImageView imageView = new ImageView(image);
                    imageView.setTranslateX(X_OFFSET + fx);
                    imageView.setTranslateY(Y_OFFSET + fy);
                    imageView.setFitWidth(width);
                    imageView.setPreserveRatio(true);
                    Platform.runLater(() -> root.getChildren().addAll(imageView));
                });


            }
        }

        pool.shutdown();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        String path = "files/pictures.txt";
        fileLoader(path);

        Pane root = new Pane();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        Pane pane = new Pane();
        root.getChildren().addAll(pane);
        primaryStage.setTitle("Picture Viewer");
        primaryStage.setResizable(false);
        Image icon = new Image(getClass().getResourceAsStream("icon/sun.png"));
        primaryStage.getIcons().add(icon);

        show(pane);

        Button button = new Button("Обновить");
        button.setLayoutX(25);
        button.setLayoutY(10);
        button.setPrefSize(100, 25);
        button.setOnAction(event -> {
            pane.getChildren().clear();
            show(pane);
        });
        root.getChildren().add(button);


        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
