import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;

public class Task1 {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        //Асинхронна генерація одновимірного масиву з 10 чисел
        CompletableFuture<int[]> arrayFuture = CompletableFuture.supplyAsync(() -> {
            long taskStartTime = System.currentTimeMillis();
            int[] array = new int[10];
            for (int i = 0; i < 10; i++) {
                array[i] = ThreadLocalRandom.current().nextInt(1, 90);          // Генерація чисел
            }
            System.out.println("Створений масив: " + Arrays.toString(array));
            System.out.println("Створення масиву виконано за: " + (System.currentTimeMillis() - taskStartTime) + " мс");
            return array;
        });

        //Додавання 10 до кожного елементу
        CompletableFuture<int[]> modifiedFuture = arrayFuture.thenApplyAsync(array -> {
            long taskStartTime = System.currentTimeMillis();
            for (int i = 0; i < array.length; i++) {
                array[i] += 10;
            }
            System.out.println("Масив після додавання 10: " + Arrays.toString(array));
            System.out.println("Додавання виконано за: " + (System.currentTimeMillis() - taskStartTime) + " мс");
            return array;
        });

        //Ділення кожного елементу на 2
        CompletableFuture<double[]> dividedFuture = modifiedFuture.thenApplyAsync(array -> {
            long taskStartTime = System.currentTimeMillis();
            double[] dividedArray = new double[array.length];
            for (int i = 0; i < array.length; i++) {
                dividedArray[i] = array[i] / 2.0;
            }
            System.out.println("Масив після ділення: " + Arrays.toString(dividedArray));
            System.out.println("Ділення виконано за: " + (System.currentTimeMillis() - taskStartTime) + " мс");
            return dividedArray;
        });

        //Вивід результату
        CompletableFuture<Void> printFuture = dividedFuture.thenAcceptAsync(dividedArray -> {
            long taskStartTime = System.currentTimeMillis();
            System.out.println("Фінальний вигляд масиву: " + Arrays.toString(dividedArray));
            System.out.println("Вивід виконано за : " + (System.currentTimeMillis() - taskStartTime) + " мс");
        });

        //Завершальна дія
        printFuture.thenRunAsync(() -> {
            System.out.println("Все завдання виконано за: " + (System.currentTimeMillis() - start) + " мс");
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}