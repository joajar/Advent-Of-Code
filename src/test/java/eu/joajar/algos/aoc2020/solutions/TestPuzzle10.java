package eu.joajar.algos.aoc2020.solutions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPuzzle10 {
    private static final DataReaderAndAbstractPuzzle PUZZLE = new Puzzle10("src/main/resources/InputPuzzle10.txt");

    @Test
    public void day10_tests() {
        assertEquals("2400", PUZZLE.solveFirstPart());
    }
}
