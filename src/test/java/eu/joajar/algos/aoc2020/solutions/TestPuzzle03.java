package eu.joajar.algos.aoc2020.solutions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPuzzle03 {
    private static final DataReaderAndAbstractPuzzle PUZZLE = new Puzzle03("src/main/resources/InputPuzzle03.txt");

    @Test
    public void day03_tests() {
        assertEquals("162", PUZZLE.solveFirstPart());
        assertEquals("3064612320", PUZZLE.solveSecondPart());
    }
}
