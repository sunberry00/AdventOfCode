import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.IntBinaryOperator;

public class LensLibrary {
    public static void firstPart(List<String> inputLine) {
        IntBinaryOperator accumulator = (x, y) -> {
            return (x + y) * 17 % 256;
        };

        long sum = inputLine.
                stream()
                .mapToLong(x -> x.chars()
                                 .reduce(0, accumulator))
                .sum();

        System.out.println(sum);
    }

    public static void secondPart(List<String> input) {
        List<Lens> lenses = input
                .stream()
                .map(line -> new Lens(line))
                .toList();

        Map<Integer, List<Lens>> boxes = new HashMap<>();

        for (int i = 0; i < 256; ++i) {
            boxes.put(i, new ArrayList<>());
        }

        for (Lens lens : lenses) {
            if (lens.getOperation() == '-') {
                boxes.get(lens.getBox()).remove(lens);
                boxes.put(lens.getBox(), new ArrayList<>(boxes.get(lens.getBox()).stream().filter(x -> x != null).toList()));
            } else {
                if (boxes.get(lens.getBox()).contains(lens)) {
                    int index = boxes.get(lens.getBox()).indexOf(lens);
                    boxes.get(lens.getBox()).remove(lens);
                    boxes.get(lens.getBox()).add(index, lens);
                } else {
                    boxes.get(lens.getBox()).add(lens);
                }
            }
        }

        long sum = 0;
        for (int i = 0; i < boxes.size(); ++i) {
            List<Lens> currentBox = boxes.get(i);
            if (!currentBox.isEmpty()) {
                for (int j = 0; j < currentBox.size(); ++j) {
                    Lens currentLens = currentBox.get(j);
                    sum += (i + 1) * (j + 1) * currentLens.getFocalLength();
                }
            }
        }


        System.out.println(sum);
    }

    public static void main(String[] args) throws IOException {
        List<String> inputLine = Arrays
                .stream(Files.readAllLines(Path.of("./2023/Day15/input"))
                .getFirst().split(","))
                .toList();
        //firstPart(inputLine);
        secondPart(inputLine);
    }
}
