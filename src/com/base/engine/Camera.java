package com.base.engine;

/**
 * Created by Dylan on 2014-03-25.
 */
public class Camera {

    public static final Vector3f yAxis = new Vector3f(0,1,0);

    private Vector3f pos;
    private Vector3f forward;
    private Vector3f up;

    public Camera(){
        this(new Vector3f(0,0,0), new Vector3f(0,0,1), new Vector3f(0,1,0));
    }

    public Camera(Vector3f pos, Vector3f forward, Vector3f up){
        this.pos = pos;
        this.forward = forward;
        this.up = up;

        up.normalized();
        forward.normalized();
    }


    public void move(Vector3f direction, float amount){
        pos = pos.add(direction.multiply(amount));
    }

    public Vector3f getLeft(){
        Vector3f left = forward.cross(up).normalized();

        return  left;
    }

    public Vector3f getRight(){
        Vector3f right = up.cross(forward).normalized();

        return  right;
    }

    public void rotateY(float angle){
        Vector3f hAxis = yAxis.cross(forward).normalized();

        forward = forward.rotate(angle, yAxis).normalized();

        up = forward.cross(hAxis).normalized();
    }

    public void rotateX(float angle){
        Vector3f hAxis = yAxis.cross(forward).normalized();

        forward = forward.rotate(angle, hAxis).normalized();

        up = forward.cross(hAxis).normalized();

    }

    public static Vector3f getyAxis() {
        return yAxis;
    }

    public Vector3f getPos() {
        return pos;
    }

    public void setPos(Vector3f pos) {
        this.pos = pos;
    }

    public Vector3f getForward() {
        return forward;
    }

    public void setForward(Vector3f forward) {
        this.forward = forward;
    }

    public Vector3f getUp() {
        return up;
    }

    public void setUp(Vector3f up) {
        this.up = up;
    }

    boolean mouseLocked = false;
    Vector2f centerPosition = new Vector2f(Window.getWidth()/2, Window.getHeight()/2);

    public void input(){
        float sensitivity = 0.3f;
        float moveAmount = (float)(10 * Time.getDelta());
        float rotationAmount = (float)(100 * Time.getDelta());

        if(Input.getKey(Input.KEY_ESCAPE))
        {
            Input.setCursor(true);
            mouseLocked = false;
        }
        if(Input.getMouseDown(0))
        {
            Input.setMousePosition(centerPosition);
            Input.setCursor(false);
            mouseLocked = true;
        }


        if(Input.getKey(Input.KEY_W)){
            move(getForward(), moveAmount);
        }
        if(Input.getKey(Input.KEY_S)){
            move(getForward(), -moveAmount);
        }
        if(Input.getKey(Input.KEY_A)){
            move(getLeft(), moveAmount);
        }
        if(Input.getKey(Input.KEY_D)){
            move(getRight(), moveAmount);
        }


        if(Input.getKey(Input.KEY_UP)){
            rotateX(-rotationAmount);
        }
        if(Input.getKey(Input.KEY_DOWN)){
            rotateX(rotationAmount);
        }
        if(Input.getKey(Input.KEY_LEFT)){
            rotateY(-rotationAmount);
        }
        if(Input.getKey(Input.KEY_RIGHT)){
            rotateY(rotationAmount);
        }

        if(mouseLocked){
            Vector2f deltaPos = Input.getMousePosition().subtract(centerPosition);

            boolean rotY = deltaPos.getX() != 0;
            boolean rotX = deltaPos.getY() != 0;

            if(rotY){
                rotateY(deltaPos.getX() * sensitivity);
            }

            if(rotX){
                rotateX(-deltaPos.getY() * sensitivity); //Hate inversion so much
            }

            if(rotY || rotX){
                Input.setMousePosition(new Vector2f(Window.getWidth()/2, Window.getHeight()/2));
            }
        }
    }
}
