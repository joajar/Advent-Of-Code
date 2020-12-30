package eu.joajar.algos.aoc2020.solutions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPuzzle06 {
    private static final DataReaderAndAbstractPuzzle PUZZLE = new Puzzle06("src/main/resources/InputPuzzle06.txt");

    @Test
    public void day06_tests() {
        assertEquals("6714", PUZZLE.solveFirstPart());
        assertEquals("3435", PUZZLE.solveSecondPart());
    }
}
