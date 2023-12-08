import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Card {
    private final Set<Integer> winningNumbers;
    private final Set<Integer> myNumbers;
    private int numberOfCopies;
    private int points;

    public Card(String line) {
        this.winningNumbers = new HashSet<>();
        this.myNumbers = new HashSet<>();

        String[] parts = line.split(" \\| ");
        Arrays.stream(parts[0].split("\\D+"))
                .filter(item -> !item.isEmpty())
                .mapToInt(Integer::parseInt)
                .skip(1) //ID
                .forEach(this.winningNumbers::add);
        Arrays.stream(parts[1].split("\\D+"))
                .filter(item -> !item.isEmpty())
                .mapToInt(Integer::parseInt)
                .forEach(this.myNumbers::add);
        points();
    }

    private void points() {
        int p = 0;
        for (int number : myNumbers) {
            if (winningNumbers.contains(number)) {
                p++;
            }
        }
        this.points = p == 0 ? 0 : (int) Math.pow(2, p - 1);
        this.numberOfCopies = p;
    }

    public int getPoints() {
        return points;
    }

    public int getNumberOfCopies() {
        return numberOfCopies;
    }
}
