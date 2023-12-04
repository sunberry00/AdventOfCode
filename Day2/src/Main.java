import java.awt.image.AreaAveragingScaleFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("./Day2/src/input"));

        //Part 1
        List<Integer> ids = new ArrayList<>();
        lines.forEach(line -> {
                    String[] arrayLine = line.split(": ");
                    int id = Integer.parseInt(arrayLine[0].split(" ")[1]);
                    String setsString = arrayLine[1];
                    String[] sets = setsString.split("; ");

                    boolean isValid = true;
                    for (int i = 0; i < sets.length; ++i) {
                        String[] set = sets[i].split(", ");
                        for (int j = 0; j < set.length; ++j) {
                            String[] x = set[j].split(" ");
                            int number = Integer.parseInt(x[0]);
                            String color = x[1];
                            switch (color) {
                                case "blue" : {
                                    if (number > 14) {
                                        isValid = false;
                                    }
                                    break;
                                }
                                case "red" : {
                                    if (number > 12) {
                                        isValid = false;
                                    }
                                    break;
                                }
                                case "green" : {
                                    if (number > 13) {
                                        isValid = false;
                                    }
                                    break;
                                }
                            }
                        }
                    }
                    if (isValid) {
                        ids.add(id);
                    }
                });


            System.out.println(ids.stream().mapToInt(item -> item).sum());
    }
}