package eu.joajar.algos.aoc2020.solutions;

public class Puzzle02 extends AbstractPuzzleDataReader {

    public Puzzle02(String _fileName) {
        super(_fileName);
    }

    @Override
    public int getDayNumber() {
        return 2;
    }

    @Override
    public String solveFirstPart() {
        var strings = getData();
        int counter = 0;
        for (String string : strings) {
            String[] stringsAfterSplitting = string.split(":|-|\\s");
            long countCharOccurrences = stringsAfterSplitting[4].chars().filter(x -> x == stringsAfterSplitting[2].charAt(0)).count();
            if (Integer.parseInt(stringsAfterSplitting[0]) <= countCharOccurrences && countCharOccurrences <= Integer.parseInt(stringsAfterSplitting[1])) {
                counter++;
            }
        }
        return String.valueOf(counter);
    }

    @Override
    public String solveSecondPart() {
        return "";
    }
}