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
        System.out.println(sum);
    }

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("./Day4/src/input"));
        partOne(lines);

    }
}