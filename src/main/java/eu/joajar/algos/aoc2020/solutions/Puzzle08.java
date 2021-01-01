package eu.joajar.algos.aoc2020.solutions;

import java.util.HashSet;
import java.util.Set;

public class Puzzle08 extends DataReaderAndAbstractPuzzle {
    public Puzzle08(String fileName) {
        super(fileName);
    }

    @Override
    public int getDayNumber() {
        return 8;
    }

    @Override
    public String solveFirstPart() {
        return preparePart1Result(getData());
    }

    private String preparePart1Result(String[] lines) {
        Set<Integer> linesVisited = new HashSet<>();
        int i = 0, accumulator = 0;
        while (linesVisited.add(i)) {
            String operation = lines[i].substring(0, 3);
            char sign = lines[i].charAt(4);
            int argument = Integer.parseInt(lines[i].substring(5));

            switch (operation) {
                case "nop" ->
                    ++i;
                case "acc" -> {
                    accumulator = (sign == '-') ? accumulator - argument : accumulator + argument;
                    ++i;
                }
                case "jmp" ->
                    i = (sign == '-') ? i - argument : i + argument;
            }
        }
        return String.valueOf(accumulator);
    }

    @Override
    public String solveSecondPart() {
        return "";
    }
}
