package com.base.engine;

/**
 * Created by dylan on 2014-03-23.
 */
public class Vertex {

    public static final int SIZE = 3;

    private Vector3f position;


    public Vertex(Vector3f inPosition){
        position = inPosition;
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }
}
