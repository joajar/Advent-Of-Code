package eu.joajar.algos.aoc2020;

import eu.joajar.algos.aoc2020.solutions.AbstractPuzzleDataReader;
import eu.joajar.algos.aoc2020.solutions.Puzzle01;
import eu.joajar.algos.aoc2020.solutions.Puzzle02;

import java.util.List;

public class Runner {

    private static final List<AbstractPuzzleDataReader> PUZZLES = List.of(
            new Puzzle01("src/main/resources/InputPuzzle01.txt"),
            new Puzzle02("src/main/resources/InputPuzzle02.txt")
    );

    public static void main(String[] args) {
        PUZZLES.forEach(PUZZLE -> {
            System.out.println("Day " + PUZZLE.getDayNumber() + " part 1 result: " + PUZZLE.solveFirstPart());
            System.out.println("Day " + PUZZLE.getDayNumber() + " part 2 result: " + PUZZLE.solveSecondPart());
        });
    }
}
