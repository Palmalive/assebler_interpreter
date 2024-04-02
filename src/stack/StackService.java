package stack;

import registers.RegisterService;
import registers.Registers;

public class StackService {
    static long[] stack = new long[8];

    public static void push(long value){
        stack[(int) RegisterService.get(Registers.SP)] = value;
    }

    public static long pop(){
        return stack[(int) RegisterService.get(Registers.SP)+1];
    }

}
