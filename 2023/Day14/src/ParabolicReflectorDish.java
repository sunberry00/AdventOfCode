import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.replaceAll;
import static java.util.Collections.sort;

public class ParabolicReflectorDish {

    public static List<Puzzle> parseInput(List<String> lines) {
        List<Puzzle> inputs = new ArrayList<>();
        for (int i = 0; i < lines.size(); ++i) {
            List<String> currentPuzzle = new ArrayList<>();
            while (i != lines.size() && !lines.get(i).isEmpty()) {
                currentPuzzle.add(lines.get(i));
                i++;
            }
            inputs.add(new Puzzle(currentPuzzle));
        }
        return inputs;
    }

    public static List<String> splitByBlocks(String str) {
        List<String> result = new ArrayList<>();
        StringBuilder block = new StringBuilder();
        for (int i = 0; i < str.length(); ++i) {
            if (str.charAt(i) != '#') {
                if (block.indexOf("#") == -1) {
                    block.append(str.charAt(i));
                } else {
                    result.add(block.toString());
                    block = new StringBuilder();
                    block.append(str.charAt(i));
                }
            } else {
                if (block.indexOf("#") == -1) {
                    result.add(block.toString());
                    block = new StringBuilder();
                }
                    block.append(str.charAt(i));
            }
        }
        result.add(block.toString());
        return result;
    }

    public static String sortBlock(String block) {
        if (block.indexOf('#') == -1) {
            int numberRocks = (int) Arrays.stream(block.split("")).filter(x -> x.equals("O")).count();
            int numberEmptySpaces = (int) Arrays.stream(block.split("")).filter(x -> x.equals(".")).count();
            return "O".repeat(numberRocks) + ".".repeat(numberEmptySpaces);
        } else {
            return block;
        }
    }

    public static void firstPart(List<String> lines) {
        List<String> flippedPuzzle = Puzzle.flipPuzzle(lines);

        List<String> tiltedPuzzle = flippedPuzzle.parallelStream()
                                .map(line -> splitByBlocks(line)
                                        .parallelStream()
                                        .map(ParabolicReflectorDish::sortBlock)
                                        .collect(Collectors.joining()))
                                        .toList();

        List<String> result = new ArrayList<>();
        for (int i = 0; i < tiltedPuzzle.size(); ++i) {
            result = Puzzle.flipPuzzle(tiltedPuzzle);
        }

        long sum = 0;
        for (int i = 0; i < result.size(); ++i) {
            String currentLine = result.get(i);
            long numberRocks = Arrays.stream(currentLine.split("")).filter(x -> x.equals("O")).count();
            sum += (numberRocks * (result.size() - i));
        }
        System.out.println(sum);

    }

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("./2023/Day14/input"));
        firstPart(lines);
    }
}
