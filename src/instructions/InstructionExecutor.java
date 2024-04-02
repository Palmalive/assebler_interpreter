package instructions;

import exceptions.AssemblerInterpreterException;
import exceptions.InstructionException;
import registers.RegisterService;
import registers.Registers;

import java.util.List;

public class InstructionExecutor {
    static int instructionPointer = 0;

    public static void executeCode(List<String> instructions) {
        initStack();

        while (instructionPointer < instructions.size()) {
            System.out.println(instructions.get(instructionPointer));
            String[] tokens = instructions.get(instructionPointer).split(" ", 2);
            Instructions instruction = Instructions.valueOf(tokens[0].toUpperCase());

            String arguments = "";

            if (tokens.length > 1) {
                arguments = tokens[1];
            }

            try {
                instruction.execute(arguments);
            } catch (InstructionException e) {

                System.out.println("\u001B[31m" + "^".repeat(instructions.get(instructionPointer).length()));
                System.out.println("There is wrong syntax at line " + (instructionPointer + 1) + ".");
                System.out.println(e.getMessage() + "." + "\u001B[0m");
                for (int i = instructionPointer + 1; i < instructions.size(); i++) {
                    System.out.println(instructions.get(i));
                }
                break;
            } catch (AssemblerInterpreterException e) {
                System.out.println("\u001B[31m" + "^".repeat(instructions.get(instructionPointer).length()));
                System.out.println("Stack exception at line " + (instructionPointer + 1) + ".");
                System.out.println(e.getMessage() + "." + "\u001B[0m");
                for (int i = instructionPointer + 1; i < instructions.size(); i++) {
                    System.out.println(instructions.get(i));
                }
                break;
            }
            instructionPointer++;
        }
    }

    private static void initStack() {
        RegisterService.put(Registers.SP, 7L);
    }
}
