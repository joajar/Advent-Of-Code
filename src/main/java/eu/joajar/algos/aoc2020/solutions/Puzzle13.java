package eu.joajar.algos.aoc2020.solutions;

import java.util.HashMap;
import java.util.Map;

public class Puzzle13 extends DataReaderAndAbstractPuzzle{
    public Puzzle13(String fileName) {
        super(fileName);
    }

    @Override
    public int getDayNumber() {
        return 13;
    }

    @Override
    public String solveFirstPart() {
        String[] strings = getData();
        String[] busNumbers = strings[1].replace(",x", "").split(",");
        int timeStamp = Integer.parseInt(strings[0]);

        Map<Integer, Integer> mapForResult = new HashMap<>();
        mapForResult.put(0, Integer.MAX_VALUE);
        int key = 0;
        for (String busNumber: busNumbers) {
            var value = (int) Math.round(Integer.parseInt(busNumber) * (Math.ceil(timeStamp * 1.0 / Integer.parseInt(busNumber))));
            if (mapForResult.get(key) > value) {
                mapForResult.remove(key);
                key = Integer.parseInt(busNumber);
                mapForResult.put(key, value);
            }
        }
        Integer keyToReturn = mapForResult.keySet().iterator().next();
        Integer valueToReturn = mapForResult.get(keyToReturn);
        return String.valueOf(keyToReturn * (valueToReturn - timeStamp));
    }

    @Override
    public String solveSecondPart() {
        return "";
    }
}
