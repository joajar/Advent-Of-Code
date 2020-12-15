package eu.joajar.algos.aoc2020.solutions;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Puzzle01 {
    public static int part1result() {
        return preparePart1Result(getData());
    }

    private static int preparePart1Result(int[] integers) {
        for (int i = 0; i < integers.length; i++) {
            for (int j = i + 1; j < integers.length; j++) {
                if (integers[i] + integers[j] == 2020) {
                    return integers[i] * integers[j];
                }
            }
        }
        throw new IllegalStateException("The solution wasn't found");
    }

    private static int[] getData() {
        BufferedReader bufferedReader = null;
        try {

            bufferedReader = new BufferedReader(new FileReader("src/main/resources/InputPuzzle01.txt"));
            String row;
            List<Integer> workingList = new ArrayList<>();

            while ((row = bufferedReader.readLine()) != null) {
                workingList.add(Integer.parseInt(row));
            }

            return workingList.stream().mapToInt(i->i).toArray();

        } catch (FileNotFoundException e1) {
            System.out.println("Do not found a file!");
            e1.printStackTrace();
        } catch (IOException e2) {
            System.out.println("IO error!");
            e2.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e3) {
                System.out.println("IO error!");
                e3.printStackTrace();
            }
        }
        throw new IllegalStateException("The data wasn't found");
    }
}
