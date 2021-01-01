package eu.joajar.algos.aoc2020.solutions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPuzzle08 {
    private static final DataReaderAndAbstractPuzzle PUZZLE = new Puzzle08("src/main/resources/InputPuzzle08.txt");

    @Test
    public void day08_tests() {
        assertEquals("1489", PUZZLE.solveFirstPart());
        assertEquals("1539", PUZZLE.solveSecondPart());
    }
}
