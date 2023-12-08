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

        //Part 1 & 2
        List<Integer> ids = new ArrayList<>();
        lines.forEach(line -> {
                    String[] arrayLine = line.split(": ");
                    int id = Integer.parseInt(arrayLine[0].split(" ")[1]);
                    String setsString = arrayLine[1];
                    String[] sets = setsString.split("; ");

                    boolean isValid = true;
                    int maxRed = 0;
                    int maxGreen = 0;
                    int maxBlue = 0;
                    for (int i = 0; i < sets.length; ++i) {
                        String[] set = sets[i].split(", ");
                        for (int j = 0; j < set.length; ++j) {
                            String[] x = set[j].split(" ");
                            int number = Integer.parseInt(x[0]);
                            String color = x[1];
                            switch (color) {
                                case "blue" : {
                                    if (maxBlue < number) {
                                        maxBlue = number;
                                    }
                                    break;
                                }
                                case "red" : {
                                    if (maxRed < number) {
                                        maxRed = number;
                                    }
                                    break;
                                }
                                case "green" : {
                                    if (maxGreen < number) {
                                        maxGreen = number;
                                    }
                                    break;
                                }
                            }
                        }
                    }
                    ids.add(maxRed * maxBlue * maxGreen);
                });


            System.out.println(ids.stream().mapToInt(item -> item).sum());
    }
}