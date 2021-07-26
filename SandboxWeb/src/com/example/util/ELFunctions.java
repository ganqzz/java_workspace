package com.example.util;

/**
 * Custom math functions implemented for use in JSP.
 */
public class ELFunctions {

    /**
     * Square the input.
     *
     * @param x the value to square
     * @return square of the value
     */
    public static double square(double x) {
        return Math.pow(x, 2.0);
    }

    /**
     * Cube the input.
     *
     * @param x the value to cube.
     * @return cube of the value
     */
    public static double cube(double x) {
        return Math.pow(x, 3.0);
    }

    /**
     * Square root of the value.
     *
     * @param x the value to get the square root
     * @return the square root of the value
     */
    public static double sqrt(double x) {
        return Math.sqrt(x);
    }
    //etc...
}
