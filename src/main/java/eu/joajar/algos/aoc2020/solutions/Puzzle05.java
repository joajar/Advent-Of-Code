package eu.joajar.algos.aoc2020.solutions;

import java.util.HashSet;
import java.util.Set;

public class Puzzle05 extends AbstractPuzzleDataReader {
    public Puzzle05(String _fileName) {
        super(_fileName);
    }

    @Override
    public int getDayNumber() {
        return 5;
    }

    @Override
    public String solveFirstPart() {
        return preparePart1Result(getData());
    }

    private static String preparePart1Result(String[] strings) {
        int rowNumber, columnNumber, hithertoHighestSeatId = 0;
        for (String stringEntry: strings) {
            rowNumber = Integer.parseInt(stringEntry.substring(0, 7).replace("F", "0").replace("B", "1") , 2);
            columnNumber = Integer.parseInt(stringEntry.substring(7).replace("L", "0").replace("R", "1"), 2);
            hithertoHighestSeatId = Math.max(hithertoHighestSeatId, rowNumber * 8 + columnNumber);
        }
        return String.valueOf(hithertoHighestSeatId);
    }

    @Override
    public String solveSecondPart() {
        int lowerMagicNumber = 79;
        int upperMagicNumber = 920;
        return preparePart2Result(getData(), lowerMagicNumber, upperMagicNumber);
    }

    private static String preparePart2Result(String[] strings, int lowerMagicNumber, int upperMagicNumber) {
        Set<Integer> initialSeatsSet = populateInitialSeatsSet(lowerMagicNumber, upperMagicNumber);
        int currentNumber;
        for (String stringEntry: strings) {
            currentNumber = Integer.valueOf(stringEntry.replace("F", "0").replace("B", "1").replace("L", "0").replace("R", "1") , 2);
            initialSeatsSet.remove(currentNumber);
        }

        if (initialSeatsSet.isEmpty()) {
            throw new IllegalStateException("Variable lowerMagicNumber is to high and/or variable upperMagicNumber is to low!");
        }
        return String.valueOf(initialSeatsSet.iterator().next());
    }

    private static Set<Integer> populateInitialSeatsSet(int lowerMagicNumber, int upperMagicNumber) {
        Set<Integer> setToPopulate = new HashSet<>();
        for (Integer i = lowerMagicNumber + 1; i < Math.min(upperMagicNumber, Math.pow(2, 10)); i++) {
            setToPopulate.add(i);
        }
        return setToPopulate;
    }
}
