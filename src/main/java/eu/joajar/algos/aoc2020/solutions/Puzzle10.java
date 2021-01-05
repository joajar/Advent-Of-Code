package eu.joajar.algos.aoc2020.solutions;

import java.util.*;
import java.util.stream.Collectors;

public class Puzzle10 extends DataReaderAndAbstractPuzzle {
    public Puzzle10(String fileName) {
        super(fileName);
    }

    @Override
    public int getDayNumber() {
        return 10;
    }

    @Override
    public String solveFirstPart() {
        Set<Integer> integers = Arrays.stream(getData())
            .map(Integer::parseInt)
            .collect(Collectors.toSet());

        var output = 0;
        var jolt1diffs = 0;
        var jolt3diffs = 0;

        while (!integers.isEmpty()) {
            var minimal = integers.stream().sorted().min(Integer::compare).get();

            switch (minimal - output) {
                case 1: {
                    ++jolt1diffs;
                    break;
                }
                case 3: {
                    ++jolt3diffs;
                    break;
                }
                default:
                    return "Unable to solve the puzzle!";
            }

            output = minimal;
            integers.remove(minimal);
        }
        return String.valueOf(jolt1diffs * (1 + jolt3diffs));
    }

    /* PART 2
    * The solution below is not elegant, but is not of exponential complexity - so it's not bad ;-)
    * The puzzle is the same as on of the puzzles from "Cracking the coding interview" book, so
    * I will refactor my solution using the book soon.
    * */

    @Override
    public String solveSecondPart() {
        Integer[] adaptersArray = createAdaptersArray();

        List<Set<Integer>> listOfMultipliers = divideResultIntoMultipliers(adaptersArray);

        Map<Set<Integer>, Integer> mapForMultipliers = parseListIntoMap(listOfMultipliers);

        Map<Set<Integer>, Integer> mapForDistinctWaysNumbers = calculateResultsMapForSmallAdaptersSets();


        Long result = 1L;
            for (Set<Integer> set: mapForDistinctWaysNumbers.keySet()){
                result *= (long) Math.pow(mapForDistinctWaysNumbers.get(set), mapForMultipliers.get(set));
            }

        return String.valueOf(result);
    }

    private Map<Set<Integer>, Integer> calculateResultsMapForSmallAdaptersSets() {
        Map<Set<Integer>, Integer> mapForDistinctWaysNumbers = new HashMap<>();

        mapForDistinctWaysNumbers.put(Set.of(0), 1);
        mapForDistinctWaysNumbers.put(Set.of(0, 1), 1);
        mapForDistinctWaysNumbers.put(Set.of(0, 1, 2), 2);
        mapForDistinctWaysNumbers.put(Set.of(0, 1, 2, 3), 4);
        mapForDistinctWaysNumbers.put(Set.of(0, 1, 2, 3, 4), 7);

        return mapForDistinctWaysNumbers;
    }

    private Map<Set<Integer>, Integer> parseListIntoMap(List<Set<Integer>> listOfMultipliers) {
        Map<Set<Integer>, Integer> mapForMultipliers = new HashMap<>();
        listOfMultipliers.forEach(l -> mapForMultipliers.put(l, 0));
        listOfMultipliers.forEach(w -> mapForMultipliers.computeIfPresent(w, (key, value) -> value + 1));

        return mapForMultipliers;
    }

    private List<Set<Integer>> divideResultIntoMultipliers(Integer[] adaptersArray) {
        List<Set<Integer>> listOfMultipliers = new ArrayList<>();
        Set<Integer> multiplier = new HashSet<>(Collections.singletonList(adaptersArray[0]));

        Integer subtrahend = adaptersArray[0];
        for (int i = 0; i < adaptersArray.length - 1; i++) {
            if (adaptersArray[i + 1] - adaptersArray[i] == 3) {
                listOfMultipliers.add(multiplier);
                multiplier = new HashSet<>();
                subtrahend = adaptersArray[i + 1];
            }
            multiplier.add(adaptersArray[i + 1] - subtrahend);
        }
        listOfMultipliers.add(multiplier);

        return listOfMultipliers;
    }

    private Integer[] createAdaptersArray() {
        Set<Integer> integerSet = Arrays.stream(getData())
            .map(Integer::parseInt)
            .collect(Collectors.toSet());

        integerSet.add(0);

        return integerSet.stream()
            .sorted()
            .toArray(Integer[]::new);
    }
}
