package eu.joajar.algos.aoc2020.solutions;

public class Puzzle09 extends DataReaderAndAbstractPuzzle {
    public Puzzle09(String fileName) {
        super(fileName);
    }

    @Override
    public int getDayNumber() {
        return 9;
    }

    @Override
    public String solveFirstPart() {
        int preambleLength = 25; //25
        String[] stringWithNumbers = getData();

        for (int k = preambleLength; k < stringWithNumbers.length; k++) {
            int checkNumber = 0;

            for (int i = 1; i <= preambleLength; i++) {
                for (int j = 1; j <= preambleLength; j++) {
                    if (Integer.parseInt(stringWithNumbers[k - i]) != Integer.parseInt(stringWithNumbers[k - j])) {
                        if (Integer.parseInt(stringWithNumbers[k - i]) + Integer.parseInt(stringWithNumbers[k - j]) != Integer.parseInt(stringWithNumbers[k])) {
                            ++checkNumber;
                        }
                    } else {
                        ++checkNumber;
                    }
                }
            }

            if (checkNumber == preambleLength * preambleLength) {
                return stringWithNumbers[k];
            }
        }
        return "Unable to solve the puzzle!";
    }

    @Override
    public String solveSecondPart() {
        String[] stringWithNumbers = getData();
        long invalidNumberFromFirstPart = Long.parseLong(solveFirstPart());
        int maxComponentsNumber = 25;//MAGIC NUMBER
        int componentsNumber = 2;
        while (componentsNumber <= maxComponentsNumber) {
            for (int i = 0; i < stringWithNumbers.length - (componentsNumber - 1); i++) {
                long sum = 0;

                for (int j = 0; j < componentsNumber; j++) {
                    sum += Long.parseLong(stringWithNumbers[i + j]);
                }

                if (sum == invalidNumberFromFirstPart) {
                    long maxNumber = Long.parseLong(stringWithNumbers[i]), minNumber = Long.parseLong(stringWithNumbers[i]);
                    for (int j = 1; j < componentsNumber; j++) {
                        maxNumber = Math.max(Long.parseLong(stringWithNumbers[i + j]), maxNumber);
                        minNumber = Math.min(Long.parseLong(stringWithNumbers[i + j]), minNumber);
                    }
                    return String.valueOf(maxNumber + minNumber);
                }
            }
            ++componentsNumber;
        }
        return "Unable to solve the puzzle!";
    }
}
