package com.base.engine;

import org.lwjgl.input.Keyboard;

/**
 * Created by dylan on 2014-03-23.
 */
public class Game {

    private Mesh mesh;
    private Shader shader;
    private Transform transform;

    public Game() {
        mesh = ResouceLoader.loadMesh("cube.obj");
        shader = new Shader();

//        Vertex[] vertices = new Vertex[]{
//                new Vertex(new Vector3f(-1, -1, 0)),
//                new Vertex(new Vector3f(0, 1, 0)),
//                new Vertex(new Vector3f(1, -1, 0)),
//                new Vertex(new Vector3f(0,-1,1))
//        };
//
//        int[] indices = new int[] {
//                0,1,3,
//                3,1,2,
//                2,1,0,
//                0,2,3
//        };
//
//        mesh.addVertices(vertices, indices);

        transform = new Transform();

        shader.addVertexShader(ResouceLoader.loadShader("basicVertex.glsl"));
        shader.addFragmentShader(ResouceLoader.loadShader("basicFragment.glsl"));
        shader.compileShader();

        shader.addUniform("transform");
    }

    public void input() {
        if (Input.getKeyDown(Keyboard.KEY_UP)) {
            System.out.println("We've just pressed UP!");
        }
        if (Input.getKeyUp(Keyboard.KEY_UP)) {
            System.out.println("We've just release UP!");
        }

        if (Input.getMouseDown(1)) {
            System.out.println("RIGHT CLICK DOWN! @" + Input.getMousePosition());
        }

        if (Input.getMouseUp(1)) {
            System.out.println("RIGHT CLICK UP!");
        }
    }

    float temp = 0.0f;

    public void update() {
        temp += Time.getDelta();
        float sinTemp = (float)Math.sin(temp);

       // transform.setTranslation(sinTemp, 0,0);
        transform.setRotation(0, sinTemp * 180 , 0);
        transform.setScale(0.7f * sinTemp, 0.7f * sinTemp, 0.7f * sinTemp);
    }

    public void render() {
        shader.bind();
        shader.setUniform("transform", transform.getTransformation());
        mesh.draw();
    }
}
