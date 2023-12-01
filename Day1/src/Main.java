import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("./Day1/src/input"));

        //Part 1
        long sum = lines.stream()
                .mapToInt(line -> {
                    String[] digits = Arrays.stream(line.split(""))
                            .filter(d -> d.matches("\\d"))
                            .toArray(String[]::new);
                    return digits.length != 1
                            ? Integer.parseInt(digits[0] + digits[digits.length - 1])
                            : Integer.parseInt(digits[0] + digits[0]);
                }).sum();



    }
}