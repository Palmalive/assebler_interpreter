package registers;

import excaptions.RegisterException;

public class RegisterService{

     private static final long[] registers = new long[16];

    public static void put(Registers register, long value) throws RegisterException {
        registers[register.index] =  switch (register.bits){
            case 64 -> value;
            case 32 -> value & 0xffffffffL;
            case 16 -> value & 0xffffL;
            case 8 -> value & 0xffL;
            default -> throw new RegisterException("Wrong bits count");

        };
    }


    public static long get(Registers register) throws RegisterException {
        return switch (register.bits){
            case 64 -> registers[register.index];
            case 32 -> registers[register.index] & 0xffffffffL;
            case 16 -> registers[register.index] & 0xffffL;
            case 8 ->  registers[register.index] & 0xffL;
            default -> throw new RegisterException("Wrong bits count");
        };
    }
}
