package eu.joajar.algos.aoc2020.solutions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPuzzle01 {

    private static final DataReaderAndAbstractPuzzle PUZZLE = new Puzzle01("src/main/resources/InputPuzzle01.txt");

    @Test
    public void day01_tests() {
        assertEquals("259716", PUZZLE.solveFirstPart());
        assertEquals("120637440", PUZZLE.solveSecondPart());
    }
}
