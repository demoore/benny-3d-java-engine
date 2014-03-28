package com.base.engine;

import org.newdawn.slick.opengl.TextureLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by Dylan on 2014-03-25.
 */
public class Texture {

    private int id;

    public Texture(String fileName){
        this(loadTexture(fileName));
    }

    public Texture(int id){
        this.id = id;
    }

    public void bind(){
        glBindTexture(GL_TEXTURE_2D, id);
    }

    public int getID(){
        return id;
    }

    private static int loadTexture(String fileName){
        String[] splitArray = fileName.split("\\.");
        String extension = splitArray[splitArray.length -1];

        try {
            int id = TextureLoader.getTexture(extension, new FileInputStream(new File("./res/textures/" + fileName))).getTextureID();

            return id;
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        return 0;
    }

}
