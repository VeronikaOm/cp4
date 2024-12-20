import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class Task2 {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        // Генерація початкової послідовності з 20 дійсних чисел
        CompletableFuture<double[]> sequenceFuture = CompletableFuture.supplyAsync(() -> {
            long taskStartTime = System.currentTimeMillis();
            double[] sequence = IntStream.range(0, 20)
                    .mapToDouble(i -> ThreadLocalRandom.current().nextDouble(1, 100))
                    .toArray();
            System.out.println("Згенерована послідовність: ");
            for (double num : sequence) {
                System.out.printf("%.2f |", num);
            }
            System.out.println("\nГенерацію послідовності завершено за: " + (System.currentTimeMillis() - taskStartTime) + " мс\n");
            return sequence;
        });

        // Обчислення (a2 - a1) * (a3 - a2) * ... * (an - an- 1)
        CompletableFuture<Double> resultFuture = sequenceFuture.thenApplyAsync(sequence -> {
            long taskStartTime = System.currentTimeMillis();
            double result = 1.0;
            for (int i = 1; i < sequence.length; i++) {
                result *= (sequence[i] - sequence[i - 1]);
            }
            System.out.println("Результат обчислення: " + result);
            System.out.println("Обчислення завершено за: " + (System.currentTimeMillis() - taskStartTime) + " мс\n");
            return result;
        });

        // Вивід фінального результату з інформаційним повідомленням
        CompletableFuture<Void> printFuture = resultFuture.thenAcceptAsync(result -> {
            long taskStartTime = System.currentTimeMillis();
            System.out.printf("Фінальний результат обчислення: %.5f\n", result);
            System.out.println("Вивід фінального результату завершено за: " + (System.currentTimeMillis() - taskStartTime) + " мс\n");
        });

        // Завершальна дія
        printFuture.thenRunAsync(() -> {
            System.out.println("Всі задачі завершено за: " + (System.currentTimeMillis() - startTime) + " мс");
        });

        // Чекаємо завершення всіх задач
        try {
            Thread.sleep(3000); // Для очікування виконання асинхронних задач
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

