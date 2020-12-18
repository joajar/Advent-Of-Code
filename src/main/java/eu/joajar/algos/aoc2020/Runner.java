package eu.joajar.algos.aoc2020;

import eu.joajar.algos.aoc2020.solutions.AbstractPuzzleDataReader;
import eu.joajar.algos.aoc2020.solutions.Puzzle01;

public class Runner {

    private static final AbstractPuzzleDataReader PUZZLE = new Puzzle01("src/main/resources/InputPuzzle01.txt");

    public static void main(String[] args) {
        try {
            System.out.println("Day 1 part 1 result: " + PUZZLE.solveFirstPart());
            System.out.println("Day 1 part 2 result: " + PUZZLE.solveSecondPart());
        } catch (IllegalArgumentException e) {
            System.out.println("Unable to solve day 1 puzzle as the data contains a duplicate!");
        }
    }
}
