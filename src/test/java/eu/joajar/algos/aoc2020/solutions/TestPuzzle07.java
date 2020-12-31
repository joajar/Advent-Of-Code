package eu.joajar.algos.aoc2020.solutions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPuzzle07 {
    private static final DataReaderAndAbstractPuzzle PUZZLE =
        new Puzzle07("src/main/resources/InputPuzzle07.txt");

    @Test
    public void day07_tests() {
        assertEquals("238", PUZZLE.solveFirstPart());
    }
}
