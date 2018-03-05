import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Task3 {

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

        String path = "files/pictures.txt";
        fileLoader(path);

    }
}
