package eu.joajar.algos.aoc2020.solutions;

import java.util.Arrays;
import java.util.HashMap;

public class Puzzle14 extends DataReaderAndAbstractPuzzle {
    public Puzzle14(String fileName) {
        super(fileName);
    }

    @Override
    public int getDayNumber() {
        return 14;
    }

    @Override
    public String solveFirstPart() {
        var lines = getData();
        var result = new HashMap<>();
        long maskX_to_1 = 0, maskX_to_0 = 0;

        for (String line: lines) {

            String[] strings = Arrays.stream(
                line
                    .replace(" ", "")
                    .split("[\\[\\]=]")
            )
                .filter(x -> !x.isEmpty())
                .toArray(String[]::new);

            if (strings[0].equals("mask")) {
                maskX_to_1 = Long.parseLong(strings[1].replace("X", "1"), 2);
                maskX_to_0 = Long.parseLong(strings[1].replace("X", "0"), 2);
            } else {
                result.put(
                    Integer.parseInt(strings[1]),
                    maskX_to_1 & Long.parseLong(strings[2]) | maskX_to_0
                );
            }

        }

        return String.valueOf(
            result.values().stream()
                .mapToLong(b -> Long.parseLong(String.valueOf(b)))
                .sum()
        );
    }

    @Override
    public String solveSecondPart() {
        return "";
    }
}
