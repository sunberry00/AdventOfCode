import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

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
            for (String instruction : instructions) {
                start = getNext.apply(start, instruction);
                steps++;
                if (start.equals("ZZZ")) {
                    break;
                }
            }
        }
        System.out.println(steps);
    }

    public static long ggT(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static void secondPart(List<String> input) {
        List<String> instructions = Arrays.stream(input.getFirst().split("")).toList();
        Map<String, Node> network = new TreeMap<>();

        input.stream().skip(2).forEach(item -> {
            String[] inp = item.split(" ");
            network.put(inp[0], new Node(inp[2] + inp[3]));
        });

        Set<String> startNodes =
                network.keySet()
                .stream()
                .filter(item -> item.charAt(2) == 'A')
                .collect(Collectors.toSet());

        Function<String, Long> getNumberOfNexts = getStringLongFunction(network, instructions);

        long result = startNodes
                .stream()
                .mapToLong(getNumberOfNexts::apply)
                .reduce(1, (x, y) -> (x * y) / ggT(x, y));

        System.out.println(result);

    }

    private static Function<String, Long> getStringLongFunction(Map<String, Node> network, List<String> instructions) {
        BiFunction<String, String, String> getNextNode = (node, direction)  -> network.get(node).getNext(direction);

        return node -> {
            long steps = 0;
            while(node.charAt(2) != 'Z') {
                for (String instruction : instructions) {
                    node = getNextNode.apply(node, instruction);
                    steps++;
                    if (node.charAt(2) == 'Z') {
                        break;
                    }
                }
            }
            return steps;
        };
    }

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("./2023/Day8/src/input"));
        //firstPart(lines);
        secondPart(lines);
    }
}