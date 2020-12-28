package eu.joajar.algos.aoc2020.solutions;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Uses the assumption that the data doesn't contain any duplicates.
 */
public class Puzzle01 extends DataReaderAndAbstractPuzzle {

    public Puzzle01(String fileName) {
        super(fileName);
    }

    @Override
    public int getDayNumber() {
        return 1;
    }

    @Override
    public String solveFirstPart() {
        try {
            return parse(
                Arrays.stream(getData())
                    /* Finds the stream of sets of pairs of elements (without repetitions) from the original data. */
                    .flatMap(s1 -> Arrays.stream(getData())
                        .filter(s2 -> s1.compareTo(s2) > 0)
                        .map(s2 -> Set.of(s1, s2))
                    )
            );
        } catch (IllegalArgumentException e) {
            return "Unable to solve the puzzle!";
        }
    }

    @Override
    public String solveSecondPart() {
        try {
            return parse(
                Arrays.stream(getData())
                    /* Finds the stream of sets of triplets of elements (without repetitions) from the original data. */
                    .flatMap(s1 -> Arrays.stream(getData())
                        .filter(s2 -> s1.compareTo(s2) > 0)
                        .flatMap(s2 -> Arrays.stream(getData())
                            .filter(s3 -> s2.compareTo(s3) > 0)
                            .map(s3 -> Set.of(s1, s2, s3))
                        )
                    )
            );
        } catch (IllegalArgumentException e) {
            return "Unable to solve the puzzle!";
        }
    }

    private String parse(Stream<Set<String>> stream) throws IllegalArgumentException {
        return stream
            .filter(set -> set.stream().mapToInt(Integer::parseInt).sum() == 2020)
            .findAny()
            .orElseThrow(IllegalArgumentException::new)
            .stream()
            .reduce((a, b) -> String.valueOf(Integer.parseInt(a) * Integer.parseInt(b)))
            .orElseThrow(IllegalArgumentException::new)
            ;
    }
}

























