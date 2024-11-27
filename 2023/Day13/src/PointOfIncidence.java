import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class PointOfIncidence {
    public static void firstPart(List<String> lines) {
        List<Pattern> patterns = new ArrayList<>();
        for (int i = 0; i < lines.size(); ++i) {
            List<String> currentPattern = new ArrayList<>();
            while (i != lines.size() && !lines.get(i).isEmpty()) {
                currentPattern.add(lines.get(i));
                i++;
            }
            patterns.add(new Pattern(currentPattern));
        }

        var t = patterns.stream()
                .mapToInt(Pattern::getNumber)
                .sum();

        System.out.println(t);
    }


    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("./2023/Day13/input"));
        firstPart(lines);
    }
}