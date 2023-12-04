import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;


public class Main {
    public static String replacer(String str) {
        Map<String, String> digits = new HashMap<>();
        digits.put("one", "o1e");
        digits.put("two", "t2o");
        digits.put("three", "t3e");
        digits.put("four", "f4r");
        digits.put("five", "f5e");
        digits.put("six", "s6x");
        digits.put("seven", "s7n");
        digits.put("eight", "e8t");
        digits.put("nine", "n9e");
        for (String key : digits.keySet()) {
            if (str.contains(key)) {
                str = str.replace(key, digits.get(key));
            }
        }
        return str;
    }
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("./Day1/src/input"));

        //Part 1
        long firstPart = lines.stream()
                .mapToInt(line -> {
                    String[] digits = Arrays.stream(line.split(""))
                            .filter(d -> d.matches("\\d"))
                            .toArray(String[]::new);
                    return digits.length != 1
                            ? Integer.parseInt(digits[0] + digits[digits.length - 1])
                            : Integer.parseInt(digits[0] + digits[0]);
                }).sum();

        //Part 2
        int[] secondPart = lines.stream()
                .mapToInt(line -> {
                    String[] digits = Arrays.stream(replacer(line).split(""))
                            .filter(d -> d.matches("\\d"))
                            .toArray(String[]::new);
                    return digits.length != 1
                            ? Integer.parseInt(digits[0] + digits[digits.length - 1])
                            : Integer.parseInt(digits[0] + digits[0]);
                }).toArray();

        System.out.println(Arrays.stream(secondPart).sum());

    }
}