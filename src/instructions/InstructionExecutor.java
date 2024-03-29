package instructions;

import java.util.List;

public class InstructionExecutor {
    static int instructionPointer;

    public InstructionExecutor() {
        instructionPointer = 0;
    }

    public static void executeCode(List<String> instructions) {
        while (instructionPointer < instructions.size()) {
            String[] tokens = instructions.get(instructionPointer).split(" ", 2);
            instructionPointer++;
            Instructions instruction = Instructions.valueOf(tokens[0].toUpperCase());
            instruction.execute(tokens[1]);
        }
    }
}
