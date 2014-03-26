package com.base.engine;

import org.lwjgl.input.Keyboard;

/**
 * Created by dylan on 2014-03-23.
 */
public class Game {

    private Mesh mesh;
    private Shader shader;
    private Transform transform;
    private Texture texture;
    private Camera camera;

    public Game() {
        mesh = ResouceLoader.loadMesh("suzanne.obj");
//        mesh = new Mesh();
        shader = new Shader();
        texture = ResouceLoader.loadTexture("test.png");
        camera = new Camera();

//        Vertex[] vertices = new Vertex[]{
//                new Vertex(new Vector3f(-1, -1, 0), new Vector2f(0,0)),
//                new Vertex(new Vector3f(0, 1, 0), new Vector2f(0.5f,0)),
//                new Vertex(new Vector3f(1, -1, 0), new Vector2f(1.0f,0)),
//                new Vertex(new Vector3f(0,-1,1), new Vector2f(0,0.5f))
//        };
//
//        int[] indices = new int[] {
//                3,1,0,
//                2,1,3,
//                0,1,2,
//                0,2,3
//        };
//
//        mesh.addVertices(vertices, indices);

        transform = new Transform();
        transform.setProjection(70f, MainComponent.WIDTH, MainComponent.HEIGHT, 0.1f, 1000);
        transform.setCamera(camera);

        shader.addVertexShader(ResouceLoader.loadShader("basicVertex.glsl"));
        shader.addFragmentShader(ResouceLoader.loadShader("basicFragment.glsl"));
        shader.compileShader();

        shader.addUniform("transform");
    }

    public void input() {
        camera.input();

//        if (Input.getKeyDown(Keyboard.KEY_UP)) {
//            System.out.println("We've just pressed UP!");
//        }
//        if (Input.getKeyUp(Keyboard.KEY_UP)) {
//            System.out.println("We've just release UP!");
//        }

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

        transform.setTranslation(sinTemp, 0,5);
        transform.setRotation(0, sinTemp * 180 , 0);
        //transform.setScale(0.7f * sinTemp, 0.7f * sinTemp, 0.7f * sinTemp);
    }

    public void render() {
        shader.bind();
        texture.bind();
        shader.setUniform("transform", transform.getProjectedTransformation());
        mesh.draw();
    }
}
