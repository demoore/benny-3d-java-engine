package com.base.engine;

/**
 * Created by dylan on 2014-03-23.
 */
public class Vector3f {
    private float x;
    private float y;
    private float z;

    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float length() {
        return (float) Math.sqrt(x * x + y * y + z * z);
    }

    public float dot(Vector3f r) {
        return x * r.getX() + y * r.getY() + z * r.getZ();
    }

    public Vector3f cross(Vector3f inVector) {
        float x_ = y * inVector.getZ() - z * inVector.getY();
        float y_ = z * inVector.getX() - x * inVector.getZ();
        float z_ = x * inVector.getY() - y * inVector.getX();

        return new Vector3f(x_, y_, z_);
    }

    public Vector3f normalized() {
        float length = length();

        return new Vector3f(x / length, y / length, z / length);
    }

    public Vector3f add(Vector3f vectorIn) {
        return new Vector3f(x + vectorIn.getX(), y + vectorIn.getY(), z + vectorIn.getZ());
    }

    public Vector3f add(float inAmount) {
        return new Vector3f(x + inAmount, y + inAmount, z + inAmount);
    }

    public Vector3f subtract(Vector3f vectorIn) {
        return new Vector3f(x - vectorIn.getX(), y - vectorIn.getY(), z - vectorIn.getZ());
    }

    public Vector3f subtract(float inAmount) {
        return new Vector3f(x - inAmount, y - inAmount, z - inAmount);
    }

    public Vector3f multiply(Vector3f vectorIn) {
        return new Vector3f(x * vectorIn.getX(), y * vectorIn.getY(), z * vectorIn.getZ());
    }

    public Vector3f multiply(float inAmount) {
        return new Vector3f(x * inAmount, y * inAmount, z * inAmount);
    }

    public Vector3f divide(Vector3f vectorIn) {
        return new Vector3f(x / vectorIn.getX(), y / vectorIn.getY(), z / vectorIn.getZ());
    }

    public Vector3f divide(float inAmount) {
        return new Vector3f(x / inAmount, y / inAmount, z / inAmount);
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

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public Vector3f rotate(float angle, Vector3f axis) {
        float sinHalfAngle = (float) Math.sin(Math.toRadians(angle / 2));
        float cosHalfAngle = (float) Math.cos(Math.toRadians(angle / 2));

        float rX = axis.getX() * sinHalfAngle; //rotationAxis
        float rY = axis.getY() * sinHalfAngle;
        float rZ = axis.getZ() * sinHalfAngle;
        float rW = cosHalfAngle;

        Quaternion rotation = new Quaternion(rX, rY, rZ, rW);
        Quaternion conjugate = rotation.conjugate();

        Quaternion w = rotation.multiply(this).multiply(conjugate);

        return new Vector3f(w.getX(), w.getY(), w.getZ());
    }

    public Vector3f abs() {
        return new Vector3f(Math.abs(x), Math.abs(y), Math.abs(z));
    }
}
