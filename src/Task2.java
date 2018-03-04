import java.util.concurrent.*;

public class Task2 {

    private static int size = 80 * 1000 * 1000;
    private static double result = 0;
    private static long a;
    private static int[] array;
    private static int treads = 4;
    private static Future<Double>[] futures = new Future[treads];

    public static class Compute implements Callable {
        private int start;
        private int count;

        private Compute(int start, int count) {
            this.start = start;
            this.count = count;
        }

        public Double call() {
            int end = start + count;
            double result = 0;
            for (int i = start; i < end; i++) {
                result += Math.sin(array[i]) + Math.cos(array[i]);
            }
            return result;
        }
    }

    private static int[] arrCreate(int size) {
        int[] temp = new int[size];
        for (int i = 0; i < size; i++) {
            temp[i] = i + 1;
        }
        return temp;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        array = arrCreate(size);

        ExecutorService pool = Executors.newFixedThreadPool(treads);

        int start = 0;
        int part = 1000 * 1000;
        int div = size / part / treads;
        result = 0;

        for (int j = 0; j < div; j++) {
            for (int i = 0; i < treads; i++) {
                futures[i] = pool.submit(new Compute(start, part));
                start += part;
            }
            for (int i = 0; i < treads; i++) {
                result += futures[i].get();
            }
        }

        System.out.println("Result is: " + result);

        pool.shutdown();
    }
}
