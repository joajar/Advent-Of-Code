package eu.joajar.algos.aoc2020.solutions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPuzzle13 {
    private static final DataReaderAndAbstractPuzzle PUZZLE = new Puzzle13("src/main/resources/InputPuzzle13.txt");

    @Test
    public void day13_tests() {
        assertEquals("203", PUZZLE.solveFirstPart());
    }
}
