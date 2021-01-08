package eu.joajar.algos.aoc2020.solutions;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

public class Puzzle12 extends DataReaderAndAbstractPuzzle{
    public Puzzle12(String fileName) {
        super(fileName);
    }

    @Override
    public int getDayNumber() {
        return 12;
    }

    /**
     * The following coding of directions is used below:
     * Map<Character, Integer> directions = Map.of('E', 0, 'S', 1, 'W', 2, 'N', 3);
     */

    @Override
    public String solveFirstPart() {
        final String[] strings = getData();
        Coordinates point = new Coordinates();

        for (String string : strings) {
            final int integer = Integer.parseInt(string.substring(1));
            switch (string.charAt(0)) {
                case 'R' -> point.facing = (point.facing + integer / 90) % 4;
                case 'L' -> point.facing = (point.facing + 4 - integer / 90) % 4;
                case 'N' -> point.y += integer;
                case 'S' -> point.y -= integer;
                case 'E' -> point.x += integer;
                case 'W' -> point.x -= integer;
                case 'F' -> {
                    switch (point.facing) {
                        case 0 -> point.x += integer;
                        case 1 -> point.y -= integer;
                        case 2 -> point.x -= integer;
                        case 3 -> point.y += integer;
                    }
                }
                default -> {
                    System.out.println(string.charAt(0));
                    return "Unable to solve the puzzle!";
                }
            }
        }
        return String.valueOf(Math.abs(point.x) + Math.abs(point.y));
    }

    @Override
    public String solveSecondPart() {
        return null;
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    private static class Coordinates {
        int x;
        int y;
        int facing;
    }
}
