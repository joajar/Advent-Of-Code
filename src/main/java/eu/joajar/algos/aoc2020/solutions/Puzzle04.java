package eu.joajar.algos.aoc2020.solutions;

import java.util.*;

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

    @Override
    public String solveSecondPart() {
        return preparePart2Result(processData(getData()));
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

    private String preparePart2Result(List<Map<String, String>> data) {
        int counter = 0;
        for (Map<String, String> listEntry: data) {
            if (passportValidation(listEntry)) {
                counter++;
            }
        }
        return String.valueOf(counter);
    }

    private boolean passportValidation(Map<String, String> passportMap) {
        if (passportMap.keySet().size() != 8 && (passportMap.keySet().size() != 7 || passportMap.containsKey("cid"))) {
            return false;
        }
        boolean isPassportValid = true;
        for (Map.Entry<String, String> entry: passportMap.entrySet()) {
            isPassportValid = isPassportValid && isEntryValid(entry.getKey(), entry.getValue());
        }
        return isPassportValid;
    }

    private boolean isEntryValid(String key, String value) {
        switch (key) {
            case "byr": return 1920 <= Integer.parseInt(value) && Integer.parseInt(value) <= 2002;
            case "iyr": return 2010 <= Integer.parseInt(value) && Integer.parseInt(value) <= 2020;
            case "eyr": return 2020 <= Integer.parseInt(value) && Integer.parseInt(value) <= 2030;
            case "hgt": {
                int i = Integer.parseInt(value.substring(0, value.length() - 2));
                String possibleMeasureUnit = value.substring(value.length() - 2);
                return (value.length() >= 3 && possibleMeasureUnit.equals("cm") && (150 <= i) && (i <= 193))
                    ||(value.length() >= 3 && possibleMeasureUnit.equals("in") && (59 <= i) && (i <= 76));
            }
            case "hcl": return value.matches("#[0-9a-f]{6}");
            case "ecl": return Arrays.asList("amb","blu","brn","gry","grn","hzl","oth").contains(value);
            case "pid": return value.matches("[\\d]{9}");
            case "cid": return true;
            default: return false;
        }
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
}
