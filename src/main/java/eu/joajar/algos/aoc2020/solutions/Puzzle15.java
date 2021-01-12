package eu.joajar.algos.aoc2020.solutions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Puzzle15 extends DataReaderAndAbstractPuzzle{
    public Puzzle15(String fileName) {
        super(fileName);
    }

    @Override
    public int getDayNumber() {
        return 15;
    }

    @Override
    public String solveFirstPart() {
        return memoryGame(2020);
    }

    @Override
    public String solveSecondPart() {
        return memoryGame(30_000_000);
    }

    private String memoryGame(int turnNumber) {
        int[] numbers = Arrays.stream(getData()[0].split(",")).mapToInt(Integer::parseInt).toArray();
        Map<Integer, Integer> whatWasSpokenAndWhen = new HashMap<>();

        for (int i = 1; i <= numbers.length - 1; i++) {
            whatWasSpokenAndWhen.put(numbers[i - 1], i);
        }

        int lastNumberSpoken = numbers[numbers.length - 1];
        int toSpoke;

        for (int i = numbers.length + 1; i <= turnNumber; i++) {
            if (whatWasSpokenAndWhen.containsKey(lastNumberSpoken)) {
                toSpoke = i - 1 - whatWasSpokenAndWhen.get(lastNumberSpoken);
                whatWasSpokenAndWhen.replace(lastNumberSpoken, i - 1);
                lastNumberSpoken = toSpoke;
            } else {
                whatWasSpokenAndWhen.put(lastNumberSpoken, i - 1);
                lastNumberSpoken = 0;
            }
        }
        return String.valueOf(lastNumberSpoken);
    }
}
