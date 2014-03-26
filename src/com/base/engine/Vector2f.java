package com.base.engine;

/**
 * Created by dylan on 2014-03-23.
 */
public class Vector2f {
    private float x;
    private float y;

    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float length() {
        return (float) Math.sqrt(x * x + y * y);
    }

    public float dot(Vector2f r) {
        return x * r.getX() + y * r.getY();
    }

    public Vector2f normalize() {
        float length = length();

        x /= length;
        y /= length;

        return this;
    }

    public Vector2f rotate(float angle) {

        double radians = Math.toRadians(angle);
        double cosine = Math.cos(radians);
        double sine = Math.sin(radians);


        return new Vector2f((float) (x * cosine - y * sine), (float) (x * sine + y * cosine));
    }

    public Vector2f add(Vector2f vectorIn) {
        return new Vector2f(x + vectorIn.getX(), y + vectorIn.getY());
    }

    public Vector2f add(float inAmount) {
        return new Vector2f(x + inAmount, y + inAmount);
    }

    public Vector2f subtract(Vector2f vectorIn) {
        return new Vector2f(x - vectorIn.getX(), y - vectorIn.getY());
    }

    public Vector2f subtract(float inAmount) {
        return new Vector2f(x - inAmount, y - inAmount);
    }

    public Vector2f multiply(Vector2f vectorIn) {
        return new Vector2f(x * vectorIn.getX(), y * vectorIn.getY());
    }

    public Vector2f multiply(float inAmount) {
        return new Vector2f(x * inAmount, y * inAmount);
    }

    public Vector2f divide(Vector2f vectorIn) {
        return new Vector2f(x / vectorIn.getX(), y / vectorIn.getY());
    }

    public Vector2f divide(float inAmount) {
        return new Vector2f(x / inAmount, y / inAmount);
    }


    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Vector2f{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
