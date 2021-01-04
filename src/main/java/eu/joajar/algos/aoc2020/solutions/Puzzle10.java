package eu.joajar.algos.aoc2020.solutions;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Puzzle10 extends DataReaderAndAbstractPuzzle {
    public Puzzle10(String fileName) {
        super(fileName);
    }

    @Override
    public int getDayNumber() {
        return 10;
    }

    @Override
    public String solveFirstPart() {
        Set<Integer> integers = Arrays.stream(getData())
            .map(Integer::parseInt)
            .collect(Collectors.toSet());

        var output = 0;
        var jolt1diffs = 0;
        var jolt3diffs = 0;

        while (!integers.isEmpty()) {
            var minimal = integers.stream().sorted().min(Integer::compare).get();

            switch (minimal - output) {
                case 1: {++jolt1diffs; break;}
                case 3: {++jolt3diffs; break;}
                default: return "Unable to solve the puzzle!";
            }

            output = minimal;
            integers.remove(minimal);
        }
        return String.valueOf(jolt1diffs * (1 + jolt3diffs));
    }

    @Override
    public String solveSecondPart() {
        return "";
    }
}
