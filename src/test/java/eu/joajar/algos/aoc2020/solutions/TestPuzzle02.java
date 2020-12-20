package eu.joajar.algos.aoc2020.solutions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPuzzle02 {
    private static final AbstractPuzzleDataReader PUZZLE = new Puzzle02("src/main/resources/InputPuzzle02.txt");

    @Test
    public void day02_tests() {
        assertEquals("538", PUZZLE.solveFirstPart());
        assertEquals("489", PUZZLE.solveSecondPart());
    }
}
