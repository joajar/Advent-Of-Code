package eu.joajar.algos.aoc2020.solutions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPuzzle14 {
    private static final DataReaderAndAbstractPuzzle PUZZLE = new Puzzle14("src/main/resources/InputPuzzle14.txt");

    @Test
    public void day14_tests() {
        assertEquals("11884151942312", PUZZLE.solveFirstPart());
    }
}
