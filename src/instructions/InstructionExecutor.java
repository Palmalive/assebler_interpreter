package instructions;

import exceptions.RegisterException;

import java.util.List;

public class InstructionExecutor {
    int instructionPointer;

    public InstructionExecutor() {
        instructionPointer = 0;
    }

    public void startProgram(List<String> instructions) {
        while (instructionPointer < instructions.size()) {
            String[] tokens = instructions.get(instructionPointer).split(" ", 2);
            instructionPointer++;
            Instructions instruction = Instructions.valueOf(tokens[0].toUpperCase());
            instruction.execute(tokens[1]);
        }
    }
}
