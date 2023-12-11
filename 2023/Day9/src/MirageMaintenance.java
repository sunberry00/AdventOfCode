import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Predicate;

public class MirageMaintenance {


    public static void firstPart(List<String> input) {
        List<List<Integer>> sequences = new ArrayList<>();
        for (String line : input) {
            String[] numbers = line.split(" ");
            sequences.add(new ArrayList<>());
            Arrays.stream(numbers).forEach(item -> sequences.get(sequences.size() - 1).add(Integer.parseInt(item)));
        }

        Predicate<List<Integer>> allZeros = x -> x.stream().anyMatch(item -> item != 0);

        long sum = 0;
        for (List<Integer> sequence : sequences) {
            List<List<Integer>> differences = new ArrayList<>(); //List of differences
            List<Integer> currentSequence = List.copyOf(sequence); //Start Sequence

            do {
                differences.add(new ArrayList<>());
                for (int i = 0; i < currentSequence.size() - 1; ++i) {
                    differences.getLast().add(currentSequence.get(i + 1) - currentSequence.get(i));
                }
                currentSequence = List.copyOf(differences.getLast());
            } while (allZeros.test(differences.getLast()));

            for (int i = differences.size() - 1; i > 0; --i) {
                int intToAdd = differences.get(i).getLast() + differences.get(i - 1).getLast();
                differences.get(i - 1).add(intToAdd);
            }
            sum += sequence.getLast() + differences.getFirst().getLast();
        }
        System.out.println(sum);
    }

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("./2023/Day9/src/input"));
        firstPart(lines);
    }
}