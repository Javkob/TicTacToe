package tictactoeGame.tttconsole;


import java.util.Scanner;

public class MainTTT {
    public static void main(String[] args) {
        TTTGame game = new TTTGame();

        while (game.checkResult() == GameResult.PENDING) {
            game.printBoard();
            TTTPosition position = getPosition();
            game.action(position.column, position.row);
        }
        System.out.println(game.checkResult());

    }

    private static TTTPosition getPosition() {
        Scanner scanner = new Scanner(System.in);

        while (true){
            System.out.println("\nPodaj numer pola od 1 do 9");
            String next = scanner.next();
            try{
                int value = Integer.parseInt(next);
                if (value > 0 && value < 10) {
                    int col = (value - 1)%3;
                    int row = (value - 1)/3;
                    return new TTTPosition(col, row);
                }
            }catch (Exception e){
                System.out.println("Nope");
        }}

    }
}
