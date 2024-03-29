package registers;

public enum Registers {

    RAX(0, 64),
    RBX(1, 64),
    RCX(2, 64),
    RDX(3, 64),
    RSP(4, 64),
    RBP(5, 64),
    RSI(6, 64),
    RDI(7, 64),
    R8(8, 64),
    R9(9, 64),
    R10(10, 64),
    R11(11, 64),
    R12(12, 64),
    R13(13, 64),
    R14(14, 64),
    r15(15, 64),
    EAX(0, 32),
    EBX(1, 32),
    ECX(2, 32),
    EDX(3, 32),
    ESP(4, 32),
    EBP(5, 32),
    ESI(6, 32),
    EDI(7, 32),
    R8D(8, 32),
    R9D(9, 32),
    R10D(10, 32),
    R11D(11, 32),
    R12D(12, 32),
    R13D(13, 32),
    R14D(15, 32),
    R15D(15, 32),
    AX(0, 16),
    BX(1, 16),
    CX(2, 16),
    DX(3, 16),
    SP(4, 16),
    BP(5, 16),
    SI(6, 16),
    DI(7, 16),
    R8W(8, 16),
    R9W(9, 16),
    R10W(10, 16),
    R11W(11, 16),
    R12W(12, 16),
    R13W(13, 16),
    R14W(14, 16),
    R15W(15, 16),
    AL(0, 8),
    BL(1, 8),
    CL(2, 8),
    DL(3, 8),
    R8B(8, 8),
    R9B(9, 8),
    R10B(10, 8),
    R11B(11, 8),
    R12B(12, 8),
    R13B(13, 8),
    R14B(14, 8),
    R15B(15, 8),
    ;

    final int index;
    final int bits;

    Registers(int index, int bits) {
        this.index = index;
        this.bits = bits;
    }

}
