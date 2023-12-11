import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class NoSpaceLeftOnDevice {
    public static void firstPart(List<String> input) {
        Stack<String> dirs = new Stack<>();
        Map<String, Long> directoriesSize = new HashMap<>();
        String currentDirectory = "";
        for (String line : input) {
            String[] tokens = line.split(" ");
            if (tokens[0].equals("$")) { //Command
                if (tokens[1].equals("cd")) {
                    if (!tokens[2].equals("..")) {
                        currentDirectory = tokens[2];
                        directoriesSize.put(currentDirectory, 0L);
                        dirs.add(tokens[2]);
                    } else {
                        currentDirectory = dirs.pop();
                    }
                } else if (tokens[1].equals("ls")) {
                    continue;
                }
            } else if (tokens[0].equals("dir")) {
                directoriesSize.put(currentDirectory, 0L);
            } else if (tokens[0].matches("\\d+")) {
                directoriesSize.put(currentDirectory, directoriesSize.get(currentDirectory) + Long.parseLong(tokens[0]));
            }
        }
        System.out.println();
    }
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("./2022/Day7/src/example"));
        firstPart(lines);
    }
}
