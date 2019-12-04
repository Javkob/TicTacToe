package tictactoeGame.exceptions;

public class FieldTakenException extends RuntimeException{

    public FieldTakenException() {
    }

    public FieldTakenException(String message) {
        super(message);
    }
}
