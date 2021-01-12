package eu.joajar.algos.aoc2020.solutions;

import java.util.*;
import java.util.stream.Collectors;

public class Puzzle16 extends DataReaderAndAbstractPuzzle {
    public Puzzle16(String fileName) {
        super(fileName);
    }

    @Override
    public int getDayNumber() {
        return 16;
    }

    /**
     * An element of a map will be treated as an interval: a key will be a lower bound of an interval,
     * whereas a value - as an upper bound.
     */

    @Override
    public String solveFirstPart() {
        return calculateErrorRate(createMapForValidation(), getNumbersList());
    }

    @Override
    public String solveSecondPart() {
        return "";
    }

    private String calculateErrorRate(Map<Integer, Integer> allIntervals, List<Integer> listToValidate) {
        int sum = 0;
        for (Integer i : listToValidate) {
            boolean isContainedInSomeInterval = false;

            loop:
            for (Integer a : allIntervals.keySet()) {
                if (a <= i && i <= allIntervals.get(a)) {
                    isContainedInSomeInterval = true;
                    break loop;
                }
            }

            if (!isContainedInSomeInterval) {
                sum += i;
            }
        }
        return String.valueOf(sum);
    }

    private List<Integer> getNumbersList() {
        List<Integer> listToReturn = new ArrayList<>();
        var lines = getData();

        int index = 1;
        while (!lines[index - 1].replaceAll("\\s", "").equals("nearbytickets:")) {
            ++index;
        }

        while (index < lines.length) {
            listToReturn.addAll(
                Arrays.stream(lines[index].split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList())
            );

            ++index;
        }

        return listToReturn;
    }

    private Map<Integer, Integer> createMapForValidation() {
        var lines = getData();
        Map<Integer, Integer> allIntervals = new HashMap<>();

        int index = 0;
        while (!lines[index].isEmpty()) {
            var intervalsBoundaries = Arrays.stream(lines[index].substring(lines[index].lastIndexOf(':') + 2)
                .replaceAll("\\s", "")
                .split("or|-"))
                .mapToInt(Integer::parseInt)
                .toArray();

            allIntervals.put(intervalsBoundaries[0], intervalsBoundaries[1]);
            allIntervals.put(intervalsBoundaries[2], intervalsBoundaries[3]);

            ++index;
        }
        return allIntervals;
    }
}
