import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class ListNumbersApp {
    private static final int N_THREADS = 1000;
    private static final int N_RUNS = 1000;
    private static final int WRITE_PROBABILITY = 30;
    private static final int N_NUMBERS = 1000;

    public static void main(String[] args) throws InterruptedException {
        Instant start = Instant.now();
        ExecutorService pool = Executors.newFixedThreadPool(N_THREADS);
        ListNumbers[] listNumbers = new ListNumbers[N_THREADS];

        List<Integer> numbers = new Random()
                .ints(N_NUMBERS)
                .boxed()
                .collect(Collectors.toList());
        ListNumbers.setNumbers(numbers);

        for (ListNumbers item : listNumbers) {
            item = new ListNumbers(N_RUNS, WRITE_PROBABILITY);
            pool.submit(item);
        }
        pool.shutdown();
        pool.awaitTermination(10, TimeUnit.SECONDS);

        System.out.println("Running time was: " + ChronoUnit.MILLIS.between(start, Instant.now()));
    }
}