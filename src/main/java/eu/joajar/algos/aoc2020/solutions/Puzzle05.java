package eu.joajar.algos.aoc2020.solutions;

public class Puzzle05 extends AbstractPuzzleDataReader {
    public Puzzle05(String _fileName) {
        super(_fileName);
    }

    @Override
    public int getDayNumber() {
        return 5;
    }

    @Override
    public String solveFirstPart() {
        return preparePart1Result(getData());
    }

    private static String preparePart1Result(String[] strings) {
        int rowNumber, columnNumber, hithertoHighestSeatId = 0;
        for (String stringEntry: strings) {
            rowNumber = Integer.parseInt(stringEntry.substring(0, 7).replace("F", "0").replace("B", "1") , 2);
            columnNumber = Integer.parseInt(stringEntry.substring(7).replace("L", "0").replace("R", "1"), 2);
            hithertoHighestSeatId = Math.max(hithertoHighestSeatId, rowNumber * 8 + columnNumber);
        }
        return String.valueOf(hithertoHighestSeatId);
    }

    @Override
    public String solveSecondPart() {
        return null;
    }
}
