package engineTester;

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
import renderEngine.Renderer;
import shaders.StaticShader;
import textures.ModelTexture;

public class MainGameLoop {
	 
    public static void main(String[] args) {
 
        DisplayManger.createDisplay();
        Loader loader = new Loader();
            
        //Vertices, indices and textures replaced by the OBJ loader
        RawModel model = OBJLoader.loadObjModel("dragon", loader);
         
        //TexturedModel staticModel = new TexturedModel(model,new ModelTexture(loader.loadTexture("image")));			//Textures for the demo cube
        TexturedModel staticModel = new TexturedModel(model,new ModelTexture(loader.loadTexture("white")));
        ModelTexture texture = staticModel.getTexture();
        texture.setShineDamper(10);
        texture.setReflectivity(1);
       
        Entity dragon = new Entity(staticModel, new Vector3f(0,-7,-30),0,0,0,1);
        
        Light light = new Light(new Vector3f(-10,0,-20),new Vector3f(1,1,1));
         
        Camera camera = new Camera();
         
        MasterRenderer renderer = new MasterRenderer();
        
        while(!Display.isCloseRequested()){
        	
        	//////////////////////////////////////////////////
        	//				MOVEMENT						//
        	//////////////////////////////////////////////////
            dragon.increaseRotation(0, 1, 0);
            camera.move();
            
            
            //////////////////////////////////////////////////
            //				ENTITY QUEQUING					//
            //////////////////////////////////////////////////
            renderer.processEntity(dragon);
            
            
            //////////////////////////////////////////////////
            //				RENDERING						//
            //////////////////////////////////////////////////
            renderer.render(light, camera);
            DisplayManger.updateDisplay();
        }
 
        renderer.cleanUp();
        loader.cleanUP();
        DisplayManger.closeDisplay();
 
    }
 
}
