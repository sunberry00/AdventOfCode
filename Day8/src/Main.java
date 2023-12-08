import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;

public class Main {

    public static void firstPart(List<String> input) {
        List<String> instructions = Arrays.stream(input.getFirst().split("")).toList();
        Map<String, Node> network = new TreeMap<>();
        String start = "AAA";

        input.stream().skip(2).forEach(item -> {
            String[] inp = item.split(" ");
            network.put(inp[0], new Node(inp[2] + inp[3]));
        });

        long steps = 0;

        BiFunction<String, String, String> getNext = (node, direction)  -> network.get(node).getNext(direction);

        while(!start.equals("ZZZ")) {
            for (int i = 0; i < instructions.size(); ++i) {
                start = getNext.apply(start, instructions.get(i));
                steps++;
                if (start.equals("ZZZ")) {
                    break;
                }
            }
        }
        System.out.println(steps);
    }
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("./Day8/src/input"));
        firstPart(lines);
    }
}