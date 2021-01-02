package eu.joajar.algos.aoc2020.solutions;

public class Puzzle09 extends DataReaderAndAbstractPuzzle {
    public Puzzle09(String fileName) {
        super(fileName);
    }

    @Override
    public int getDayNumber() {
        return 9;
    }

    @Override
    public String solveFirstPart() {
        int preambleLength = 25;
        String[] numbers = getData();

        for (int k = preambleLength; k < numbers.length; k++) {
            int checkNumber = 0;

            for (int i = 1; i <= preambleLength; i++) {
                for (int j = 1; j <= preambleLength; j++) {
                    if (Integer.parseInt(numbers[k - i]) != Integer.parseInt(numbers[k - j])) {
                        if (Integer.parseInt(numbers[k - i]) + Integer.parseInt(numbers[k - j]) != Integer.parseInt(numbers[k])) {
                            ++checkNumber;
                        }
                    } else {
                        ++checkNumber;
                    }
                }
            }

            if (checkNumber == preambleLength * preambleLength) {
                return numbers[k];
            }
        }
        return "Unable to solve the puzzle!";
    }

    @Override
    public String solveSecondPart() {
        return "";
    }
}
