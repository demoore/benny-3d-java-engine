package com.base.engine;

/**
 * Created by dylan on 2014-03-23.
 */
public class Time {

    public static final long SECOND = 1000000000L;

    private static double delta;

    public static long getTime() {
        return System.nanoTime();
    }

    public static double getDelta() {
        return delta;
    }

    public static void setDelta(double inDelta) {
        delta = inDelta;
    }
}
