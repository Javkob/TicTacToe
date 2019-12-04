package tictactoeGame.tttconsole;


import tictactoeGame.exceptions.FieldTakenException;
import tictactoeGame.exceptions.GameOverException;

import java.util.Arrays;

public class TTTGame {
    private FieldStatus[][] board;
    private boolean isPlayerXTurn = true;
    public TTTGame() {
        board = new FieldStatus[3][3];
        for (int i = 0; i < 3; i++) {
            Arrays.fill(board[i],FieldStatus.EMPTY);
        }
    }



    public FieldStatus getFieldStatus(int column, int row) {
        return board[row][column];
    }

    public void printBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j <3 ; j++) {
                FieldStatus field = getFieldStatus(j, i);
                System.out.print(field.getStatus());
                if(j<2) System.out.print("|");
            }
            if(i<2) System.out.println("\n-+-+-");
        }
    }

    public void action(int column, int row) {
        GameResult result = checkResult();
        if(result != GameResult.PENDING){
            throw new GameOverException("Go home");
        }
        FieldStatus status = getFieldStatus(column, row);
        if(status != FieldStatus.EMPTY){
            throw new FieldTakenException("Field is TAKEN!");
        }
        if(isPlayerOTurn()){
            board[row][column] = FieldStatus.O;
        }else{
            board[row][column] = FieldStatus.X;
        }
        isPlayerXTurn = !isPlayerXTurn;
    }
    public boolean isPlayerXTurn(){
        return isPlayerXTurn;
    }
    public boolean isPlayerOTurn(){
        return !isPlayerXTurn;
    }

    public GameResult checkResult() {
        FieldStatus checkedField = FieldStatus.X;
        if (isPlayerWin(checkedField.X)) {
            return GameResult.WIN_X;
        }
        if (isPlayerWin(checkedField.O)) {
            return GameResult.WIN_O;
        }
        if(isBoardFull()){
            return GameResult.DRAW;
        }
        return GameResult.PENDING;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                FieldStatus status = getFieldStatus(i, j);
                if(status == FieldStatus.EMPTY){
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isPlayerWin(FieldStatus checkedField) {
        return isVerticalWin(checkedField) ||
                isHorizontallyWin(checkedField) ||
                isRightDiagonallyWin(checkedField) ||
                isLeftDiagonallyWin(checkedField);
    }

    private boolean isVerticalWin(FieldStatus checkedField) {
        for (int i = 0; i < 3; i++) {
            if( getFieldStatus(i, 0) == checkedField &&
                    getFieldStatus(i, 1) == checkedField &&
                    getFieldStatus(i, 2) == checkedField)
                return true;
        }
        return false;
    }
    private boolean isHorizontallyWin(FieldStatus checkedField) {
        for (int i = 0; i < 3; i++) {
            if( getFieldStatus(0, i) == checkedField &&
                    getFieldStatus(1, i) == checkedField &&
                    getFieldStatus(2, i) == checkedField)
                return true;
        }
        return false;
    }
    private  boolean isRightDiagonallyWin(FieldStatus checkedField){
        return getFieldStatus(0, 0) == checkedField &&
                getFieldStatus(1, 1) == checkedField &&
                getFieldStatus(2, 2) == checkedField;
    }
    private  boolean isLeftDiagonallyWin(FieldStatus checkedField){
        return getFieldStatus(0, 2) == checkedField &&
                getFieldStatus(1, 1) == checkedField &&
                getFieldStatus(2, 0) == checkedField;
    }

}
