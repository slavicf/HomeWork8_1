import java.util.ArrayList;
import java.util.concurrent.*;

public class Task2 {

    private static int[] array;

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

        int size = 80000000;
        double result = 0;
        array = arrCreate(size);
        int threads = 32;
        ArrayList<Future<Double>> list = new ArrayList<>();

        ExecutorService pool = Executors.newFixedThreadPool(threads);

        long startTime = System.currentTimeMillis();
        int part = size / threads;
        for (int i = 0; i < size; i += part) {
            @SuppressWarnings("unchecked")
            Callable<Double> callable = new Compute(i, part);
            Future<Double> future = pool.submit(callable);
            list.add(future);
        }
        for (Future<Double> future : list) {
            result += future.get();
        }
        long endTime = System.currentTimeMillis();

        System.out.println("Result is: " + result);
        System.out.println( "Calculated in " + ((float) (endTime - startTime) / 1000) + " seconds");

        pool.shutdown();
    }
}
