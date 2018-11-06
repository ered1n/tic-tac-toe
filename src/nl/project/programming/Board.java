package nl.project.programming;

public class Board {

    private int rows;
    private int cols;

    private char board[][];
    
    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.board = new char[rows][cols];
    }

    public char[][] getBoard() {
        return board;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
    
    public void draw() {
        StringBuilder board = new StringBuilder();

        board.append(new String(new char[cols * 4 + 1]).replace('\0', '-'));

        for (int i = 0; i < rows; i++) {
            board.append("\n| ");

            for (int j = 0; j < cols; j++) {
                if (this.board[i][j] == 0) {
                    board.append("  | ");
                } else {
                    board.append(this.board[i][j]).append(" | ");
                }
            }

            board.append("\n").append(new String(new char[cols * 4 + 1]).replace('\0', '-'));
        }

        System.out.println(board.toString());
    }

    public boolean isNotFilled() {
        boolean isEmptyCells = false;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if(board[i][j] == 0){
                    isEmptyCells = true;
                    break;
                }
            }
        }

        return isEmptyCells;
    }

    public boolean charAtPosition(int x, int y) {
        return board[x][y] != 0;
    }

    /**
     * for testing purposes only
     */
    public void fillBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = 'X';
            }
        }
    }
}
