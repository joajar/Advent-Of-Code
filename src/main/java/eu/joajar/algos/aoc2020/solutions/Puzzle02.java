package eu.joajar.algos.aoc2020.solutions;

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
        var strings = getData();
        int counter = 0;
        for (String string : strings) {
            String[] stringsAfterSplitting = string.split(":\\s|-|\\s");
            long countCharOccurrences = stringsAfterSplitting[3].chars().filter(x -> x == stringsAfterSplitting[2].charAt(0)).count();
            if (Integer.parseInt(stringsAfterSplitting[0]) <= countCharOccurrences && countCharOccurrences <= Integer.parseInt(stringsAfterSplitting[1])) {
                counter++;
            }
        }
        return String.valueOf(counter);
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