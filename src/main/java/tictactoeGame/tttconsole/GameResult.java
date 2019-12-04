package tictactoeGame.tttconsole;

public enum GameResult {
    PENDING("Pending"),
    WIN_O("O is a Winner!"),
    WIN_X("X is a Winner!"),
    DRAW("Nobody win, Draw!");
    private final String status;

    GameResult(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
