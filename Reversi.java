import java.util.Scanner;

/**
 * CS18000 - Fall 2018
 *
 * Project 2 - Reversi
 *
 * Abstraction of and launcher for a Reversi game
 *
 * @author Aarya Barve, abarve@purdue.edu
 *
 * @version October 12th 2018
 */
public class Reversi {

    public static boolean isEmpty(Point[] pOint) {

        boolean check = true;
        int trace = 0;

        for (int i = 0; i < 64; i++) {
            if (pOint[i].x == -1 && pOint[i].y == -1) {
                trace++;
            }
        }

        if (trace == 63) {
            check = false;
        }

        return check;
    }

    //Check whether a Point is the Points array or not

    public static boolean contains(Point[] points, Point p) {

        boolean check = true;

        for (int i = 0; i < 64; i++) {
            if (points[i].x == p.x && points[i].y == p.y) {
                check = true;
                break;
            } else {
                check = false;
                continue;
            }
        }

        return check;

    }

    public static void start(Game g) {

        Scanner input = new Scanner(System.in);

        String draw = "It is a draw.";
        String whiteWins = "White wins: " + g.wScore + ":" + g.bScore;
        String blackWins = "Black wins: " + g.bScore + ":" + g.wScore;
        String exit = "Exiting!";

        String blackMove = "Place move (Black): row then column: ";
        String blackSkipping = "Black needs to skip... Passing to white";
        String invalidBlackMove = "Invalid move!\nPlace move (Black): row then column: ";
        String blackScoreShow = "Black: " + g.bScore + " White: " + g.wScore;

        String whiteSkipping = "White needs to skip... Passing to Black";
        String whiteMove = "Place move (White): row then column: ";
        String invalidWhiteMove = "Invalid move!\nPlace move (White): row then column: ";
        String whiteScoreShow = "White: " + g.wScore + " Black: " + g.bScore;

        char player = 'B';
        char opponent = 'W';
        boolean check = true;
        int end = 2;

        while (check) {

            boolean track = true;

            g.getPlaceableLocations(player, opponent);

            if (!isEmpty(g.empty())) {
                check = false;
                continue;
            }

            if (g.skip() == 0) {
                char temp;
                temp = player;
                player = opponent;
                opponent = temp;
                g.getPlaceableLocations(player, opponent);
                if (g.skip() == 0) {
                    end = g.gameResult(g.placeablePositions,
                            g.placeablePositions);
                    check = false;
                    continue;
                }
            }

            g.showPlaceableLocations(g.getPlaceableLocations(player, opponent), player, opponent);
            g.displayBoard(g);

            if (player == 'B') {
                System.out.println(blackMove);
            } else {
                System.out.println(whiteMove);
            }

            while (track) {
                String move = "00";
                move = input.nextLine();
                while (move.isEmpty()) {
                    if (player == 'B') {
                        System.out.println(invalidBlackMove);
                    } else {
                        System.out.println(invalidWhiteMove);
                    }
                    if (input.hasNextLine()) {
                        move = input.nextLine();
                    }
                }
                Point p = new Point(0, 0);
                if (move.charAt(0) >= '1' && move.charAt(0) <= '8' &&
                    move.length() == 2) {
                    int num1 = Integer.parseInt(move.substring(0, 1));
                    int num2 = Integer.parseInt(move.substring(1, 2));
                    p.setPoint(num1 - 1, num2 - 1);
                } else if (!move.equals("exit")) {
                    if (player == 'B') {
                        System.out.println(invalidBlackMove);
                    } else {
                        System.out.println(invalidWhiteMove);
                    }
                    continue;
                } else {
                    System.out.println(exit);
                    track = false;
                    check = false;
                    continue;
                }
                if (contains(g.placeablePositions, p)) {
                    g.placeMove(p, player, opponent);
                    g.updateScores();
                    if (player == 'B') {
                        System.out.println("Black: " + g.bScore + " White: " + g.wScore);
                    } else {
                        System.out.println("White: " + g.wScore + " Black: " + g.bScore);
                    }
                    char temp;
                    temp = player;
                    player = opponent;
                    opponent = temp;
                    break;
                } else {
                    if (player == 'B') {
                        System.out.println(invalidBlackMove);
                    } else {
                        System.out.println(invalidWhiteMove);
                    }
                }

            }

            end = g.gameResult(g.placeablePositions,
                    g.placeablePositions);

            if (end != 2) {
                check = false;
                continue;
            }

        }
        if (end == -1) {
            g.displayBoard(g);
            System.out.println("Black wins: " + g.bScore + ":" + g.wScore);
        } else if (end == 0) {
            g.displayBoard(g);
            System.out.println("It is a draw.");
        } else if (end == 1) {
            g.displayBoard(g);
            System.out.println("White wins: " + g.wScore + ":" + g.bScore);
        }

    }

    public static void main(String[] args) {
        Game b = new Game();
        start(b);

    }
}
