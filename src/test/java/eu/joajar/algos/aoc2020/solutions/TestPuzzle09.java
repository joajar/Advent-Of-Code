package eu.joajar.algos.aoc2020.solutions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPuzzle09 {
    private static final DataReaderAndAbstractPuzzle PUZZLE = new Puzzle09("src/main/resources/InputPuzzle09.txt");

    @Test
    public void day09_tests() {
        assertEquals("23278925", PUZZLE.solveFirstPart());
        assertEquals("4011064", PUZZLE.solveSecondPart());
    }
}
