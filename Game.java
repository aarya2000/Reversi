import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * CS18000 - Fall 2018
 *
 * Project 2 - Reversi
 *
 * Implementation of the game mechanics in Reversi
 *
 * @author Aarya Barve, abarve@purdue.edu
 *
 * @version October 12th 2018
 *
 */

public class Game {

    public Point pOint;
    private final char[][] board;
    public int wScore;
    public int bScore;
    public int remaining;
    private final char[] boardX = new char[]{'1', '2', '3', '4', '5', '6', '7', '8'};
    public Point[] placeablePositions = new Point[64];
    public Point[] temp = new Point[64];
    public Point[] empty = new Point[64];

    public Game() {
        board = new char[][]{
                {'_', '_', '_', '_', '_', '_', '_', '_', },
                {'_', '_', '_', '_', '_', '_', '_', '_', },
                {'_', '_', '_', '_', '_', '_', '_', '_', },
                {'_', '_', '_', 'W', 'B', '_', '_', '_', },
                {'_', '_', '_', 'B', 'W', '_', '_', '_', },
                {'_', '_', '_', '_', '_', '_', '_', '_', },
                {'_', '_', '_', '_', '_', '_', '_', '_', },
                {'_', '_', '_', '_', '_', '_', '_', '_', }, };

    }

    public void displayBoard(Game b) {


        for (int i = 0; i < 8; i++) {
            System.out.print(boardX[i] + " ");
        }
        System.out.println();

        for (int i = 0; i < 8; i++) {
            System.out.print(boardX[i] + " ");
            for (int j = 0; j < 8; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }


    }

    //There are three cases black win = -1, white win = 1, draw = 0

    public int gameResult(Point[] whitePlaceableLocations, Point[] blackPlaceableLocations) {

        boolean check = true;
        int result = 0;
        int trace = 0;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == '*') {
                    trace++;
                }
            }
        }

        if (trace > 0) {
            result = 2;
            check = false;
        }

        if (check) {
            updateScores();
            if (bScore > wScore) {
                result = -1;
            } else if (bScore == wScore) {
                result = 0;
            } else {
                result = 1;
            }
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == '*') {
                    board[i][j] = '_';
                }
            }
        }

        return result;
    }

    public Point[] getPlaceableLocations(char player, char opponent) {

        for (int i = 0; i < 64; i++) {
            placeablePositions[i] = new Point(-1, -1);
        }

        int count = 0;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == player) {

                    if (i == 0 && j == 0) {
                        if (board[i][j + 1] == opponent) {
                            for (int k = 1; j + k <= 7; k++) {
                                if (board[i][j + k] == player) {
                                    break;
                                } else if (board[i][j + k] == '_') {
                                    placeablePositions[count] = new Point(i, j + k);
                                    count++;
                                    break;
                                }
                            }
                        }

                        if (board[i + 1][j + 1] == opponent) {
                            for (int k = 1; j + k <= 7 && i + k <= 7; k++) {
                                if (board[i + k][j + k] == player) {
                                    break;
                                } else if (board[i + k][j + k] == '_') {
                                    placeablePositions[count] = new Point(i + k, j + k);
                                    count++;
                                    break;
                                }
                            }
                        }

                        if (board[i + 1][j] == opponent) {
                            for (int k = 1; i + k <= 7; k++) {
                                if (board[i + k][j] == player) {
                                    break;
                                } else if (board[i + k][j] == '_') {
                                    placeablePositions[count] = new Point(i + k, j);
                                    count++;
                                    break;
                                }
                            }
                        }
                    } else if (i == 0 && j > 0 && j < 7) {
                        if (board[i][j + 1] == opponent) {
                            for (int k = 1; j + k <= 7; k++) {
                                if (board[i][j + k] == player) {
                                    break;
                                } else if (board[i][j + k] == '_') {
                                    placeablePositions[count] = new Point(i, j + k);
                                    count++;
                                    break;
                                }
                            }
                        }

                        if (board[i + 1][j + 1] == opponent) {
                            for (int k = 1; j + k <= 7 && i + k <= 7; k++) {
                                if (board[i + k][j + k] == player) {
                                    break;
                                } else if (board[i + k][j + k] == '_') {
                                    placeablePositions[count] = new Point(i + k, j + k);
                                    count++;
                                    break;
                                }
                            }
                        }

                        if (board[i + 1][j] == opponent) {
                            for (int k = 1; i + k <= 7; k++) {
                                if (board[i + k][j] == player) {
                                    break;
                                } else if (board[i + k][j] == '_') {
                                    placeablePositions[count] = new Point(i + k, j);
                                    count++;
                                    break;
                                }
                            }
                        }

                        if (board[i + 1][j - 1] == opponent) {
                            for (int k = 1; i + k <= 7 && k <= j; k++) {
                                if (board[i + k][j - k] == player) {
                                    break;
                                } else if (board[i + k][j - k] == '_') {
                                    placeablePositions[count] = new Point(i + k, j - k);
                                    count++;
                                    break;
                                }
                            }
                        }

                        if (board[i][j - 1] == opponent) {
                            for (int k = 1; k <= j; k++) {
                                if (board[i][j - k] == player) {
                                    break;
                                } else if (board[i][j - k] == '_') {
                                    placeablePositions[count] = new Point(i, j - k);
                                    count++;
                                    break;
                                }
                            }
                        }
                    } else if (i == 0 && j == 7) {
                        if (board[i + 1][j] == opponent) {
                            for (int k = 1; i + k <= 7; k++) {
                                if (board[i + k][j] == player) {
                                    break;
                                } else if (board[i + k][j] == '_') {
                                    placeablePositions[count] = new Point(i + k, j);
                                    count++;
                                    break;
                                }
                            }
                        }

                        if (board[i + 1][j - 1] == opponent) {
                            for (int k = 1; i + k <= 7 && k <= j; k++) {
                                if (board[i + k][j - k] == player) {
                                    break;
                                } else if (board[i + k][j - k] == '_') {
                                    placeablePositions[count] = new Point(i + k, j - k);
                                    count++;
                                    break;
                                }
                            }
                        }

                        if (board[i][j - 1] == opponent) {
                            for (int k = 1; k <= j; k++) {
                                if (board[i][j - k] == player) {
                                    break;
                                } else if (board[i][j - k] == '_') {
                                    placeablePositions[count] = new Point(i, j - k);
                                    count++;
                                    break;
                                }
                            }
                        }
                    } else if (i > 0 && i < 7 && j == 7) {
                        if (board[i + 1][j] == opponent) {
                            for (int k = 1; i + k <= 7; k++) {
                                if (board[i + k][j] == player) {
                                    break;
                                } else if (board[i + k][j] == '_') {
                                    placeablePositions[count] = new Point(i + k, j);
                                    count++;
                                    break;
                                }
                            }
                        }

                        if (board[i + 1][j - 1] == opponent) {
                            for (int k = 1; i + k <= 7 && k <= j; k++) {
                                if (board[i + k][j - k] == player) {
                                    break;
                                } else if (board[i + k][j - k] == '_') {
                                    placeablePositions[count] = new Point(i + k, j - k);
                                    count++;
                                    break;
                                }
                            }
                        }

                        if (board[i][j - 1] == opponent) {
                            for (int k = 1; k <= j; k++) {
                                if (board[i][j - k] == player) {
                                    break;
                                } else if (board[i][j - k] == '_') {
                                    placeablePositions[count] = new Point(i, j - k);
                                    count++;
                                    break;
                                }
                            }
                        }

                        if (board[i - 1][j - 1] == opponent) {
                            for (int k = 1; k <= i && k <= j; k++) {
                                if (board[i - k][j - k] == player) {
                                    break;
                                } else if (board[i - k][j - k] == '_') {
                                    placeablePositions[count] = new Point(i - k, j - k);
                                    count++;
                                    break;
                                }
                            }
                        }

                        if (board[i - 1][j] == opponent) {
                            for (int k = 1; k <= i; k++) {
                                if (board[i - k][j] == player) {
                                    break;
                                } else if (board[i - k][j] == '_') {
                                    placeablePositions[count] = new Point(i - k, j);
                                    count++;
                                    break;
                                }
                            }
                        }
                    } else if (i == 7 && j == 7) {
                        if (board[i][j - 1] == opponent) {
                            for (int k = 1; k <= j; k++) {
                                if (board[i][j - k] == player) {
                                    break;
                                } else if (board[i][j - k] == '_') {
                                    placeablePositions[count] = new Point(i, j - k);
                                    count++;
                                    break;
                                }
                            }
                        }

                        if (board[i - 1][j - 1] == opponent) {
                            for (int k = 1; k <= i && k <= j; k++) {
                                if (board[i - k][j - k] == player) {
                                    break;
                                } else if (board[i - k][j - k] == '_') {
                                    placeablePositions[count] = new Point(i - k, j - k);
                                    count++;
                                    break;
                                }
                            }
                        }

                        if (board[i - 1][j] == opponent) {
                            for (int k = 1; k <= i; k++) {
                                if (board[i - k][j] == player) {
                                    break;
                                } else if (board[i - k][j] == '_') {
                                    placeablePositions[count] = new Point(i - k, j);
                                    count++;
                                    break;
                                }
                            }
                        }
                    } else if (i == 7 && j < 7 && j > 0) {
                        if (board[i][j - 1] == opponent) {
                            for (int k = 1; k <= j; k++) {
                                if (board[i][j - k] == player) {
                                    break;
                                } else if (board[i][j - k] == '_') {
                                    placeablePositions[count] = new Point(i, j - k);
                                    count++;
                                    break;
                                }
                            }
                        }

                        if (board[i - 1][j - 1] == opponent) {
                            for (int k = 1; k <= i && k <= j; k++) {
                                if (board[i - k][j - k] == player) {
                                    break;
                                } else if (board[i - k][j - k] == '_') {
                                    placeablePositions[count] = new Point(i - k, j - k);
                                    count++;
                                    break;
                                }
                            }
                        }

                        if (board[i - 1][j] == opponent) {
                            for (int k = 1; k <= i; k++) {
                                if (board[i - k][j] == player) {
                                    break;
                                } else if (board[i - k][j] == '_') {
                                    placeablePositions[count] = new Point(i - k, j);
                                    count++;
                                    break;
                                }
                            }
                        }

                        if (board[i - 1][j + 1] == opponent) {
                            for (int k = 1; k <= i && j + k <= 7; k++) {
                                if (board[i - k][j + k] == player) {
                                    break;
                                } else if (board[i - k][j + k] == '_') {
                                    placeablePositions[count] = new Point(i - k, j + k);
                                    count++;
                                    break;
                                }
                            }
                        }

                        if (board[i][j + 1] == opponent) {
                            for (int k = 1; j + k <= 7; k++) {
                                if (board[i][j + k] == player) {
                                    break;
                                } else if (board[i][j + k] == '_') {
                                    placeablePositions[count] = new Point(i, j + k);
                                    count++;
                                    break;
                                }
                            }
                        }
                    } else if (i == 7 && j == 0) {
                        if (board[i - 1][j] == opponent) {
                            for (int k = 1; k <= i; k++) {
                                if (board[i - k][j] == player) {
                                    break;
                                } else if (board[i - k][j] == '_') {
                                    placeablePositions[count] = new Point(i - k, j);
                                    count++;
                                    break;
                                }
                            }
                        }

                        if (board[i - 1][j + 1] == opponent) {
                            for (int k = 1; k <= i && j + k <= 7; k++) {
                                if (board[i - k][j + k] == player) {
                                    break;
                                } else if (board[i - k][j + k] == '_') {
                                    placeablePositions[count] = new Point(i - k, j + k);
                                    count++;
                                    break;
                                }
                            }
                        }

                        if (board[i][j + 1] == opponent) {
                            for (int k = 1; j + k <= 7; k++) {
                                if (board[i][j + k] == player) {
                                    break;
                                } else if (board[i][j + k] == '_') {
                                    placeablePositions[count] = new Point(i, j + k);
                                    count++;
                                    break;
                                }
                            }
                        }
                    } else if (i > 0 && i < 7 && j == 0) {
                        if (board[i - 1][j] == opponent) {
                            for (int k = 1; k <= i; k++) {
                                if (board[i - k][j] == player) {
                                    break;
                                } else if (board[i - k][j] == '_') {
                                    placeablePositions[count] = new Point(i - k, j);
                                    count++;
                                    break;
                                }
                            }
                        }

                        if (board[i - 1][j + 1] == opponent) {
                            for (int k = 1; k <= i && j + k <= 7; k++) {
                                if (board[i - k][j + k] == player) {
                                    break;
                                } else if (board[i - k][j + k] == '_') {
                                    placeablePositions[count] = new Point(i - k, j + k);
                                    count++;
                                    break;
                                }
                            }
                        }

                        if (board[i][j + 1] == opponent) {
                            for (int k = 1; j + k <= 7; k++) {
                                if (board[i][j + k] == player) {
                                    break;
                                } else if (board[i][j + k] == '_') {
                                    placeablePositions[count] = new Point(i, j + k);
                                    count++;
                                    break;
                                }
                            }
                        }

                        if (board[i + 1][j + 1] == opponent) {
                            for (int k = 1; j + k <= 7 && i + k <= 7; k++) {
                                if (board[i + k][j + k] == player) {
                                    break;
                                } else if (board[i + k][j + k] == '_') {
                                    placeablePositions[count] = new Point(i + k, j + k);
                                    count++;
                                    break;
                                }
                            }
                        }

                        if (board[i + 1][j] == opponent) {
                            for (int k = 1; i + k <= 7; k++) {
                                if (board[i + k][j] == player) {
                                    break;
                                } else if (board[i + k][j] == '_') {
                                    placeablePositions[count] = new Point(i + k, j);
                                    count++;
                                    break;
                                }
                            }
                        }
                    } else {

                        if (board[i - 1][j - 1] == opponent) {
                            for (int k = 1; k <= i && k <= j; k++) {
                                if (board[i - k][j - k] == player) {
                                    break;
                                } else if (board[i - k][j - k] == '_') {
                                    placeablePositions[count] = new Point(i - k, j - k);
                                    count++;
                                    break;
                                }
                            }
                        }

                        if (board[i - 1][j] == opponent) {
                            for (int k = 1; k <= i; k++) {
                                if (board[i - k][j] == player) {
                                    break;
                                } else if (board[i - k][j] == '_') {
                                    placeablePositions[count] = new Point(i - k, j);
                                    count++;
                                    break;
                                }
                            }
                        }

                        if (board[i - 1][j + 1] == opponent) {
                            for (int k = 1; k <= i && j + k <= 7; k++) {
                                if (board[i - k][j + k] == player) {
                                    break;
                                } else if (board[i - k][j + k] == '_') {
                                    placeablePositions[count] = new Point(i - k, j + k);
                                    count++;
                                    break;
                                }
                            }
                        }

                        if (board[i][j + 1] == opponent) {
                            for (int k = 1; j + k <= 7; k++) {
                                if (board[i][j + k] == player) {
                                    break;
                                } else if (board[i][j + k] == '_') {
                                    placeablePositions[count] = new Point(i, j + k);
                                    count++;
                                    break;
                                }
                            }
                        }

                        if (board[i + 1][j + 1] == opponent) {
                            for (int k = 1; j + k <= 7 && i + k <= 7; k++) {
                                if (board[i + k][j + k] == player) {
                                    break;
                                } else if (board[i + k][j + k] == '_') {
                                    placeablePositions[count] = new Point(i + k, j + k);
                                    count++;
                                    break;
                                }
                            }
                        }

                        if (board[i + 1][j] == opponent) {
                            for (int k = 1; i + k <= 7; k++) {
                                if (board[i + k][j] == player) {
                                    break;
                                } else if (board[i + k][j] == '_') {
                                    placeablePositions[count] = new Point(i + k, j);
                                    count++;
                                    break;
                                }
                            }
                        }

                        if (board[i + 1][j - 1] == opponent) {
                            for (int k = 1; i + k <= 7 && k <= j; k++) {
                                if (board[i + k][j - k] == player) {
                                    break;
                                } else if (board[i + k][j - k] == '_') {
                                    placeablePositions[count] = new Point(i + k, j - k);
                                    count++;
                                    break;
                                }
                            }
                        }

                        if (board[i][j - 1] == opponent) {
                            for (int k = 1; k <= j; k++) {
                                if (board[i][j - k] == player) {
                                    break;
                                } else if (board[i][j - k] == '_') {
                                    placeablePositions[count] = new Point(i, j - k);
                                    count++;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }

        return placeablePositions;
    }

    public void showPlaceableLocations(Point[] locations, char player, char opponent) {

        for (int i = 0; i < 64; i++) {
            if (placeablePositions[i].x != -1 && placeablePositions[i].y != -1) {
                board[placeablePositions[i].x][placeablePositions[i].y] = '*';
            }
        }

    }

    public void placeMove(Point p, char player, char opponent) {

        int i = p.x;
        int j = p.y;

        board[i][j] = player;

        if (i == 0 && j == 0) {
            east(p, player, opponent);

            southEast(p, player, opponent);

            south(p, player, opponent);

        } else if (i == 0 && j > 0 && j < 7) {
            east(p, player, opponent);

            southEast(p, player, opponent);

            south(p, player, opponent);

            southWest(p, player, opponent);

            west(p, player, opponent);

        } else if (i == 0 && j == 7) {
            south(p, player, opponent);

            southWest(p, player, opponent);

            west(p, player, opponent);

        } else if (i > 0 && i < 7 && j == 7) {
            south(p, player, opponent);

            southWest(p, player, opponent);

            west(p, player, opponent);

            northWest(p, player, opponent);

            north(p, player, opponent);

        } else if (i == 7 && j == 7) {
            west(p, player, opponent);

            northWest(p, player, opponent);

            north(p, player, opponent);

        } else if (i == 7 && j < 7 && j > 0) {
            west(p, player, opponent);

            northWest(p, player, opponent);

            north(p, player, opponent);

            northEast(p, player, opponent);

            east(p, player, opponent);

        } else if (i == 7 && j == 0) {
            north(p, player, opponent);

            northEast(p, player, opponent);

            east(p, player, opponent);

        } else if (i > 0 && i < 7 && j == 0) {
            north(p, player, opponent);

            northEast(p, player, opponent);

            east(p, player, opponent);

            southEast(p, player, opponent);

            south(p, player, opponent);

        } else {

            northWest(p, player, opponent);

            north(p, player, opponent);

            northEast(p, player, opponent);

            east(p, player, opponent);

            southEast(p, player, opponent);

            south(p, player, opponent);

            southWest(p, player, opponent);

            west(p, player, opponent);
        }

    }

    public void updateScores() {

        int countB = 0;
        int countW = 0;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == 'B') {
                    countB++;
                    bScore = countB;
                } else if (board[i][j] == 'W') {
                    countW++;
                    wScore = countW;
                }
            }
        }
    }

    public void northWest(Point p, char player, char opponent) {

        int i = p.x;
        int j = p.y;

        if (board[i - 1][j - 1] == opponent) {
            int k = 1;
            int count = 1;
            boolean check = false;
            while (k <= i && k <= j) {
                if (board[i - k][j - k] == player) {
                    check = true;
                    break;
                } else if (board[i - k][j - k] == opponent) {
                    count++;
                } else {
                    break;
                }
                k++;
            }
            if (check) {
                for (int n = 0; n <= count; n++) {
                    board[i - n][j - n] = player;
                }
            }
        }
    }

    public void north(Point p, char player, char opponent) {

        int i = p.x;
        int j = p.y;

        if (board[i - 1][j] == opponent) {
            int k = 1;
            int count = 1;
            boolean check = false;
            while (k <= i) {
                if (board[i - k][j] == player) {
                    check = true;
                    break;
                } else if (board[i - k][j] == opponent) {
                    count++;
                } else {
                    break;
                }
                k++;
            }
            if (check) {
                for (int n = 0; n <= count; n++) {
                    board[i - n][j] = player;
                }
            }
        }
    }

    public void northEast(Point p, char player, char opponent) {

        int i = p.x;
        int j = p.y;

        if (board[i - 1][j + 1] == opponent) {
            int k = 1;
            int count = 1;
            boolean check = false;
            while (k <= i && j + k <= 7) {
                if (board[i - k][j + k] == player) {
                    check = true;
                    break;
                } else if (board[i - k][j + k] == opponent) {
                    count++;
                } else {
                    break;
                }
                k++;
            }
            if (check) {
                for (int n = 0; n <= count; n++) {
                    board[i - n][j + n] = player;
                }
            }
        }
    }

    public void east(Point p, char player, char opponent) {

        int i = p.x;
        int j = p.y;

        if (board[i][j + 1] == opponent) {
            int k = 1;
            int count = 1;
            boolean check = false;
            while (j + k <= 7) {
                if (board[i][j + k] == player) {
                    check = true;
                    break;
                } else if (board[i][j + k] == opponent) {
                    count++;
                } else {
                    break;
                }
                k++;
            }
            if (check) {
                for (int n = 0; n <= count; n++) {
                    board[i][j + n] = player;
                }
            }
        }
    }

    public void southEast(Point p, char player, char opponent) {

        int i = p.x;
        int j = p.y;

        if (board[i + 1][j + 1] == opponent) {
            int k = 1;
            int count = 1;
            boolean check = false;
            while (i + k <= 7 && j + k <= 7) {
                if (board[i + k][j + k] == player) {
                    check = true;
                    break;
                } else if (board[i + k][j + k] == opponent) {
                    count++;
                } else {
                    break;
                }
                k++;
            }
            if (check) {
                for (int n = 0; n <= count; n++) {
                    board[i + n][j + n] = player;
                }
            }
        }
    }

    public void south(Point p, char player, char opponent) {

        int i = p.x;
        int j = p.y;

        if (board[i + 1][j] == opponent) {
            int k = 1;
            int count = 1;
            boolean check = false;
            while (i + k <= 7) {
                if (board[i + k][j] == player) {
                    check = true;
                    break;
                } else if (board[i + k][j] == opponent) {
                    count++;
                } else {
                    break;
                }
                k++;
            }
            if (check) {
                for (int n = 0; n <= count; n++) {
                    board[i + n][j] = player;
                }
            }
        }
    }

    public void southWest(Point p, char player, char opponent) {

        int i = p.x;
        int j = p.y;

        if (board[i + 1][j - 1] == opponent) {
            int k = 1;
            int count = 1;
            boolean check = false;
            while (i + k <= 7 && k <= j) {
                if (board[i + k][j - k] == player) {
                    check = true;
                    break;
                } else if (board[i + k][j - k] == opponent) {
                    count++;
                } else {
                    break;
                }
                k++;
            }
            if (check) {
                for (int n = 0; n <= count; n++) {
                    board[i + n][j - n] = player;
                }
            }
        }
    }

    public void west(Point p, char player, char opponent) {

        int i = p.x;
        int j = p.y;

        if (board[i][j - 1] == opponent) {
            int k = 1;
            int count = 1;
            boolean check = false;
            while (k <= j) {
                if (board[i][j - k] == player) {
                    check = true;
                    break;
                } else if (board[i][j - k] == opponent) {
                    count++;
                } else {
                    break;
                }
                k++;
            }
            if (check) {
                for (int n = 0; n <= count; n++) {
                    board[i][j - n] = player;
                }
            }
        }
    }

    public int skip() {

        int skip = 1;

        for (int i = 0; i < 64; i++) {
            temp[i] = new Point(-1, -1);
        }

        for (int i = 0; i < 64; i++) {
            temp[i].x = placeablePositions[i].x;
            temp[i].y = placeablePositions[i].y;
        }

        for (int i = 0; i < 64; i++) {
            placeablePositions[i] = new Point(-1, -1);
        }

        for (int i = 0; i < 64; i++) {
            if (temp[i].x != -1 &&
                temp[i].y != -1) {
                break;
            } else {
                skip = 0;
            }
        }

        return skip;
    }

    public Point[] empty() {
        int track = 0;
        for (int i = 0; i < 64; i++) {
            empty[i] = new Point(-1, -1);
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] != 'B' ||
                    board[i][j] != 'W') {
                    empty[track] = new Point(0, 0);
                    track++;
                }
            }
        }
        return empty;
    }
}
