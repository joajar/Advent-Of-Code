package eu.joajar.algos.aoc2020.solutions;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public class Puzzle12 extends DataReaderAndAbstractPuzzle {
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
        ShipCoordinates point = new ShipCoordinates();

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
                        default -> {
                            return "Unable to solve the puzzle!";
                        }
                    }
                }
                default -> {
                    return "Unable to solve the puzzle!";
                }
            }
        }
        return String.valueOf(Math.abs(point.x) + Math.abs(point.y));
    }

    /**
     * In this puzzle, when the author writes >>rotate the waypoint around the ship<<,
     * he means >>rotate the waypoint around point (0,0)<<
     */
    @Override
    public String solveSecondPart() {
        final String[] strings = getData();
        ShipCoordinates ship = new ShipCoordinates();
        Waypoint waypoint = new Waypoint();

        for (String string : strings) {
            final int integer = Integer.parseInt(string.substring(1));
            switch (string.charAt(0)) {

                case 'R' -> {
                    for (int j = 0; j < integer / 90; j++) {
                        waypoint = new Waypoint(waypoint.y, -waypoint.x);
                    }
                }
                case 'L' -> {
                    for (int j = 0; j < integer / 90; j++) {
                        waypoint = new Waypoint(-waypoint.y, waypoint.x);
                    }
                }
                case 'F' -> {
                    ship.x += integer * (waypoint.x);
                    ship.y += integer * (waypoint.y);
                }
                case 'N' -> waypoint.y += integer;
                case 'S' -> waypoint.y -= integer;
                case 'E' -> waypoint.x += integer;
                case 'W' -> waypoint.x -= integer;
                default -> {
                    System.out.println(string.charAt(0));
                    return "Unable to solve the puzzle!";
                }
            }
        }
        return String.valueOf(Math.abs(ship.x) + Math.abs(ship.y));
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    private static class Waypoint {
        int x = 10;
        int y = 1;
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    private static class ShipCoordinates {
        int x;
        int y;
        int facing;
    }
}
