package eu.joajar.algos.aoc2020.solutions;

import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;

public class Puzzle14 extends DataReaderAndAbstractPuzzle {
    public Puzzle14(String fileName) {
        super(fileName);
    }

    @Override
    public int getDayNumber() {
        return 14;
    }

    @Override
    public String solveFirstPart() {
        var lines = getData();
        var result = new HashMap<>();
        long maskX_to_1 = 0, maskX_to_0 = 0;

        for (String line : lines) {

            String[] strings = Arrays.stream(
                line
                    .replace(" ", "")
                    .split("[\\[\\]=]")
            )
                .filter(x -> !x.isEmpty())
                .toArray(String[]::new);

            if (strings[0].equals("mask")) {
                maskX_to_1 = Long.parseLong(strings[1].replace("X", "1"), 2);
                maskX_to_0 = Long.parseLong(strings[1].replace("X", "0"), 2);
            } else {
                result.put(
                    Integer.parseInt(strings[1]),
                    maskX_to_1 & Long.parseLong(strings[2]) | maskX_to_0
                );
            }

        }

        return String.valueOf(
            result.values().stream()
                .mapToLong(b -> Long.parseLong(String.valueOf(b)))
                .sum()
        );
    }

    @Override
    public String solveSecondPart() {
        var lines = getData();
        var result = new HashMap<>();
        var mask = "";

        for (String line: lines) {

            String[] strings = Arrays.stream(
                line
                    .replace(" ", "")
                    .split("[\\[\\]=]")
            )
                .filter(x -> !x.isEmpty())
                .toArray(String[]::new);

            if (strings[0].equals("mask")) {
                mask = strings[1];
            } else {
                int significantDigitsNumber = Math.max(
                    Long.toBinaryString(Long.parseLong(mask.replace("X", "1"), 2)).length(),
                    Long.toBinaryString(Long.parseLong(strings[1])).length()
                );
                char[] floatingMemoryAddress = mask.toCharArray();
                char[] dataValue = (new String(new char[36 - Long.toBinaryString(Long.parseLong(strings[1])).length()])
                        .replace("\0", "0") + Long.toBinaryString(Long.parseLong(strings[1])))
                    .toCharArray();

                for (int i = mask.length() - significantDigitsNumber; i < mask.length(); i++) {
                    if (floatingMemoryAddress[i] == '0') {
                        floatingMemoryAddress[i] = dataValue[i];
                    }
                }

                Set<String> s1 = new CopyOnWriteArraySet<>();
                s1.add(String.valueOf(floatingMemoryAddress));

                long exponent = mask.chars().filter(l -> l == 'X').count();

                for (int i = 0; i < exponent; i++) {
                    Set<String> s2 = new CopyOnWriteArraySet<>();
                    for (String str : s1) {
                        String str0 = str.replaceFirst("X", "0");
                        String str1 = str.replaceFirst("X", "1");
                        s2.add(str0);
                        s2.add(str1);
                        s1 = s2;
                    }
                }

                for (String s4 : s1) {
                    result.put(s4, Long.parseLong(strings[2]));
                }
            }
        }

        return String.valueOf(
            result.values().stream()
                .mapToLong(b -> Long.parseLong(String.valueOf(b)))
                .sum()
        );
    }
}
