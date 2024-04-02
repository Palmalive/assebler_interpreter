package instructions;

import exceptions.FlagAlreadyExistsException;
import exceptions.InstructionException;
import labels.LabelService;

import java.util.List;

public class InstructionExecutor {
    static int instructionPointer;

    public InstructionExecutor() {
        instructionPointer = 0;
    }

    public static void executeCode(List<String> instructions) {
        while (instructionPointer < instructions.size()) {
            System.out.println(instructions.get(instructionPointer));
            String[] tokens = instructions.get(instructionPointer).split(" ", 2);
            if (!tokens[0].endsWith(":")) {
                Instructions instruction = Instructions.valueOf(tokens[0].toUpperCase());
                try {
                    instruction.execute(tokens[1]);
                } catch (InstructionException e) {

                    System.out.println("\u001B[31m" + "^".repeat(instructions.get(instructionPointer).length()));
                    System.out.println("There is wrong syntax at line " + (instructionPointer + 1) + ".");
                    System.out.println(e.getMessage() + "." + "\u001B[0m");
                    for (int i = instructionPointer + 1; i < instructions.size(); i++) {
                        System.out.println(instructions.get(i));
                    }
                    break;
                }
            } else {
                try {
                    String label = tokens[0].substring(0, tokens[0].length() - 1);
                    String instruction = tokens.length > 1 ? tokens[1] : instructions.get(++instructionPointer);
                    LabelService.addLabel(label, instruction);

                } catch (FlagAlreadyExistsException e) {
                    System.out.println("\u001B[31m" + "^".repeat(instructions.get(instructionPointer).length()));
                    System.out.println(e.getMessage() + " at line " + (instructionPointer + 1) + "." + "\u001B[0m");
                    for (int i = instructionPointer + 1; i < instructions.size(); i++) {
                        System.out.println(instructions.get(i));
                    }
                }
            }
            instructionPointer++;
        }
    }
}
