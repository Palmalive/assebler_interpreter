package instructions;

import exceptions.InstructionException;

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
            Instructions instruction = Instructions.valueOf(tokens[0].toUpperCase());
            try {
                instruction.execute(tokens[1]);
            } catch (InstructionException e) {
                System.out.println("\u001B[31m" + "^".repeat(instructions.get(instructionPointer).length()));
                System.out.println("There is wrong syntax at line " + (instructionPointer+1)+".");
                System.out.println(e.getMessage() + "." + "\u001B[0m");
                for (int i = instructionPointer+1; i< instructions.size(); i++){
                    System.out.println(instructions.get(i));
                }
                break;
            }
            instructionPointer++;
        }
    }



}
