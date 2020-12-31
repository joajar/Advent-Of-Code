package eu.joajar.algos.aoc2020.solutions;

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

    @Override
    public String solveFirstPart() {
        return preparePart1Result(processData(getData()));
    }

    @Override
    public String solveSecondPart() {
        return "";
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

        for (String[] bags: data) {
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

        private String getColour() {
            return colour;
        }

        public Set<Bag> getBagReferences() {
            return bagReferences;
        }
    }
}


