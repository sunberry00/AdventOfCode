import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void firstPart(List<String> input) {
        String[] times = input.get(0).split(" +");
        String[] distances = input.get(1).split(" +");
        List<Race> races = new ArrayList<>();
        for (int i = 1; i < times.length; ++i) {
            races.add(new Race(Integer.parseInt(times[i]), Integer.parseInt(distances[i])));
        }

        RaceFunction function = new RaceFunction();

        int sum = races.stream()
                .mapToInt(function::apply)
                .reduce(1, (x, y) -> x * y);

        System.out.println(sum);
    }

    public static void secondPart(List<String> input) {

    }

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("./Day6/src/input"));
        firstPart(lines);
    }
}