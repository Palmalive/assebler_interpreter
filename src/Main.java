import input.Input;
import instructions.InstructionExecutor;
import registers.RegisterService;
import registers.Registers;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

         var instructions = Input.formatInputFile();
        InstructionExecutor.executeCode(instructions);

        System.out.println(RegisterService.get(Registers.RAX));




    }
}