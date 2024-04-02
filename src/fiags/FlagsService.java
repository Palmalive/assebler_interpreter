package fiags;

public class FlagsService {
    public static boolean zeroFlag;
public static boolean carryFlag;
    public static boolean signFlag;

    private static void changeSignFlag(long res){
        if (res >> 31 == 0){
            signFlag = false;
        } else if (res >> 31 == 1) {
            signFlag = true;
        }
    }

    private static void changeZeroFlag(long result){
        zeroFlag = result == 0;
    }

    public static void notMovBBeqBne(long res){
        changeZeroFlag(res);
        changeSignFlag(res);
    }
}

