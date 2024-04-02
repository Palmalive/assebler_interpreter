package exceptions;

public class FlagAlreadyExistsException extends AssemblerInterpreterException{
    public FlagAlreadyExistsException(String message) {
        super(message);
    }
}
