/**
 * CS18000 - Fall 2018
 *
 * Project 2 - Reversi
 *
 * Abstraction for a point on a two-dimensional coordinate space
 *
 * @author Aarya Barve, abarve@purdue.edu
 *
 * @version October 12th 2018
 *
 */
public class Point {

    public int x;
    public int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setPoint(int a, int b) {
        this.x = a;
        this.y = b;
    }

}
