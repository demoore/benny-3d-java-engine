package com.base.engine;

/**
 * Created by dylan on 2014-03-23.
 */
public class Quaternion {
    private float x;
    private float y;
    private float z;
    private float w;

    public Quaternion(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public float length() {
        return (float) Math.sqrt(x * x + y * y + z * z + w * w);
    }

    public Quaternion normalize() {
        float length = length();

        x /= length;
        y /= length;
        z /= length;
        w /= length;

        return this;
    }

    public Quaternion conjugate() {
        return new Quaternion(-x, -y, -z, w);
    }

    public Quaternion multiply(Quaternion inQuaternion) {

        float w_ = w * inQuaternion.getW() - x * inQuaternion.getX() - y * inQuaternion.getY() - z * inQuaternion.getZ();
        float x_ = x * inQuaternion.getW() + w * inQuaternion.getX() + y * inQuaternion.getZ() - z * inQuaternion.getY();
        float y_ = y * inQuaternion.getW() + w * inQuaternion.getY() + z * inQuaternion.getX() - x * inQuaternion.getZ();
        float z_ = z * inQuaternion.getW() + w * inQuaternion.getZ() + x * inQuaternion.getY() - y * inQuaternion.getX();

        return new Quaternion(x_, y_, z_, w_);
    }

    public Quaternion multiply(Vector3f inVector3f) {

        float w_ = -x * inVector3f.getX() - y * inVector3f.getY() - z * inVector3f.getZ();
        float x_ = w * inVector3f.getX() + y * inVector3f.getZ() - z * inVector3f.getY();
        float y_ = w * inVector3f.getY() + z * inVector3f.getX() - x * inVector3f.getZ();
        float z_ = w * inVector3f.getZ() + x * inVector3f.getY() - y * inVector3f.getX();
        return new Quaternion(x_, y_, z_, w_);
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

    public float getW() {
        return w;
    }

    public void setW(float w) {
        this.w = w;
    }
}
