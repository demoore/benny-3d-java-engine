package com.base.engine;

import org.newdawn.slick.opengl.TextureLoader;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by dylan on 2014-03-23.
 */
public class ResouceLoader {

    public static Texture loadTexture(String fileName){
        String[] splitArray = fileName.split("\\.");
        String extension = splitArray[splitArray.length -1];

        try {
            int id = TextureLoader.getTexture(extension, new FileInputStream(new File("./res/textures/" + fileName))).getTextureID();

            return new Texture(id);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        return null;
    }

    public static String loadShader(String fileName){
        StringBuilder shaderSource = new StringBuilder();
        BufferedReader shaderReader;

        try {
            shaderReader = new BufferedReader(new FileReader("./res/shaders/" + fileName));
            String line;
            while((line = shaderReader.readLine()) != null){
                shaderSource.append(line).append("\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }


        return shaderSource.toString();
    }

    public static  Mesh loadMesh(String fileName){
        String[] splitArray = fileName.split("\\.");
        String extension = splitArray[splitArray.length -1];

        if(!extension.equals("obj")){
            System.err.println("Error: File format not supported for mesh data: " + extension);
            new Exception().printStackTrace();;
            System.exit(1);
        }

        ArrayList<Vertex> vertices = new ArrayList<Vertex>();
        ArrayList<Integer> indices = new ArrayList<Integer>();

        BufferedReader meshReader;

        try {
            meshReader = new BufferedReader(new FileReader("./res/models/" + fileName));
            String line;

            while((line = meshReader.readLine()) != null){
                String[] tokens = line.split(" ");
                tokens = Util.removeEmptyStrings(tokens);

                if(tokens.length == 0 || tokens[0].equals("#")){
                    continue;
                }

                if(tokens[0].equals("v")){ //VERTICES
                    vertices.add(new Vertex((new Vector3f(
                            Float.valueOf(tokens[1]),
                            Float.valueOf(tokens[2]),
                            Float.valueOf(tokens[3])
                    ))));
                }

                if(tokens[0].equals("f")){  //FACES
                    indices.add(Integer.parseInt(tokens[1].split("/")[0]) - 1);
                    indices.add(Integer.parseInt(tokens[2].split("/")[0]) - 1);
                    indices.add(Integer.parseInt(tokens[3].split("/")[0]) - 1);

                    if(tokens.length > 4){
                        indices.add(Integer.parseInt(tokens[1].split("/")[0]) - 1);
                        indices.add(Integer.parseInt(tokens[3].split("/")[0]) - 1);
                        indices.add(Integer.parseInt(tokens[4].split("/")[0]) - 1);
                    }
                }
            }
            meshReader.close();

            Mesh res = new Mesh();

            Vertex[] vertexData = new Vertex[vertices.size()];
            vertices.toArray(vertexData);

            Integer[] indexData = new Integer[indices.size()];
            indices.toArray(indexData);

            res.addVertices(vertexData, Util.toIntArray(indexData));

            return res;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }



        return null;
    }
}
