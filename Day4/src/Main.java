import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {

    public static void partOne(List<String> input) {
        int sum = input.stream()
                .mapToInt(item -> {
                    Card card = new Card(item);
                    return card.getPoints();
                }).sum();
        //System.out.println(sum);
    }

    public static void partTwo(List<String> input) {
        List<Card> cards = input.stream().map(Card::new).toList();

        int[] copies = new int[cards.size() + 1];

        copies[1] = 1; //First card (Original)
        int result = 0;
        int id = 1;
        while(copies[id] != 0) {
            result++;
            copies[id]--;
            int nextCards = cards.get(id - 1).getNumberOfCopies();
            for (int j = 1; j <= nextCards; ++j) {
                if (id + j < copies.length) {
                    copies[id + j]++;
                }
            }
            if (copies[id] == 0) {
                if (id + 1 < copies.length) {
                    copies[++id]++;
                }
            }
        }

        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("./Day4/src/input"));
        partOne(lines);
        partTwo(lines);
    }
}