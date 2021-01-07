package eu.joajar.algos.aoc2020.solutions;

import java.util.HashSet;
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
        String[] strings = findEquilibriumFor1Part(getData());

        return String.valueOf(countOccupiedSeats(strings));
    }

    @Override
    public String solveSecondPart() {
        String[] strings = findEquilibriumFor2Part(getData());

        return String.valueOf(countOccupiedSeats(strings));
    }

    private String[] iterateFor2Part(String[] initStrings) {
        char empty = "L".charAt(0);
        char occupied = "#".charAt(0);
        char floor = ".".charAt(0);
        String[] newStrings = new String[maxY];

        Set<Coordinates> neighboursCoordinates;

        for (int y = 0; y < maxY; y++) {
            var builder = new StringBuilder();
            for (int x = 0; x < maxX; x++) {

                neighboursCoordinates = findAdjacentSeats(new Coordinates(x,y), initStrings);

                if (initStrings[y].charAt(x) == empty || initStrings[y].charAt(x) == occupied) {
                    int occupiedNumber = 0;
                    for (Coordinates coordinates: neighboursCoordinates) {
                        if (initStrings[coordinates.y].charAt(coordinates.x) == occupied) {
                            ++occupiedNumber;
                        }
                    }

                    if (initStrings[y].charAt(x) == empty && occupiedNumber == 0) {
                        builder.append(occupied);
                    } else if (initStrings[y].charAt(x) == occupied && occupiedNumber >= 5) {
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

    private Set<Coordinates> findAdjacentSeats(Coordinates seat, String[] initStrings) {
        char floor = ".".charAt(0);

        Set<Coordinates> neighboursCoordinates = new HashSet<>();

        for (int y = seat.y - 1; y >= 0; y--) {
            if (initStrings[y].charAt(seat.x) != floor) {
                neighboursCoordinates.add(new Coordinates(seat.x, y));
                break;
            }
        }

        for (int i = 1; (seat.x + i < maxX) && (seat.y - i >= 0); i++) {
            if (initStrings[seat.y - i].charAt(seat.x + i) != floor) {
                neighboursCoordinates.add(new Coordinates(seat.x + i, seat.y - i));
                break;
            }
        }

        for (int x = seat.x + 1; x < maxX; x++) {
            if (initStrings[seat.y].charAt(x) != floor) {
                neighboursCoordinates.add(new Coordinates(x, seat.y));
                break;
            }
        }

        for (int i = 1; (seat.x + i < maxX) && (seat.y + i < maxY); i++) {
            if (initStrings[seat.y + i].charAt(seat.x + i) != floor) {
                neighboursCoordinates.add(new Coordinates(seat.x + i, seat.y + i));
                break;
            }
        }

        for (int y = seat.y + 1; y < maxY; y++) {
            if (initStrings[y].charAt(seat.x) != floor) {
                neighboursCoordinates.add(new Coordinates(seat.x, y));
                break;
            }
        }

        for (int i = 1; (seat.x - i >= 0) && (seat.y + i < maxY); i++) {
            if (initStrings[seat.y + i].charAt(seat.x - i) != floor) {
                neighboursCoordinates.add(new Coordinates(seat.x - i, seat.y + i));
                break;
            }
        }

        for (int x = seat.x - 1; x >= 0; x--) {
            if (initStrings[seat.y].charAt(x) != floor) {
                neighboursCoordinates.add(new Coordinates(x, seat.y));
                break;
            }
        }

        for (int i = 1; (seat.x - i >= 0) && (seat.y - i >= 0); i++) {
            if (initStrings[seat.y - i].charAt(seat.x - i) != floor) {
                neighboursCoordinates.add(new Coordinates(seat.x - i, seat.y - i));
                break;
            }
        }

        return neighboursCoordinates;
    }

    private String[] findEquilibriumFor2Part(String[] inputStrings) {
        String[] strings = inputStrings;
        String[] newStrings;
        boolean areIdentical;
        do {
            newStrings = iterateFor2Part(strings);
            areIdentical = areIdentical(strings, newStrings);
            strings = newStrings;
        } while (!areIdentical);

        return strings;
    }

    private String[] iterateFor1Part(String[] initStrings) {
        char empty = "L".charAt(0);
        char occupied = "#".charAt(0);
        char floor = ".".charAt(0);
        String[] newStrings = new String[maxY];

        Set<Coordinates> coordinatesForAdjacentSeats = Stream.of(
            new Coordinates(0, 1), new Coordinates(1, 1), new Coordinates(1, 0),
            new Coordinates(1, -1), new Coordinates(0, -1), new Coordinates(-1, -1),
            new Coordinates(-1, 0), new Coordinates(-1, 1)
        ).collect(Collectors.toSet());

        for (int y = 0; y < maxY; y++) {
            var builder = new StringBuilder();
            for (int x = 0; x < maxX; x++) {

                if (initStrings[y].charAt(x) == empty || initStrings[y].charAt(x) == occupied) {
                    int occupiedNumber = 0;
                    for (Coordinates coordinates: coordinatesForAdjacentSeats) {
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

    private long countOccupiedSeats(String[] strings) {
        long count = 0;
        for (String string : strings) {
            count += string.chars().filter(ch -> ch == '#').count();
        }
        return count;
    }

    private String[] findEquilibriumFor1Part(String[] inputStrings) {
        String[] strings = inputStrings;
        String[] newStrings;
        boolean areIdentical;
        do {
            newStrings = iterateFor1Part(strings);
            areIdentical = areIdentical(strings, newStrings);
            strings = newStrings;
        } while (!areIdentical);

        return strings;
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

    private static class Coordinates {
        int x;
        int y;

        private Coordinates(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
