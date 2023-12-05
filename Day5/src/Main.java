import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static void readSeeds(String[] seedsFromFile, List<Long> seeds) {
        for (int i = 1; i < seedsFromFile.length; ++i) {
            seeds.add(Long.parseLong(seedsFromFile[i]));
        }
    }

    private static Source parseSource(String source) {
        return switch (source) {
            case "seed" -> Source.SEED;
            case "soil" -> Source.SOIL;
            case "fertilizer" -> Source.FERTILIZER;
            case "water" -> Source.WATER;
            case "light" -> Source.LIGHT;
            case "temperature" -> Source.TEMPERATURE;
            case "humidity" -> Source.HUMIDITY;
            case "location" -> Source.LOCATION;
            default -> null;
        };
    }

    private static void readMaps(List<String> input, List<SourceToDestination> functions) {
        //Init Values
        String[] nameToMap;
        String[] srcDest;
        Source source = null;
        Source destination = null;
        List<Long[]> initValues = new ArrayList<>();
        for (int i = 2; i < input.size(); ++i) {
            String line = input.get(i);
            if (line.isEmpty()) { //Before every Map is one empty line
                nameToMap = null;
                srcDest = null;
                functions.add(new SourceToDestination(initValues, source, destination));
                initValues = new ArrayList<>();
            } else if (line.matches("\\D+")) { //Line with the header of map
                nameToMap = line.split(" ");
                srcDest = nameToMap[0].split("-to-");
                source = parseSource(srcDest[0]);
                destination = parseSource(srcDest[1]);
            } else {
                String[] DSR = line.split(" "); //Destination, Source, Range
                Long[] valuesLine = new Long[3];
                for (int j = 0; j < 3; ++j) {
                    valuesLine[j] = Long.parseLong(DSR[j]);
                }
                initValues.add(valuesLine);
            }
        }
        functions.add(new SourceToDestination(initValues, source, destination)); //Last function
    }

    public static void firstPart(List<String> input) {
        //Read all Seeds from File and save in "seeds"
        List<Long> seeds = new ArrayList<>();
        String firstLineWithSeeds = input.get(0);
        readSeeds(firstLineWithSeeds.split(" "), seeds);

        //Read all maps from file
        List<SourceToDestination> functions = new ArrayList<>();
        readMaps(input, functions);

        long minLoc = Long.MAX_VALUE;
        for (int i = 0; i < seeds.size(); ++i) {
            Long seed = seeds.get(i);
            Long soil = functions.get(0).apply(seed);
            Long fert = functions.get(1).apply(soil);
            Long water = functions.get(2).apply(fert);
            Long light = functions.get(3).apply(water);
            Long temp = functions.get(4).apply(light);
            Long hum = functions.get(5).apply(temp);
            Long loc = functions.get(6).apply(hum);
            minLoc = loc < minLoc ? loc : minLoc;
        }

        System.out.println(minLoc);
    }
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("./Day5/src/input"));
        firstPart(lines);
    }
}