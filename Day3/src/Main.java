import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {

    private final static int N = 140;

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("./Day3/src/input"));


        char[][] field = new char[N][N];

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                field[i][j] = lines.get(i).charAt(j);
            }
        }

        //Part 1
        boolean isValid = false;
        long sum = 0;
        for (int i = 0; i < N; ++i) {
            StringBuilder number = new StringBuilder();
            for (int j = 0; j < N; ++j) {
                if (!String.valueOf(field[i][j]).matches("\\d")) {
                    if (number.isEmpty()) {
                        continue;
                    } else {
                        if (isValid) {
                            sum += Integer.parseInt(number.toString());
                        }
                        number = new StringBuilder();
                        isValid = false;
                    }
                } else {
                    if (String.valueOf(field[i][j]).matches("\\d")) {
                        number.append(field[i][j]);
                        if (i != 0) {
                            if (j != 0) {
                                if (!String.valueOf(field[i - 1][j - 1]).matches("\\d") && field[i - 1][j - 1] != '.') {
                                    isValid = true;
                                }
                            }
                            if (!String.valueOf(field[i - 1][j]).matches("\\d") && field[i - 1][j] != '.') {
                                isValid = true;
                            }
                            if (j != 139) {
                                if (!String.valueOf(field[i - 1][j + 1]).matches("\\d") && field[i - 1][j + 1] != '.') {
                                    isValid = true;
                                }
                            }
                        }
                        if (j != 0) {
                            if (!String.valueOf(field[i][j - 1]).matches("\\d") && field[i][j - 1] != '.') {
                                isValid = true;
                            }
                        }
                        if (j != 139) {
                            if (!String.valueOf(field[i][j + 1]).matches("\\d") && field[i][j + 1] != '.') {
                                isValid = true;
                            }
                        }
                        if (i != 139) {
                            if (j != 0) {
                                if (!String.valueOf(field[i + 1][j - 1]).matches("\\d") && field[i + 1][j - 1] != '.') {
                                    isValid = true;
                                }
                            }
                            if (!String.valueOf(field[i + 1][j]).matches("\\d") && field[i + 1][j] != '.') {
                                isValid = true;
                            }
                            if (j != 139) {
                                if (!String.valueOf(field[i + 1][j + 1]).matches("\\d") && field[i + 1][j + 1] != '.') {
                                    isValid = true;
                                }
                            }
                        }
                    }
                }
            }
            if (isValid) {
                sum += Integer.parseInt(number.toString());
            }
            isValid = false;
        }

        System.out.println(sum);
    }
}