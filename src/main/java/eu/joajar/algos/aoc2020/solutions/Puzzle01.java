package eu.joajar.algos.aoc2020.solutions;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Collection;

public class Puzzle01 extends AbstractPuzzleDataReader {

    public Puzzle01(String _fileName) {
        super(_fileName);
    }

    @Override
    public String solveFirstPart() {
        return prepareFirstPartResult(getData());
    }

    /**
     * Uses the assumption that the data doesn't contain any duplicates.
     * @throws IllegalArgumentException if this assumption is not fulfilled.
     */
    private String prepareFirstPartResult(String[] strings) {
        throwExceptionForDataWithDuplicates(strings);

        /* Finds the stream of sets of pairs of original data. */
        var streamOfSetsOfPairs = Arrays.stream(strings)
                .flatMap(s1 -> Arrays.stream(strings)
                        .filter(s2 -> !s1.equals(s2))
                        .map(s2 -> Set.of(s1, s2))
                );

        var setSummingTo2020 = streamOfSetsOfPairs
                .filter(Puzzle01::hasSum2020)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("The solution wasn't found!"));

        return multiply(setSummingTo2020);
    }

    private static boolean hasSum2020(Collection<String> collection) {
        return collection.stream().mapToInt(Integer::parseInt).sum() == 2020;
    }

    private static String multiply(Collection<String> collection) {
        return collection
                .stream()
                .reduce((a, b) -> String.valueOf(Integer.parseInt(a) * Integer.parseInt(b)))
                .orElseThrow(() -> new IllegalStateException("The solution wasn't found!"));
    }

    private void throwExceptionForDataWithDuplicates(String[] data) {
        var set = new HashSet<>();
        if (Arrays.stream(data).filter(set::add).count() != data.length) {
            throw new IllegalArgumentException("The data contains a duplicate!");
        }
    }
}
