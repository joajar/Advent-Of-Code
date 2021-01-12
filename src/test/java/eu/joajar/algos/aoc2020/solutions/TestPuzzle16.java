package eu.joajar.algos.aoc2020.solutions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPuzzle16 {
    private static final DataReaderAndAbstractPuzzle PUZZLE = new Puzzle16("src/main/resources/InputPuzzle16.txt");

    @Test
    public void day16_tests() {
        assertEquals("23044", PUZZLE.solveFirstPart());
     }
}
