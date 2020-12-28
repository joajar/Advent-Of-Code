package eu.joajar.algos.aoc2020.solutions;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class DataReaderAndAbstractPuzzle {
    private final String fileName;

    public DataReaderAndAbstractPuzzle(String fileName) {
        this.fileName = fileName;
    }

    public abstract int getDayNumber();

    public abstract String solveFirstPart();

    public abstract String solveSecondPart();

    public String[] getData() {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(fileName));
            String row;
            List<String> workingList = new ArrayList<>();

            while ((row = bufferedReader.readLine()) != null) {
                workingList.add(row);
            }

            return workingList.toArray(String[]::new);

        } catch (FileNotFoundException e1) {
            System.out.println("A file wasn't found!");
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
        throw new IllegalStateException("Data not found!");
    }
}
