package eu.joajar.algos.aoc2020.solutions;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Puzzle06 extends DataReaderAndAbstractPuzzle {
    public Puzzle06(String fileName) {
        super(fileName);
    }

    @Override
    public int getDayNumber() {
        return 6;
    }

    @Override
    public String solveFirstPart() {
        return preparePart1Result(processData(getData()));
    }

    private static String preparePart1Result(List<Set<Set<Character>>> charList) {
        int counter = 0;
        for (Set<Set<Character>> answersWithinGroup : charList) {
            Set<Character> setOfAnswersWithinGroup = new HashSet<>();
            for (Set<Character> passengerAnswers: answersWithinGroup) {
                setOfAnswersWithinGroup.addAll(passengerAnswers);
            }
            counter += setOfAnswersWithinGroup.size();
        }
        return String.valueOf(counter);
    }

    @Override
    public String solveSecondPart() {
        return preparePart2Result(processData(getData()));
    }

    private static String preparePart2Result(List<Set<Set<Character>>> charList) {
        int counter = 0;
        for (Set<Set<Character>> answersWithinGroup : charList) {
            Set<Character> setOfAnswersWithinGroup = IntStream.range(97, 123).mapToObj(ch -> (char) ch).collect(Collectors.toSet());
            for (Set<Character> passengerAnswers: answersWithinGroup) {
                setOfAnswersWithinGroup.retainAll(passengerAnswers);
            }
            counter += setOfAnswersWithinGroup.size();
        }
        return String.valueOf(counter);
    }

    private List<Set<Set<Character>>> processData(String[] strings) {
        Set<Set<Character>> answersWithinGroup = new HashSet<>();
        List<Set<Set<Character>>> groupList = new ArrayList<>();
        for (String row : strings) {
            if (!row.trim().isEmpty()) {
                Set<Character> passengerAnswers = row.chars().mapToObj(ch->(char) ch).collect(Collectors.toSet());
                answersWithinGroup.add(passengerAnswers);
            } else {
                groupList.add(answersWithinGroup);
                answersWithinGroup = new HashSet<>();
            }
        }
        groupList.add(answersWithinGroup);

        return groupList;
    }
}
