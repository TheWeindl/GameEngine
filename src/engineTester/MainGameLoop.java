package engineTester;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Entity;
import entities.Light;
import models.RawModel;
import renderEngine.DisplayManger;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import renderEngine.OBJLoader;
import terrains.Terrain;
import textures.ModelTexture;

public class MainGameLoop {
	 
    public static void main(String[] args) {
 

        DisplayManger.createDisplay();
        Loader loader = new Loader();
         
         
        RawModel model = OBJLoader.loadObjModel("tree", loader);
        RawModel normalTree = OBJLoader.loadObjModel("NormalTreeTex", loader);
         
        TexturedModel staticModel = new TexturedModel(model,new ModelTexture(loader.loadTexture("tree")));
        TexturedModel staticTree = new TexturedModel(normalTree,new ModelTexture(loader.loadTexture("blue")));
         
        List<Entity> entities = new ArrayList<Entity>();
        Random random = new Random();
        for(int i=0;i<500;i++){
            entities.add(new Entity(staticModel, new Vector3f(random.nextFloat()*800 - 400,0,random.nextFloat() * -600),0,0,0,3));
            entities.add(new Entity(staticTree, new Vector3f(random.nextFloat()*800 - 400,0,random.nextFloat() * -600),0,0,0,1));
        }
         
        Light light = new Light(new Vector3f(1000,20000,1000),new Vector3f(1,1,1));
         
        Terrain terrain = new Terrain(-1,-1,loader,new ModelTexture(loader.loadTexture("grass")));
        Terrain terrain2 = new Terrain(0,-1,loader,new ModelTexture(loader.loadTexture("grass")));
         
        Camera camera = new Camera(0.5f); 			//Sensitivity of mouse movement (Value between 0 and 1)   
        MasterRenderer renderer = new MasterRenderer();
         
        while(!Display.isCloseRequested()){
            camera.move();
             
            renderer.processTerrains(terrain);
            renderer.processTerrains(terrain2);
            for(Entity entity:entities){
                renderer.processEntity(entity);
            }
            renderer.render(light, camera);
            DisplayManger.updateDisplay();
        }
 
        renderer.cleanUp();
        loader.cleanUP();
        DisplayManger.closeDisplay();
 
    }
 
}
