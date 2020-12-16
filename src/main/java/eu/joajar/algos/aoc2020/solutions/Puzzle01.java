package eu.joajar.algos.aoc2020.solutions;

import java.util.NoSuchElementException;

public class Puzzle01 extends AbstractPuzzleDataReader {

    public Puzzle01(String _fileName) {
        super(_fileName);
    }

    @Override
    public String solveFirstPuzzle() {
        return String.valueOf(preparePuzzle1Result(getData()));
    }

    private int preparePuzzle1Result(String[] strings) {
        for (int i = 0; i < strings.length; i++) {
            for (int j = i + 1; j < strings.length; j++) {
                if (Integer.parseInt(strings[i]) + Integer.parseInt(strings[j]) == 2020) {
                    return Integer.parseInt(strings[i]) * Integer.parseInt(strings[j]);
                }
            }
        }
        throw new NoSuchElementException("The solution wasn't found");
    }
}
