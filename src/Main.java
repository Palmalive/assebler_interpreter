import input.Input;
import instructions.InstructionExecutor;
import registers.RegisterService;
import registers.Registers;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {


        var input = Input.formatInputFile();
        InstructionExecutor.executeCode(input);


        System.out.println(RegisterService.get(Registers.RAX));
        System.out.println(RegisterService.get(Registers.RBX));
        System.out.println(RegisterService.get(Registers.RCX));



    }
}