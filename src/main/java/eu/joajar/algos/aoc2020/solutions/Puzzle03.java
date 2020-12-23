package eu.joajar.algos.aoc2020.solutions;

import java.math.BigInteger;

public class Puzzle03 extends AbstractPuzzleDataReader {
    public Puzzle03(String _fileName) {
        super(_fileName);
    }

    @Override
    public int getDayNumber() {
        return 3;
    }

    @Override
    public String solveFirstPart() {
        return preparePart1Result(getData());
    }

    private String preparePart1Result(String[] strings) {
        return getNumberOfTrees(strings, 3, 1);
    }

    @Override
    public String solveSecondPart() {
        return preparePart2Result(getData());
    }

    private String preparePart2Result(String[] dataString) {
        BigInteger multiplicationResult = new BigInteger(getNumberOfTrees(dataString, 1, 2));

        for (int i = 0; i <= 3; i++) {
            multiplicationResult = multiplicationResult.multiply(
                new BigInteger(getNumberOfTrees(dataString, 1 + 2*i, 1))
            );
        }

        return multiplicationResult.toString();
    }

    private String getNumberOfTrees(String[] dataString, int numberRightSlopes, int numberDownSlopes) {
        int divisor = dataString[0].length();
        long counter = 0;
        for (int i = 1; i < dataString.length / numberDownSlopes; i++) {
            if (dataString[numberDownSlopes*i].charAt( (numberRightSlopes*i) % divisor) == '#') {
                counter++;
            }
        }
        return String.valueOf(counter);
    }
}
