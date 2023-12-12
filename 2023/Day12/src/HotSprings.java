import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

public class HotSprings {

    public static void firstPart(List<String> lines) {
        List<Springs> inputSprings = lines
                .stream()
                .map(item -> new Springs(item.split(" ")[0], item.split(" ")[1].split(",")))
                .toList();

        Function<String[], String[]> allPossibleArrangements = arr ->
                IntStream.range(0, arr.length)
                .mapToObj(i -> i < arr.length - 1 ?
                        new String[]{arr[i] + "#", arr[i] + "."} :
                        new String[]{arr[i]})
                .reduce((arr1, arr2) -> Arrays.stream(arr1)
                        .flatMap(str1 -> Arrays.stream(arr2)
                                .map(str2 -> str1 + str2))
                        .toArray(String[]::new))
                .orElse(null);

        for (Springs spr : inputSprings) {
            int numberOfQuestions = (int) Arrays.stream(spr.getRepresentation().split("")).filter(ch -> ch.equals("?")).count();
            String[] splitedStr = spr.getRepresentation().split("\\?", (int) Math.pow(2, numberOfQuestions));
            String[] res = allPossibleArrangements.apply(splitedStr);
            spr.setArrangements(res);
        }


        System.out.println(
                inputSprings.parallelStream()
                .mapToInt(Springs::countArrangements)
                .sum());

    }
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("./2023/Day12/input"));
        firstPart(lines);
    }
}
