import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import tictactoeGame.tttconsole.FieldStatus;
import tictactoeGame.tttconsole.GameResult;
import tictactoeGame.tttconsole.TTTGame;
import tictactoeGame.exceptions.FieldTakenException;
import tictactoeGame.exceptions.GameOverException;

import static org.junit.jupiter.api.Assertions.*;


class TTTGameTest {

    private TTTGame game;
    @BeforeEach
    public void setup(){
        game = new TTTGame();
    }

    @Test
    public void atBeginingFieldsAreEmpty(){
    //given
    game.printBoard();

    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            //when
            FieldStatus field = game.getFieldStatus(i, j);
            //then
            assertEquals(FieldStatus.EMPTY, field);
        }
    }
}
    @Test
    public void firtsActionIsX() {

        //when
        game.action(0,0);
        game.printBoard();

        //then
        assertEquals(FieldStatus.X,game.getFieldStatus(0,0));
    }
    @Test
    public void fieldIsOccupied() {
        //given
        game.action(0,0);
        game.printBoard();

        //when
        assertThrows(FieldTakenException.class, new Executable(){
            @Override
            public void execute() throws  Throwable {
                game.action(0, 0);
            }
        });
    }
    @Test
    public void nextPlayerIsO(){

        game.action(0,0);

        game.action(0,1);
        game.printBoard();

        FieldStatus status = game.getFieldStatus(0, 1);
        assertEquals(FieldStatus.O, status);
    }
    @Test
    public void playerXWinsVerticaly(){
        game.action(0,0);
        game.action(1,0);
        game.action(0,1);
        game.action(2,0);
        game.action(0,2);
        game.printBoard();
        GameResult result = game.checkResult();
        assertEquals(GameResult.WIN_X,result);
    }
    @Test
    public void playerXWinsHorizontally(){
        game.action(0,1);
        game.action(0,0);
        game.action(1,1);
        game.action(1,0);
        game.action(2,1);
        game.printBoard();
        GameResult result = game.checkResult();
        assertEquals(GameResult.WIN_X,result);
    }
    @Test
    public void playerXWinsDiagonallyRight() {
        game.action(0,0);
        game.action(1,0);//o
        game.action(1,1);
        game.action(2,0);//0
        game.action(2,2);
        game.printBoard();
        GameResult result = game.checkResult();
        assertEquals(GameResult.WIN_X,result);
    }
    @Test
    public void playerXWinsDiagonallyLeft() {
        game.action(0,2);
        game.action(1,0);//o
        game.action(1,1);
        game.action(0,1);//0
        game.action(2,0);
        game.printBoard();
        GameResult result = game.checkResult();
        assertEquals(GameResult.WIN_X,result);
    }
    @Test
    public void playerOWinsVerticaly(){
        game.action(0,0);
        game.action(2,0);
        game.action(1,1);
        game.action(2,2);
        game.action(1,2);
        game.action(2,1);
        game.printBoard();
        GameResult result = game.checkResult();
        assertEquals(GameResult.WIN_O,result);
    }
    @Test
    public void cantPerformActionWhenGameEnds_shouldThrow(){
        game.action(0,0);
        game.action(2,0);
        game.action(1,1);
        game.action(2,2);
        game.action(1,2);
        game.action(2,1);
        game.printBoard();

        assertThrows(GameOverException.class, new Executable(){
            @Override
            public void execute() throws Throwable{
                game.action(1,0);
            }
        });
    }
    @Test
    public void draw(){
        game.action(0,0);
        game.action(1,1);
        game.action(0,1);
        game.action(0,2);
        game.action(2,0);
        game.action(1,0);
        game.action(1,2);
        game.action(2,1);
        game.action(2,2);
        game.printBoard();
        GameResult result = game.checkResult();
        assertEquals(GameResult.DRAW,result);
    }



}