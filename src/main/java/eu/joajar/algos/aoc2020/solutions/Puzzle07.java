package eu.joajar.algos.aoc2020.solutions;

import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Puzzle07 extends DataReaderAndAbstractPuzzle {
    public Puzzle07(String fileName) {
        super(fileName);
    }

    @Override
    public int getDayNumber() {
        return 7;
    }

    /**
     * Part 1
     */

    @Override
    public String solveFirstPart() {
        return preparePart1Result(processData(getData()));
    }

    private List<String[]> processData(String[] lines) {
        return Arrays.stream(lines)
            .map(line -> line.replaceAll("[0-9]", "")
                .replaceAll(" no other bags.", "")
                .replace(" bags contain", "")
                .replace(" bag.", "")
                .replace(" bags.", "")
                .replace(" bag,", "")
                .replace(" bags,", "")
                .split("  ")
            )
            .collect(Collectors.toList());
    }

    Set<Bag> bagSet = new HashSet<>();
    Set<String> setOfBagNamesThatContainShinyBag = new HashSet<>();

    private String preparePart1Result(List<String[]> data) {

        for (String[] bags : data) {
            if (findOptionalBag(bags[0]).isEmpty()) {
                Bag newBag = new Bag(bags[0]);
                bagSet.add(newBag);
            }

            for (int i = 1; i < bags.length; i++) {
                String currentColour = bags[i];
                if (findOptionalBag(currentColour).isEmpty()) {
                    Bag newBag = new Bag(currentColour,
                        findOptionalBag(bags[0]).isPresent() ? findOptionalBag(bags[0]).get() : new Bag(bags[0])
                    );
                    bagSet.add(newBag);
                } else {
                    findOptionalBag(currentColour).get().bagReferences.add(
                        findOptionalBag(bags[0]).isPresent() ? findOptionalBag(bags[0]).get() : new Bag(bags[0])
                    );
                }
            }
        }

        String ToSolveFirstPart = "shiny gold";
        solve1PuzzleByRecursion(ToSolveFirstPart);
        return String.valueOf(setOfBagNamesThatContainShinyBag.size());
    }

    private void solve1PuzzleByRecursion(String bagColour) {
        if ((findOptionalBag(bagColour).isPresent()) && findOptionalBag(bagColour).get().getBagReferences().size() >= 1) {
            for (Bag bag : findOptionalBag(bagColour).get().getBagReferences()) {
                if (!setOfBagNamesThatContainShinyBag.contains(bag.getColour())) {
                    setOfBagNamesThatContainShinyBag.add(bag.getColour());
                    solve1PuzzleByRecursion(bag.getColour());
                }
            }
        }
    }

    private Optional<Bag> findOptionalBag(String bagColour) {
        return bagSet
            .stream()
            .filter(bag -> bag.getColour().equals(bagColour))
            .findAny();
    }

    @Getter
    private static class Bag {
        private final String colour;
        private final Set<Bag> bagReferences;

        private Bag(String colour) {
            this.colour = colour;
            this.bagReferences = new HashSet<>();
        }

        private Bag(String colour, Bag child) {
            this.colour = colour;
            this.bagReferences = Stream.of(child).collect(Collectors.toCollection(HashSet::new));
        }
    }

    /**
     * Part 2
     */

    @Override
    public String solveSecondPart() {
        return preparePart2Result(processData2(getData()));
    }

    private List<String[]> processData2(String[] lines) {
        return Arrays.stream(lines)
            .map(line -> line.replaceAll(" no other bags.", "")
                .replace("bags contain", "")
                .replace(" bag.", "")
                .replace(" bags.", "")
                .replace("bag,", "")
                .replace("bags,", "")
                .split("  ")
            )
            .collect(Collectors.toList());
    }

    Set<Bag2> bagSet2 = new HashSet<>();
    Set<String> setOfBagNamesContainedInShinyBag = new HashSet<>();
    int finalNumberOfBags = 0;

    private String preparePart2Result(List<String[]> data) {

        for (String[] bags : data) {
            if (findOptionalBag2(bags[0]).isEmpty()) {
                bagSet2.add(new Bag2(bags[0]));
            }
            Bag2 newBag0 = findOptionalBag2(bags[0]).get();

            for (int i = 1; i < bags.length; i++) {
                int childrenNumber = Integer.parseInt(bags[i].substring(0, 1));
                String childColour = bags[i].substring(2);

                Bag2 newBag;

                if (findOptionalBag2(childColour).isEmpty()) {
                    newBag = new Bag2(childColour);
                    bagSet2.add(newBag);
                } else {
                    newBag = findOptionalBag2(childColour).get();
                }

                newBag0.bagReferences.put(newBag, childrenNumber);
            }
        }
        //
        String ToSolveSecondPart = "shiny gold";
        solve2PuzzleByRecursion(ToSolveSecondPart);
        return String.valueOf(finalNumberOfBags);
    }

    private void solve2PuzzleByRecursion(String bagColour) {
        if ((findOptionalBag2(bagColour).isPresent()) && findOptionalBag2(bagColour).get().getBagReferences().size() >= 1) {
            for (Bag2 bag2 : findOptionalBag2(bagColour).get().getBagReferences().keySet()) {
                setOfBagNamesContainedInShinyBag.add(bag2.getColour());
                finalNumberOfBags += findOptionalBag2(bagColour).get().getBagReferences().get(bag2);
                for (int i = 0; i < findOptionalBag2(bagColour).get().getBagReferences().get(bag2); i++) {
                    solve2PuzzleByRecursion(bag2.colour);
                }
            }
        }
    }

    private Optional<Bag2> findOptionalBag2(String bagColour) {
        return bagSet2
            .stream()
            .filter(bag -> bag.getColour().equals(bagColour))
            .findAny();
    }

    @Getter
    private static class Bag2 {
        private final String colour;
        private final Map<Bag2, Integer> bagReferences;

        private Bag2(String colour) {
            this.colour = colour;
            this.bagReferences = new HashMap<>();
        }
    }
}


