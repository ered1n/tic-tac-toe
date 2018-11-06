package nl.project.programming;

import java.util.Scanner;

public class Player {

    private int player;
    private char character;
    private Board board;

    public Player(int player, Board board) {
        this.player = player;
        this.board = board;

        try {
            this.character = Symbols.SYMBOLS[getPlayer()];
        }
        catch(ArrayIndexOutOfBoundsException exception) {
            this.character = Symbols.SYMBOLS[0];
        }
    }

    public int getPlayer() {
        return player;
    }

    public char getChar() {
        return character;
    }

    public int[] move() {
        Scanner input = new Scanner(System.in);

        int[] max = {board.getCols(), board.getRows()};
        int[] position = new int[2];

        for (int i = 0; i < position.length; i++) {
            String text = (i == 0) ? "Horizontaal" : "Verticaal";

            System.out.print("Player " + getPlayer() + ": Waar wil je je karakter plaatsen? (" + text + "): ");
            int pos = input.nextInt();

            if (pos < 1 || pos > max[i]) {
                System.out.println("Verkeerde positie ingevoerd (alleen 1 t/m " + max[i] + " is geldig)\n");
                move();
                return position;
            } else {
                position[i] = pos - 1;
            }
        }

        if (board.charAtPosition(position[1], position[0])) {
            System.out.println("Op deze positie staal al een teken, probeer het opnieuw\n");
            move();
        } else {
            board.getBoard()[position[1]][position[0]] = getChar();
        }

        return position;
    }

    public boolean hasWon(int[] position) {
        int row = position[1];
        int col = position[0];
        int length = board.getBoard().length;

        boolean diagonal = (row == col) || (col == -1 * row + (length - 1));
        boolean horizontalWin = true, verticalWin = true, diagonalWin = true, antiDiagonalWin = true;

        for (int i = 0; i < length; i++) {
            if (board.getBoard()[row][i] != getChar()) {
                horizontalWin = false;
            }
            if (board.getBoard()[i][col] != getChar()) {
                verticalWin = false;
            }
        }

        if (diagonal) {
            for (int i = 0; i < length; i++) {
                if (board.getBoard()[i][i] != getChar()) {
                    diagonalWin = false;
                }
                if (board.getBoard()[i][-1 * i + (length - 1)] != getChar()) {
                    antiDiagonalWin = false;
                }
            }
        } else {
            diagonalWin = false;
            antiDiagonalWin = false;
        }

        return horizontalWin || verticalWin || diagonalWin || antiDiagonalWin;
    }
}
