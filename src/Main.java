import excaptions.RegisterException;
import registers.RegisterService;
import registers.Registers;

public class Main {
    public static void main(String[] args) throws RegisterException {

        RegisterService.put(Registers.RAX, 0x0102030405060708L);
        System.out.println(Long.toHexString(RegisterService.get(Registers.RAX)));
        System.out.println(Long.toHexString(RegisterService.get(Registers.EAX)));
        System.out.println(Long.toHexString(RegisterService.get(Registers.AX)));
        System.out.println(Long.toHexString(RegisterService.get(Registers.AL)));
    }
}