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
            .filter(this::validateForFirstPart)
            .count()
        );
    }

    @Override
    public String solveSecondPart() {
        return String.valueOf(
            Arrays.stream(getData())
            .map(string -> string.split(":\\s|-|\\s"))
            .map(PasswordData::transform)
            .filter(this::validateForSecondPart)
            .count()
        );
    }

    private record PasswordData(int lowerBound, int upperBound, char character, String password) {
        private static PasswordData transform(String[] array) {
            return new PasswordData(
                Integer.parseInt(array[0]),
                Integer.parseInt(array[1]),
                array[2].charAt(0),
                array[3]
            );
        }
    }

    private boolean validateForFirstPart(PasswordData passwordData) {
        var countCharOccurrences = passwordData.password.chars().filter(ch -> ch == passwordData.character).count();
        return passwordData.lowerBound <= countCharOccurrences && countCharOccurrences <= passwordData.upperBound;
    }

    private boolean validateForSecondPart(PasswordData passwordData) {
        return ((passwordData.password.charAt(passwordData.lowerBound - 1) == passwordData.character)
            ^ (passwordData.password.charAt(passwordData.upperBound - 1) == passwordData.character));
    }
}