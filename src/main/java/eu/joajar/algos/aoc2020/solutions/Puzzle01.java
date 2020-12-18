package eu.joajar.algos.aoc2020.solutions;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class Puzzle01 extends AbstractPuzzleDataReader {

    public Puzzle01(String _fileName) {
        super(_fileName);
    }

    @Override
    public String solveFirstPart() throws IllegalArgumentException {
        return findSolution(true);
    }

    @Override
    public String solveSecondPart() throws IllegalArgumentException {
        return findSolution(false);
    }

    /**
     * Uses the assumption that the data doesn't contain any duplicates.
     * @throws IllegalArgumentException if this assumption is not fulfilled.
     */

    private String findSolution(Boolean isSolvingFirstPart) throws IllegalArgumentException {
        var strings = getData();

        throwExceptionForDataWithDuplicates(strings);

        /* Finds the stream of sets of pairs/triplets of elements from the original data. */
        var streamOfSets =
                isSolvingFirstPart ? produceStreamOfSetsOfPairs(strings) : produceStreamOfSetsOfTriplets(strings);

        var setSummingTo2020 = findSetSummingTo2020(streamOfSets);

        return multiply(setSummingTo2020);
    }

    private Stream<Set<String>> produceStreamOfSetsOfTriplets(String[] strings) {
        return Arrays.stream(strings)
                .flatMap(s3 -> Arrays.stream(strings)
                        .filter(s1 -> !s1.equals(s3))
                        .flatMap(s1 -> Arrays.stream(strings)
                                .filter(s2 -> !s2.equals(s1))
                                .filter(s2 -> !s2.equals(s3))
                                .map(s2 -> Set.of(s1, s2, s3))
                        )
                );
    }

    private Stream<Set<String>> produceStreamOfSetsOfPairs(String[] strings) {
        return Arrays.stream(strings)
                .flatMap(s1 -> Arrays.stream(strings)
                        .filter(s2 -> !s1.equals(s2))
                        .map(s2 -> Set.of(s1, s2))
                );
    }

    private Set<String> findSetSummingTo2020(Stream<Set<String>> setStream) {
        return setStream
                .filter(Puzzle01::hasSum2020)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("The solution wasn't found!"));
    }

    private static boolean hasSum2020(Collection<String> collection) {
        return collection
                .stream()
                .mapToInt(Integer::parseInt).sum() == 2020;
    }

    private static String multiply(Collection<String> collection) {
        return collection
                .stream()
                .reduce((a, b) -> String.valueOf(Integer.parseInt(a) * Integer.parseInt(b)))
                .orElseThrow(() -> new IllegalStateException("The solution wasn't found!"));
    }

    private void throwExceptionForDataWithDuplicates(String[] data) throws IllegalArgumentException {
        var set = new HashSet<>();
        if (Arrays.stream(data).filter(set::add).count() != data.length) {
            throw new IllegalArgumentException("The data contains a duplicate!");
        }
    }
}
