import java.util.List;
import java.util.Random;

public class ListNumbers extends Thread {
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 500;
    static List<Integer> numbers;
    private int nRuns;
    private int writeProbability;

    public ListNumbers(int nRuns, int writeProbability) {
        super();
        this.nRuns = nRuns;
        this.writeProbability = writeProbability;
    }

    public static void setNumbers(List numbers) {
        ListNumbers.numbers = numbers;
    }

    @Override
    public void run() {
        for (int i = 0; i < nRuns; i++) {
            if (getRandomNumber(0, 100) <= writeProbability) {
                insertNumber();
            } else {
                getNumber();
            }
        }
    }

    synchronized private Integer getNumber() {
        int size = numbers.size();
        int index = getRandomNumber(0, size);
        Integer res = numbers.get(index);
        return res;
    }

    synchronized private void insertNumber() {
        int size = numbers.size();
        int index = getRandomNumber(0, size);
        int number = getRandomNumber(MIN_NUMBER, MAX_NUMBER);
        numbers.add(index, number);
    }

    private int getRandomNumber(int min, int max) {
        return min + new Random().nextInt(max - min);
    }
}