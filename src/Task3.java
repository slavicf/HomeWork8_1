import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Task3 extends Application {

    private static final double WIDTH = 800;                // Ширина окна
    private static final double HEIGHT = 800;               // Высота окна
    private static ArrayList<String> pictureList = new ArrayList<>();

    private static void fileLoader(String path) {
        try {
            FileInputStream stream = new FileInputStream(path);
            Scanner scanner = new Scanner(stream);
            while (scanner.hasNext()) {
                pictureList.add(scanner.nextLine());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        String path = "files/pictures.txt";
        fileLoader(path);

        Pane root = new Pane();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        Pane pane = new Pane();
        root.getChildren().addAll(pane);
        primaryStage.setTitle("Picture Viewer");
        primaryStage.setResizable(false);

        Image image = new Image(pictureList.get(0));
        ImageView imageView = new ImageView(image);
//        Platform.runLater(() -> {
            imageView.setTranslateX(100);
            imageView.setFitWidth(120);
            imageView.setPreserveRatio(true);
            pane.getChildren().addAll(imageView);



        primaryStage.setScene(scene);
        primaryStage.show();
        }
}
