package eu.joajar.algos.aoc2020.solutions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPuzzle15 {
    private static final DataReaderAndAbstractPuzzle PUZZLE = new Puzzle15("src/main/resources/InputPuzzle15.txt");

    @Test
    public void day15_tests() {
        assertEquals("1194", PUZZLE.solveFirstPart());
    }
}
