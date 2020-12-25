package eu.joajar.algos.aoc2020.solutions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPuzzle04 {
    private static final AbstractPuzzleDataReader PUZZLE = new Puzzle04("src/main/resources/InputPuzzle04.txt");

    @Test
    public void day04_tests() {
        assertEquals("204", PUZZLE.solveFirstPart());
    }
}
