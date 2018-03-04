
import java.util.Scanner;
import java.util.concurrent.*;

public class Task1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите число A:");
        final int a = scanner.nextInt();
        System.out.println("Введите число B:");
        final int b = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Какое математическое действие надо применить к этим числам: +, -, *, /, %, ==, >, <");
        final String action = scanner.nextLine();

        Callable<String> compute = () -> {
            String result = "Результат: ";
            if ("+".equals(action)) result += a + b;
            else if ("-".equals(action)) result += a - b;
            else if ("*".equals(action)) result += a * b;
            else if ("/".equals(action)) result += a / b;
            else if ("%".equals(action)) result += a % b;
            else if ("==".equals(action)) result += a == b;
            else if (">".equals(action)) result += a > b;
            else if ("<".equals(action)) result += a < b;
            else result = "Ошибка";
            return result;
        };

        ExecutorService service = Executors.newFixedThreadPool(2);
        Future<String> future = service.submit(compute);
        System.out.println(future.get());

        service.shutdown();


    }
}
