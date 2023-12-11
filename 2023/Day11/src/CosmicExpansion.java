import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CosmicExpansion {


/*
    public static void firstPart(List<String> input) {
        //New Rows
        for (int i = 0; i < input.size(); ++i) {
            String currentRow = input.get(i);
            if (currentRow.matches("\\.+")) {
                for (int j = 0; j < 1000000; ++j) {
                    input.add(i, currentRow);
                }
                i += 1000000;
            }
        }

        List<String> toLeft = new ArrayList<>();
        for (int j = 0; j < input.get(0).length(); ++j) {
            StringBuilder column = new StringBuilder();
            for (int i = 0; i < input.size(); ++i) {
                column.append(input.get(input.size() - i - 1).charAt(j));
            }
            toLeft.add(column.toString());
        }

        for (int i = 0; i < toLeft.size(); ++i) {
            String currentRow = toLeft.get(i);
            if (currentRow.matches("\\.+")) {
                toLeft.add(i, currentRow);
                i++;
            }
        }

        List<String> galaxies = new ArrayList<>();
        for (int j = 0; j < toLeft.get(0).length(); ++j) {
            StringBuilder row = new StringBuilder();
            for (int i = 0; i < toLeft.size(); ++i) {
                row.append(toLeft.get(i).charAt(toLeft.get(i).length() - 1 - j));
            }
            galaxies.add(row.toString());
        }

        List<Galaxy> listOfGalaxies = new ArrayList<>();
        for (int i = 0; i < galaxies.size(); ++i) {
            for (int j = 0; j < galaxies.get(i).length(); ++j) {
                char currentChar = galaxies.get(i).charAt(j);
                if (currentChar == '#') {
                    listOfGalaxies.add(new Galaxy(i, j));
                }
            }
        }

        for (int i = 0; i < listOfGalaxies.size() - 1; ++i) {
            Galaxy currentGalaxy = listOfGalaxies.get(i);
            for (int j = i + 1; j < listOfGalaxies.size(); ++j) {
                Galaxy galaxyTo = listOfGalaxies.get(j);
                int distance = Math.abs(currentGalaxy.x - galaxyTo.x) + Math.abs(currentGalaxy.y - galaxyTo.y);
                currentGalaxy.distances.add(distance);
                galaxyTo.distances.add(distance);
            }
        }
       System.out.println(listOfGalaxies.stream().mapToInt(item -> item.distances.stream().mapToInt(x -> Integer.valueOf(x)).sum()).sum() / 2);
    }
*/
    public static void secondPart(List<String> input) {
        Set<Integer> emptyRows = new HashSet<>();
        for (int i = 0; i < input.size(); ++i) {
            if (input.get(i).matches("\\.+")) {
                emptyRows.add(i);
            }
        }

        Set<Integer> emptyColumns = new HashSet<>();
        for (int j = 0; j < input.get(0).length(); ++j) {
            StringBuilder currentColumn = new StringBuilder();
            for (int i = 0; i < input.size(); ++i) {
                currentColumn.append(input.get(i).charAt(j));
            }
            if (currentColumn.toString().matches("\\.+")) {
                emptyColumns.add(j);
            }
        }

        List<Galaxy> listOfGalaxies = new ArrayList<>();
        for (int i = 0; i < input.size(); ++i) {
            for (int j = 0; j < input.get(i).length(); ++j) {
                char currentChar = input.get(i).charAt(j);
                if (currentChar == '#') {
                    listOfGalaxies.add(new Galaxy(i, j));
                }
            }
        }

        for (int i = 0; i < listOfGalaxies.size() - 1; ++i) {
            Galaxy currentGalaxy = listOfGalaxies.get(i);
            for (int j = i + 1; j < listOfGalaxies.size(); ++j) {
                Galaxy galaxyTo = listOfGalaxies.get(j);
                long r = nums(emptyRows, currentGalaxy.x, galaxyTo.x) * 999999;
                long c = nums(emptyColumns, currentGalaxy.y, galaxyTo.y) * 999999;
                long distance = Math.abs(currentGalaxy.x - galaxyTo.x) + Math.abs(currentGalaxy.y - galaxyTo.y) + r + c;
                currentGalaxy.distances.add(distance);
                galaxyTo.distances.add(distance);
            }
        }
        System.out.println(listOfGalaxies.stream().mapToLong(item -> item.distances.stream().mapToLong(x -> Long.valueOf(x)).sum()).sum() / 2);
    }

    private static long nums(Set<Integer> empty, int start, int end) {
        int startTo = Math.min(start, end);
        int endTo = Math.max(start, end);
        int result = 0;
        for (int i = startTo; i <= endTo; ++i) {
            if (empty.contains(i)) {
                result++;
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("./2023/Day11/src/input"));
        //firstPart(lines);
       secondPart(lines);
    }
}
