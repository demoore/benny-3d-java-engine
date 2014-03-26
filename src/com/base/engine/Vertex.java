package com.base.engine;

/**
 * Created by dylan on 2014-03-23.
 */
public class Vertex {

    public static final int SIZE = 8;

    private Vector3f position;
    private Vector3f normal;
    private Vector2f textureCoordinates;


    public Vertex(Vector3f inPosition){
        this(inPosition, new Vector2f(0,0));    }

    public Vertex(Vector3f inPosition, Vector2f inTextureCoordinates) {
       this(inPosition, inTextureCoordinates, new Vector3f(0,0,0));
    }

    public Vertex(Vector3f position, Vector2f textureCoordinates, Vector3f normal) {
        this.position = position;
        this.normal = normal;
        this.textureCoordinates = textureCoordinates;
    }

    public Vector3f getNormal() {
        return normal;
    }

    public void setNormal(Vector3f normal) {
        this.normal = normal;
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
