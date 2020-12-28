package eu.joajar.algos.aoc2020.solutions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPuzzle05 {
    private static final AbstractPuzzleDataReader PUZZLE = new Puzzle05("src/main/resources/InputPuzzle05.txt");

    @Test
    public void day05_tests() {
        assertEquals("919", PUZZLE.solveFirstPart());
        assertEquals("642", PUZZLE.solveSecondPart());

    }
}
