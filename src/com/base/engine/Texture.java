package com.base.engine;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by Dylan on 2014-03-25.
 */
public class Texture {

    private int id;

    public Texture(int id){
        this.id = id;
    }

    public void bind(){
        glBindTexture(GL_TEXTURE_2D, id);
    }

    public int getID(){
        return id;
    }
}
