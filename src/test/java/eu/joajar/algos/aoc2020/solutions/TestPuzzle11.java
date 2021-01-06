package eu.joajar.algos.aoc2020.solutions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPuzzle11 {
    private static final DataReaderAndAbstractPuzzle PUZZLE = new Puzzle11("src/main/resources/InputPuzzle11.txt");

    @Test
    public void day11_tests() {
        assertEquals("2249", PUZZLE.solveFirstPart());
    }
}
