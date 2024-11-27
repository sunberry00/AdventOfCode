import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class HotSprings {

    public record Spring(String map, List<Integer> groups) {}

    public static List<Integer> fromStringToGroups(String[] strAmounts) {
        return Arrays.stream(strAmounts).map(Integer::valueOf).toList();
    }

    public static List<Integer> fromStringToGroupsPartTwo(String[] strAmounts) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < 5; ++i) {
            result.addAll(Arrays.stream(strAmounts).map(Integer::valueOf).toList());
        }
        return result;
    }
    public static boolean isRightArrangement(String arrangement, List<Integer> groups) {
        String[] s = Arrays.stream(arrangement.split("\\.+")).filter(x -> !x.isEmpty()).toArray(String[]::new);;
        if (s.length == groups.size()) {
            for (int i = 0; i < s.length; ++i) {
                if (s[i].length() != groups.get(i)) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    public static long numberOfArrangements(Spring spring) {
        return numberOfArrangements(spring, new HashMap<>());
    }

    public static long numberOfArrangements(Spring spring, HashMap<Spring, Long> memo) {
        if (spring.map().indexOf('?') == -1) {
            if (isRightArrangement(spring.map(), spring.groups())) {
                return 1;
            } else {
                return 0;
            }
        }

        if (memo.containsKey(spring)) {
            return memo.get(spring);
        }

        long y = numberOfArrangements(new Spring(spring.map().replaceFirst("\\?", "."), spring.groups()), memo)
                + numberOfArrangements(new Spring(spring.map().replaceFirst("\\?", "#"), spring.groups()), memo);
        memo.put(spring, y);
        return y;
    }

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("./2023/Day12/example"));

        List<Spring> hotSprings = lines.parallelStream()
                .map(line -> new Spring(
                        (line.split(" ")[0]) + ("?" + line.split(" ")[0]).repeat(4),
                        fromStringToGroupsPartTwo(line.split(" ")[1].split(","))))
                .toList();

        System.out.println(hotSprings
                .parallelStream()
                .mapToLong(HotSprings::numberOfArrangements)
                .sum());
    }
}
