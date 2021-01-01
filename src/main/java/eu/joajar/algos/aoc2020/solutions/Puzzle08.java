package eu.joajar.algos.aoc2020.solutions;

import java.util.HashSet;
import java.util.Set;

public class Puzzle08 extends DataReaderAndAbstractPuzzle {
    public Puzzle08(String fileName) {
        super(fileName);
    }

    @Override
    public int getDayNumber() {
        return 8;
    }

    @Override
    public String solveFirstPart() {
        return calculateAccumulatorValue(transform(getData()));
    }

    private boolean doesInstructionsTerminate = false;

    private String calculateAccumulatorValue(Instruction[] instructions) {
        Set<Integer> linesVisited = new HashSet<>();
        int i = 0, accumulator = 0;
        while (linesVisited.add(i)) {

            switch (instructions[i].operation) {
                case "nop" -> ++i;
                case "acc" -> {
                    accumulator = (instructions[i].sign == '-') ? accumulator - instructions[i].argument : accumulator + instructions[i].argument;
                    ++i;
                }
                case "jmp" -> i = (instructions[i].sign == '-') ? i - instructions[i].argument : i + instructions[i].argument;
            }
            if (i < 0 || i >= instructions.length) {
                doesInstructionsTerminate = true;
                break;
            }

        }
        return String.valueOf(accumulator);
    }

    private Instruction[] transform(String[] lines) {
        Instruction[] instructions = new Instruction[lines.length];
        for (int i = 0; i < lines.length; i++) {
            instructions[i] = Instruction.parse(lines[i]);
        }
        return instructions;
    }

    private Instruction[] produceChangedInstructions(Instruction[] instructions, int index) {
        Instruction[] transformedInstructions = instructions;
        if (instructions[index].operation.equals("nop")) {
            transformedInstructions[index] = Instruction.nopIntoJmp(instructions[index]);
        } else if (instructions[index].operation.equals("jmp")) {
            transformedInstructions[index] = Instruction.jmpIntoNop(instructions[index]);
        }
        return transformedInstructions;
    }

    @Override
    public String solveSecondPart() {
        int i = 0;
        String toReturn = "";
        while (!doesInstructionsTerminate) {
            toReturn = calculateAccumulatorValue(produceChangedInstructions(transform(getData()), i));
            ++i;

        }
        return toReturn;
    }

    private record Instruction(String operation, char sign, int argument) {
        private static Instruction parse(String line) {
            return new Instruction(
                line.substring(0, 3),
                line.charAt(4),
                Integer.parseInt(line.substring(5))
            );
        }

        private static Instruction nopIntoJmp(Instruction instruction) {
            return new Instruction(
                "jmp",
                instruction.sign,
                instruction.argument
            );
        }

        private static Instruction jmpIntoNop(Instruction instruction) {
            return new Instruction(
                "nop",
                instruction.sign,
                instruction.argument
            );
        }
    }
}
