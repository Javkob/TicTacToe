package tictactoeGame.tttupdate;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import tictactoeGame.tttconsole.FieldStatus;
import tictactoeGame.tttconsole.TTTGame;
import tictactoeGame.tttconsole.TTTPosition;


import java.util.Arrays;


public class TTTController {
    private FieldStatus[][] board;
    private TTTGame game;
    @FXML
    private GridPane grid;
    @FXML
    private Label label;
    @FXML
    public void initialize() {
        System.out.println("Hello");
        game = new TTTGame();
    }
    @FXML
    private void whoWinGame(){
        label.setText(game.checkResult().getStatus());
    }

    @FXML
    private void handleClick(ActionEvent event){
        Object object = event.getSource();
        if(object instanceof Button){
            Button button = (Button) object;
            int col = GridPane.getColumnIndex(button);
            int row = GridPane.getRowIndex(button);
            performAction(new TTTPosition(col,row));
        }
        whoWinGame();
    }
    @FXML
    private void restartButton(ActionEvent event){
        String status = "";
        game = new TTTGame();
        board = new FieldStatus[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Arrays.fill(board[i], FieldStatus.EMPTY);
                Node nodeFromGridPane = getNodeFromGridPane(grid, i, j);
                Button button = (Button) nodeFromGridPane;
                button.setText(status);
            }
        }
        whoWinGame();
    }


    private void performAction(TTTPosition position) {
        game.action(position.column, position.row);
        Node nodeFromGridPane = getNodeFromGridPane(grid, position.column, position.row);
        Button button = (Button) nodeFromGridPane;
        FieldStatus status = game.getFieldStatus(position.column, position.row);
        button.setText(status.getStatus());
    }

    private Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;

    }
}
