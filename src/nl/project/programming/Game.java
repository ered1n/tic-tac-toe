package nl.project.programming;

import java.util.Scanner;

public class Game {

    private static Player[] players = new Player[2];

    public static void main(String[] args) {
        initGame();
    }

    private static void initGame() {
        Board board = new Board(3, 3);

        players[0] = new Player (0, board);
        Scanner input = new Scanner(System.in);

        System.out.print("Is de 2e speler een bot? (y/n): ");
        String answer = input.nextLine();

        if (answer.equals("y")) {
            System.out.println("bot code");
            return;
        } else if (answer.equals("n")) {
            players[1] = new Player(1, board);
        }

        while (board.isNotFilled()) {
            for (Player player : players) {
                if (board.isNotFilled()) {
                    board.draw();

                    int[] position = player.move();

                    if (player.hasWon(position)) {
                        board.draw();
                        System.out.println("Player " + player.getPlayer() + " heeft gewonnen!");
                        return;
                    }
                } else {
                    System.out.println("Einde van het spel, het is een draw!");
                    return;
                }
            }
        }
    }
}
