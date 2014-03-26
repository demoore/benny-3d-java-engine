package com.base.engine;

/**
 * Created by dylan on 2014-03-23.
 */
public class Vertex {

    public static final int SIZE = 5;

    private Vector3f position;
    private Vector2f textureCoordinates;


    public Vertex(Vector3f inPosition){
        this(inPosition, new Vector2f(0,0));    }

    public Vertex(Vector3f inPosition, Vector2f inTextureCoordinates) {
        this.position = inPosition;
        this.textureCoordinates = inTextureCoordinates;
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public Vector2f getTextureCoordinates() {
        return textureCoordinates;
    }

    public void setTextureCoordinates(Vector2f textureCoordinates) {
        this.textureCoordinates = textureCoordinates;
    }
}
