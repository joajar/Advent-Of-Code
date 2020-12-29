package eu.joajar.algos.aoc2020.solutions;

import java.util.Arrays;

public class Puzzle02 extends DataReaderAndAbstractPuzzle {

    public Puzzle02(String fileName) {
        super(fileName);
    }

    @Override
    public int getDayNumber() {
        return 2;
    }

    @Override
    public String solveFirstPart() {
        return String.valueOf(
            Arrays.stream(getData())
            .map(string -> string.split(":\\s|-|\\s"))
            .map(PasswordData::transform)
            .filter(this::isValid)
            .count()
        );
    }

    private record PasswordData(long lowerBound, long upperBound, char character, String password) {
        private static PasswordData transform(String[] array) {
            return new PasswordData(
                Long.parseLong(array[0]),
                Long.parseLong(array[1]),
                array[2].charAt(0),
                array[3]
            );
        }
    }

    private boolean isValid(PasswordData passwordData) {
        var countCharOccurrences = passwordData.password.chars().filter(ch -> ch == passwordData.character).count();
        return passwordData.lowerBound <= countCharOccurrences && countCharOccurrences <= passwordData.upperBound;
    }

    @Override
    public String solveSecondPart() {
        var strings = getData();
        int counter = 0;
        for (String string : strings) {
            String[] stringsAfterSplitting = string.split(":\\s|-|\\s");

            if ((stringsAfterSplitting[3].charAt(Integer.parseInt(stringsAfterSplitting[0]) - 1) == stringsAfterSplitting[2].charAt(0))
                ^ (stringsAfterSplitting[3].charAt(Integer.parseInt(stringsAfterSplitting[1]) - 1) == stringsAfterSplitting[2].charAt(0))) {
                counter++;
            }
        }
        return String.valueOf(counter);
    }
}