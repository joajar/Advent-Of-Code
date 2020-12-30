package eu.joajar.algos.aoc2020;

import eu.joajar.algos.aoc2020.solutions.*;

import java.util.List;

public class Runner {

    private static final List<DataReaderAndAbstractPuzzle> PUZZLES = List.of(
            new Puzzle01("src/main/resources/InputPuzzle01.txt"),
            new Puzzle02("src/main/resources/InputPuzzle02.txt"),
            new Puzzle03("src/main/resources/InputPuzzle03.txt"),
            new Puzzle04("src/main/resources/InputPuzzle04.txt"),
            new Puzzle05("src/main/resources/InputPuzzle05.txt"),
            new Puzzle06("src/main/resources/InputPuzzle06.txt")
    );

    public static void main(String[] args) {
        PUZZLES.forEach(PUZZLE -> {
            System.out.println("Day " + PUZZLE.getDayNumber() + " part 1 result: " + PUZZLE.solveFirstPart());
            System.out.println("Day " + PUZZLE.getDayNumber() + " part 2 result: " + PUZZLE.solveSecondPart());
        });
    }
}
