package eu.joajar.algos.aoc2020.solutions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPuzzle12 {
    private static final DataReaderAndAbstractPuzzle PUZZLE = new Puzzle12("src/main/resources/InputPuzzle12.txt");

    @Test
    public void day12_tests() {
        assertEquals("879", PUZZLE.solveFirstPart());
        assertEquals("18107", PUZZLE.solveSecondPart());
    }
}
