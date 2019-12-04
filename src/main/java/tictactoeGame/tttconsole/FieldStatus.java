package tictactoeGame.tttconsole;

public enum FieldStatus {
    EMPTY(" "),
        X("X"),
        O("O");
    private final String status;
    FieldStatus(String status){
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
