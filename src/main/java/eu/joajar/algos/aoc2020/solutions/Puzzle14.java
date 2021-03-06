package eu.joajar.algos.aoc2020.solutions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.regex.Pattern;

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
        var bitMaskPattern = Pattern.compile("([01X]{36})$");
        long maskX_to_1 = 0, maskX_to_0 = 0;
        Map<Long, Long> result = new HashMap<>();

        for (String line: lines) {

            var matcher = bitMaskPattern.matcher(line);

            if (matcher.find()) {
                maskX_to_1 = Long.parseLong(matcher.group().replace("X", "1"), 2);
                maskX_to_0 = Long.parseLong(matcher.group().replace("X", "0"), 2);
            } else {
                var strings = Arrays.stream(
                    line
                        .replace(" ", "")
                        .replace("mem", "")
                        .split("[\\[\\]=]")
                )
                    .filter(x -> !x.isEmpty())
                    .toArray(String[]::new);

                result.put(
                    Long.parseLong(strings[0]),
                    maskX_to_1 & Long.parseLong(strings[1]) | maskX_to_0
                );
            }

        }
        return calculateResult(result);
    }

    @Override
    public String solveSecondPart() {
        var lines = getData();
        var pattern = Pattern.compile("([01X]{36})$");
        var mask = "";
        Map<Long, Long> result = new HashMap<>();

        for (String line: lines) {

            var strings = Arrays.stream(
                line
                    .replace(" ", "")
                    .replace("mem", "")
                    .split("[\\[\\]=]")
            )
                .filter(x -> !x.isEmpty())
                .toArray(String[]::new);

            var matcher = pattern.matcher(line);
            if (matcher.find()) {
                mask = matcher.group();
            } else {
                var memory = Long.toBinaryString(Long.parseLong(strings[0]));
                var value = Long.parseLong(strings[1]);
                var significantDigitsNumber = Math.max(
                    Long.toBinaryString(Long.parseLong(mask.replace("X", "1"), 2)).length(),
                    memory.length()
                );
                var maskToCharArray = mask.toCharArray();
                var dataValue = (new String(new char[36 - memory.length()])
                    .replace("\0", "0") + memory)
                    .toCharArray();

                for (var i = mask.length() - significantDigitsNumber; i < mask.length(); i++) {
                    if (maskToCharArray[i] == '0') {
                        maskToCharArray[i] = dataValue[i];
                    }
                }

                Set<String> stringSet1 = new CopyOnWriteArraySet<>();
                stringSet1.add(String.valueOf(maskToCharArray));

                var howManyX = mask.chars().filter(l -> l == 'X').count();

                for (var i = 0; i < howManyX; i++) {
                    Set<String> stringSet2 = new CopyOnWriteArraySet<>();
                    for (String str: stringSet1) {
                        stringSet2.add(str.replaceFirst("X", "0"));
                        stringSet2.add(str.replaceFirst("X", "1"));
                        stringSet1 = stringSet2;
                    }
                }

                for (var s4: stringSet1) {
                    result.put(Long.parseLong(s4, 2), value);
                }
            }
        }
        return calculateResult(result);
    }

    private String calculateResult(Map<Long, Long> map) {
        return String.valueOf(
            map.values().stream()
                .mapToLong(value -> value)
                .sum()
        );
    }
}
