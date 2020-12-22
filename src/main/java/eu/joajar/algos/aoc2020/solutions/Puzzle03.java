package eu.joajar.algos.aoc2020.solutions;

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
        return getNumberOfTrees(strings);
    }

    private String getNumberOfTrees(String[] dataString) {
        final int numberRightSlopes = 3;
        final int numberDownSlopes = 1;
        int divisor = dataString[0].length();
        long counter = 0;
        for (int i = 1; i < dataString.length / numberDownSlopes; i++) {
            if (dataString[numberDownSlopes*i].charAt( (numberRightSlopes*i) % divisor) == '#') {
                counter++;
            }
        }
        return String.valueOf(counter);
    }

    @Override
    public String solveSecondPart() {
        return null;
    }
}
