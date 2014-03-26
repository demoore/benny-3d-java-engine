package com.base.engine;

import org.lwjgl.input.Keyboard;

/**
 * Created by dylan on 2014-03-23.
 */
public class Game {

    private Mesh mesh;
    private Shader shader;
    private Transform transform;
    private Material material;
    private Camera camera;

    PointLight pointLight1 = new PointLight(new BaseLight(new Vector3f(1,0.5f,0), 0.8f), new Attenuation(0,0,1), new Vector3f(-2,0,5f));
    PointLight pointLight2 = new PointLight(new BaseLight(new Vector3f(0,0.5f,1), 0.8f), new Attenuation(0,0,1), new Vector3f(2,0,7f));
    PointLight pointLight3 = new PointLight(new BaseLight(new Vector3f(0.5f,1,0), 0.8f), new Attenuation(0,0,1), new Vector3f(2,0,7f));
    PointLight pointLight4 = new PointLight(new BaseLight(new Vector3f(0,1,0.5f), 0.8f), new Attenuation(0,0,1), new Vector3f(2,0,7f));

    public Game() {
//        mesh = ResouceLoader.loadMesh("cube.obj");
        mesh = new Mesh();
        shader = new PhongShader().getInstance();
        material = new Material(ResouceLoader.loadTexture("test2.png"), new Vector3f(1,1,1), 1, 8);
        camera = new Camera();
        transform = new Transform();

//        Vertex[] vertices = new Vertex[] { new Vertex( new Vector3f(-1.0f, -1.0f, 0.5773f),	new Vector2f(0.0f, 0.0f)),
//                new Vertex( new Vector3f(0.0f, -1.0f, -1.15475f),	new Vector2f(0.5f, 0.0f)),
//                new Vertex( new Vector3f(1.0f, -1.0f, 0.5773f),	new Vector2f(1.0f, 0.0f)),
//                new Vertex( new Vector3f(0.0f, 1.0f, 0.0f), new Vector2f(0.5f, 1.0f))
//        };
//
//        int[] indices = new int[] {
//                0,3,1,
//                1,3,2,
//                2,3,0,
//                0,1,2
//        };

        float fieldDepth = 10.0f;
        float fieldWidth = 10.0f;

        Vertex[] vertices = new Vertex[] { new Vertex( new Vector3f(-fieldWidth, 0.0f, -fieldDepth), new Vector2f(0.0f, 0.0f)),
                new Vertex( new Vector3f(-fieldWidth, 0.0f, fieldDepth * 3), new Vector2f(0.0f, 1.0f)),
                new Vertex( new Vector3f(fieldWidth * 3, 0.0f, -fieldDepth), new Vector2f(1.0f, 0.0f)),
                new Vertex( new Vector3f(fieldWidth * 3, 0.0f, fieldDepth * 3), new Vector2f(1.0f, 1.0f))};

        int indices[] = { 0, 1, 2,
                2, 1, 3};



        mesh.addVertices(vertices, indices, true);


        transform.setProjection(70f, MainComponent.WIDTH, MainComponent.HEIGHT, 0.1f, 1000);
        transform.setCamera(camera);

        PhongShader.setAmbientLight(new Vector3f(0.1f,0.1f,0.1f));
        //PhongShader.setDirectionalLight( new DirectionalLight(new BaseLight(new Vector3f(1,1,1), 0.8f), new Vector3f(1,1,1)));



        PhongShader.setPointLights(new PointLight[] {pointLight1, pointLight2, pointLight3, pointLight4});
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

        transform.setTranslation(0, -1, 5);
        //transform.setTranslation(sinTemp, 0,5);
        //transform.setRotation(0, sinTemp * 180 , 0);
        //transform.setScale(0.7f * sinTemp, 0.7f * sinTemp, 0.7f * sinTemp);

        pointLight1.setPosition(new Vector3f(3,0,8.0f * (float)(Math.sin(temp) + 1.0/2.0) + 10));
        pointLight2.setPosition(new Vector3f(7,0,8.0f * (float)(Math.cos(temp) + 1.0/2.0) + 10));
        pointLight3.setPosition(new Vector3f(11,0,8.0f * (float)(Math.cos(temp) + 1.0/2.0) + 10));
        pointLight4.setPosition(new Vector3f(15,0,8.0f * (float)(Math.sin(temp) + 1.0/2.0) + 10));
    }

    public void render() {
        RenderUtil.setClearColor(Transform.getCamera().getPos().divide(2048f).abs());
        shader.bind();
        shader.updateUniforms(transform.getTransformation(), transform.getProjectedTransformation(), material);
        mesh.draw();
    }
}
