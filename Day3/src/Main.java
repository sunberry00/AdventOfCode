import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    private final static int N = 140;

    private static List<Integer> extractNumbers(char[][] field, int row, int column) {
        List<Integer> result = new ArrayList<>();

        //TOP ROW
        StringBuilder upperRow = new StringBuilder();
        for (int i = -1; i <= 1; ++i) {
            if (isValidIndex(row - 1, column + i)) {
                upperRow.append(field[row - 1][column + i]);
            }
        }

        if (upperRow.toString().matches("\\d+")) {
            result.add(Integer.parseInt(upperRow.toString()));
        } else if (upperRow.toString().matches("\\d\\D\\d") || upperRow.toString().matches("\\d\\D\\D") || upperRow.toString().matches("\\D\\D\\d")) {
            if (Character.isDigit(upperRow.charAt(0))) {
                StringBuilder leftNumber = new StringBuilder();
                leftNumber.append(upperRow.charAt(0));
                if (isValidIndex(row - 1, column - 2) && Character.isDigit(field[row - 1][column - 2])) {
                    leftNumber.insert(0, field[row - 1][column - 2]);
                    if (isValidIndex(row - 1, column - 3) && Character.isDigit(field[row - 1][column - 3])) {
                        leftNumber.insert(0, field[row - 1][column - 3]);
                    }
                }
                result.add(Integer.parseInt(leftNumber.toString()));
            }

            if (Character.isDigit(upperRow.charAt(2))) {
                StringBuilder rightNumber = new StringBuilder();
                rightNumber.append(upperRow.charAt(2));
                if (isValidIndex(row - 1, column + 2) && Character.isDigit(field[row - 1][column + 2])) {
                    rightNumber.append(field[row - 1][column + 2]);
                    if (isValidIndex(row - 1, column + 3) && Character.isDigit(field[row - 1][column + 3])) {
                        rightNumber.append(field[row - 1][column + 3]);
                    }
                }
                result.add(Integer.parseInt(rightNumber.toString()));
            }
        } else if (upperRow.toString().matches("\\d\\d\\D")) {
            StringBuilder leftNumber = new StringBuilder();
            leftNumber.append(upperRow.charAt(0)).append(upperRow.charAt(1));
            if (isValidIndex(row - 1, column - 2) && Character.isDigit(field[row - 1][column - 2])) {
                leftNumber.insert(0, field[row - 1][column - 2]);
            }
            result.add(Integer.parseInt(leftNumber.toString()));
        } else if (upperRow.toString().matches("\\D\\d\\d")) {
            StringBuilder rightNumber = new StringBuilder();
            rightNumber.append(upperRow.charAt(1)).append(upperRow.charAt(2));
            if (isValidIndex(row - 1, column + 2) && Character.isDigit(field[row - 1][column + 2])) {
                rightNumber.append(field[row - 1][column + 2]);
            }
            result.add(Integer.parseInt(rightNumber.toString()));
        }

        //MIDDLE ROW
        StringBuilder middleRow = new StringBuilder();
        for (int i = -1; i <= 1; ++i) {
            if (isValidIndex(row, column + i)) {
                middleRow.append(field[row][column + i]);
            }
        }

        if (Character.isDigit(middleRow.charAt(0))) {
            StringBuilder leftNumber = new StringBuilder();
            leftNumber.append(middleRow.charAt(0));
            if (isValidIndex(row, column - 2) && Character.isDigit(field[row][column - 2])) {
                leftNumber.insert(0, field[row][column - 2]);
                if (isValidIndex(row, column - 3) && Character.isDigit(field[row][column - 3])) {
                    leftNumber.insert(0, field[row][column - 3]);
                }
            }
            result.add(Integer.parseInt(leftNumber.toString()));
        }

        if (Character.isDigit(middleRow.charAt(2))) {
            StringBuilder rightNumber = new StringBuilder();
            rightNumber.append(middleRow.charAt(2));
            if (isValidIndex(row, column + 2) && Character.isDigit(field[row][column + 2])) {
                rightNumber.append(field[row][column + 2]);
                if (isValidIndex(row, column + 3) && Character.isDigit(field[row][column + 3])) {
                    rightNumber.append(field[row][column + 3]);
                }
            }
            result.add(Integer.parseInt(rightNumber.toString()));
        }

        //Bottom ROW
        StringBuilder bottomRow = new StringBuilder();
        for (int i = -1; i <= 1; ++i) {
            if (isValidIndex(row + 1, column + i)) {
                bottomRow.append(field[row + 1][column + i]);
            }
        }

        if (bottomRow.toString().matches("\\d+")) {
            result.add(Integer.parseInt(bottomRow.toString()));
        } else if (bottomRow.toString().matches("\\d\\D\\d") || bottomRow.toString().matches("\\d\\D\\D") || bottomRow.toString().matches("\\D\\D\\d")) {
            if (Character.isDigit(bottomRow.charAt(0))) {
                StringBuilder leftNumber = new StringBuilder();
                leftNumber.append(bottomRow.charAt(0));
                if (isValidIndex(row + 1, column - 2) && Character.isDigit(field[row + 1][column - 2])) {
                    leftNumber.insert(0, field[row + 1][column - 2]);
                    if (isValidIndex(row + 1, column - 3) && Character.isDigit(field[row + 1][column - 3])) {
                        leftNumber.insert(0, field[row + 1][column - 3]);
                    }
                }
                result.add(Integer.parseInt(leftNumber.toString()));
            }

            if (Character.isDigit(bottomRow.charAt(2))) {
                StringBuilder rightNumber = new StringBuilder();
                rightNumber.append(bottomRow.charAt(2));
                if (isValidIndex(row + 1, column + 2) && Character.isDigit(field[row + 1][column + 2])) {
                    rightNumber.append(field[row + 1][column + 2]);
                    if (isValidIndex(row + 1, column + 3) && Character.isDigit(field[row + 1][column + 3])) {
                        rightNumber.append(field[row + 1][column + 3]);
                    }
                }
                result.add(Integer.parseInt(rightNumber.toString()));
            }
        } else if (bottomRow.toString().matches("\\d\\d\\D")) {
            StringBuilder leftNumber = new StringBuilder();
            leftNumber.append(bottomRow.charAt(0)).append(bottomRow.charAt(1));
            if (isValidIndex(row + 1, column - 2) && Character.isDigit(field[row + 1][column - 2])) {
                leftNumber.insert(0, field[row + 1][column - 2]);
            }
            result.add(Integer.parseInt(leftNumber.toString()));
        } else if (bottomRow.toString().matches("\\D\\d\\d")) {
            StringBuilder rightNumber = new StringBuilder();
            rightNumber.append(bottomRow.charAt(1)).append(bottomRow.charAt(2));
            if (isValidIndex(row + 1, column + 2) && Character.isDigit(field[row + 1][column + 2])) {
                rightNumber.append(field[row + 1][column + 2]);
            }
            result.add(Integer.parseInt(rightNumber.toString()));
        } else if (bottomRow.toString().matches("\\D\\d\\D")) {
            result.add(Integer.parseInt(String.valueOf(bottomRow.charAt(1))));
        }

        return result;
    }

    private static boolean isValidIndex(int row, int col) {
        return row >= 0 && row < N && col >= 0 && col < N;
    }

    public static void partTwo(char[][] field) throws IOException {

        int sum = 0;
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                if (field[i][j] == '*') {
                    List<Integer> numbers = extractNumbers(field, i, j);
                    if (numbers.size() == 2) {
                        sum += (numbers.get(0) * numbers.get(1));
                    }
                }
            }
        }
        System.out.println(sum);
    }

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
                        if (j != N - 1) {
                            if (!String.valueOf(field[i][j + 1]).matches("\\d") && field[i][j + 1] != '.') {
                                isValid = true;
                            }
                        }
                        if (i != N - 1) {
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

        partTwo(field);
    }
}