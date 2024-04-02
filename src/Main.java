import input.Input;
import instructions.InstructionExecutor;
import labels.LabelService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {


        var input = Input.formatInputFile();
        InstructionExecutor.executeCode(input);


        System.out.println(LabelService.getAllLabels());



    }
}