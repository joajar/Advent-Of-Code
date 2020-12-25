package eu.joajar.algos.aoc2020.solutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Puzzle04 extends AbstractPuzzleDataReader {
    public Puzzle04(String _fileName) {
        super(_fileName);
    }

    @Override
    public int getDayNumber() {
        return 4;
    }

    @Override
    public String solveFirstPart() {
        return preparePart1Result(processData(getData()));
    }

    private String preparePart1Result(List<Map<String, String>> data) {
        int counter = 0;
        for (Map<String, String> passportMap : data) {
            if (passportMap.keySet().size() == 8 || (passportMap.keySet().size() == 7 && !passportMap.containsKey("cid"))) {
                counter++;
            }
        }
        return String.valueOf(counter);
    }

    private List<Map<String, String>> processData(String[] strings) {
        Map<String, String> passportMap = new HashMap<>();
        List<Map<String, String>> passportList = new ArrayList<>();
        for (String row : strings) {
            if (!row.trim().isEmpty()) {
                String[] stringArray = row.split("\\s");
                for (String string : stringArray) {
                    String[] passportData = string.split(":");
                    passportMap.put(passportData[0], passportData[1]);
                }
            } else {
                passportList.add(passportMap);
                passportMap = new HashMap<>();
            }
        }
        passportList.add(passportMap);

        return passportList;
    }

    @Override
    public String solveSecondPart() {
        return null;
    }
}
