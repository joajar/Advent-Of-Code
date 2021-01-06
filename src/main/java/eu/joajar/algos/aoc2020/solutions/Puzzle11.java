package eu.joajar.algos.aoc2020.solutions;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Puzzle11 extends DataReaderAndAbstractPuzzle {
    public Puzzle11(String fileName) {
        super(fileName);
    }

    @Override
    public int getDayNumber() {
        return 11;
    }

    final int maxX = getData()[0].length();
    final int maxY = getData().length;

    @Override
    public String solveFirstPart() {
        String[] strings = getData();
        String[] newStrings;
        boolean areIdentical;
        do {
            newStrings = iterate(strings);
            areIdentical = areIdentical(strings, newStrings);
            strings= newStrings;
        } while (!areIdentical);

        return String.valueOf(countOccupiedSeats(strings));
    }

    @Override
    public String solveSecondPart() {
        return null;
    }

    private long countOccupiedSeats(String[] strings) {
        long count = 0;
        for (String string : strings) {
            count += string.chars().filter(ch -> ch == '#').count();
        }
        return count;
    }

    private boolean areIdentical(String[] strings1, String[] strings2) {
        if (strings1.length != strings2.length) {
            return false;
        }

        for (int i = 0; i < strings1.length; i++) {
            if (!strings1[i].equals(strings2[i])) {
                return false;
            }
        }

        return true;
    }

    private String[] iterate(String[] initStrings) {
        char empty = "L".charAt(0);
        char occupied = "#".charAt(0);
        char floor = ".".charAt(0);
        String[] newStrings = new String[initStrings.length];

        Set<Coordinates> coordinateSet = Stream.of(
            new Coordinates(0, 1), new Coordinates(1, 1), new Coordinates(1, 0),
            new Coordinates(1, -1), new Coordinates(0, -1), new Coordinates(-1, -1),
            new Coordinates(-1, 0), new Coordinates(-1, 1)
        ).collect(Collectors.toSet());

        for (int y = 0; y < maxY; y++) {
            var builder = new StringBuilder();
            for (int x = 0; x < maxX; x++) {

                if (initStrings[y].charAt(x) == empty || initStrings[y].charAt(x) == occupied) {
                    int occupiedNumber = 0;
                    for (Coordinates coordinates: coordinateSet) {
                        if (0 <= x+coordinates.x && x+coordinates.x < maxX && 0 <= y+coordinates.y && y+coordinates.y < maxY && initStrings[y+coordinates.y].charAt(x+coordinates.x) == occupied) {
                            ++occupiedNumber;
                        }
                    }

                    if (initStrings[y].charAt(x) == empty && occupiedNumber == 0) {
                        builder.append(occupied);
                    } else if (initStrings[y].charAt(x) == occupied && occupiedNumber >= 4) {
                        builder.append(empty);
                    } else {
                        builder.append(initStrings[y].charAt(x));
                    }

                } else if (initStrings[y].charAt(x) == floor) {
                    builder.append(initStrings[y].charAt(x));
                }
            }
            newStrings[y] = builder.toString();
        }

        return newStrings;
    }

    private static class Coordinates {
        int x;
        int y;

        private Coordinates(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
