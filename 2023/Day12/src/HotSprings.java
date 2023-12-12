import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class HotSprings {

    public record Spring(String map, List<Integer> groups) {}
    public record Key(int currentIndex, int currentGroup, int sizeOfBlocks) {}
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

    public static long getNumberOfArrangements(Spring spring) {
        return getNumberOfArrangements(spring.map(), spring.groups(), new HashMap<>(), 0, 0, 0);
    }

    public static long getNumberOfArrangements(String repr, List<Integer> groups, HashMap<Key, Long> memo, int currentIndex, int currentGroup, int sizeOfBlocks) {
        Key key = new Key(currentIndex, currentGroup, sizeOfBlocks);
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        if (currentIndex == repr.length()) {
            if (currentGroup == groups.size() && sizeOfBlocks == 0) {
                return 1;
            } else if (currentGroup == groups.size() - 1 && groups.get(currentGroup) == sizeOfBlocks) {
                return 1;
            } else {
                return 0;
            }
        }

        long numberOfArrangements = 0;
        char[] possibilities = {'.', '#'};
        for (char c : possibilities) {
            if (repr.charAt(currentIndex) == c || repr.charAt(currentIndex) == '?') {
                if (c == '.' && sizeOfBlocks == 0) {
                    numberOfArrangements += getNumberOfArrangements(repr, groups, memo, currentIndex + 1, currentGroup, 0);
                } else if (c == '.' && sizeOfBlocks > 0 && currentGroup < groups.size() && groups.get(currentGroup) == sizeOfBlocks) {
                    numberOfArrangements += getNumberOfArrangements(repr, groups, memo, currentIndex + 1, currentGroup + 1, 0);
                } else if (c == '#') {
                    numberOfArrangements += getNumberOfArrangements(repr, groups, memo, currentIndex + 1, currentGroup, sizeOfBlocks + 1);
                }
            }
        }
        memo.put(key, numberOfArrangements);
        return numberOfArrangements;
    }

    public static long numberOfArrangements(Spring spring) {
        if (spring.map().indexOf('?') == -1) {
            if (isRightArrangement(spring.map(), spring.groups())) {
                return 1;
            } else {
                return 0;
            }
        }

        long y = numberOfArrangements(new Spring(spring.map().replaceFirst("\\?", "."), spring.groups()))
                + numberOfArrangements(new Spring(spring.map().replaceFirst("\\?", "#"), spring.groups()));
        return y;
    }

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("./2023/Day12/input"));

        List<Spring> hotSprings = lines.parallelStream()
                .map(line -> new Spring(
                        (line.split(" ")[0]) + ("?" + line.split(" ")[0]).repeat(4),
                        fromStringToGroupsPartTwo(line.split(" ")[1].split(","))))
                .toList();

        //Part 1
        System.out.println(hotSprings
                .parallelStream()
                .mapToLong(HotSprings::getNumberOfArrangements)
                .sum());


    }
}
