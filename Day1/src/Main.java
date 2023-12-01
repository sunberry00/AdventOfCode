import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("input"));

        long sum = lines.stream()
                .mapToInt(line -> {
                    String[] digits = line.split("\\D");
                    return digits.length != 1
                            ? Integer.parseInt(digits[0]) + Integer.parseInt(digits[digits.length - 1])
                            : Integer.parseInt(digits[0]) * 2;
                }).sum();

        System.out.println(sum);
    }
}