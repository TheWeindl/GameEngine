package engineTester;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Entity;
import models.RawModel;
import renderEngine.DisplayManger;
import renderEngine.Loader;
import renderEngine.OBJLoader;
import renderEngine.Renderer;
import shaders.StaticShader;
import textures.ModelTexture;

public class MainGameLoop {
	 
    public static void main(String[] args) {
 
        DisplayManger.createDisplay();
        Loader loader = new Loader();
        StaticShader shader = new StaticShader();
        Renderer renderer = new Renderer(shader);
         
        
        //Vertices, indices and textures replaced by the OBJ loader
        RawModel model = OBJLoader.loadObjModel("dragon", loader);
         
        //TexturedModel staticModel = new TexturedModel(model,new ModelTexture(loader.loadTexture("image")));			//Textures for the demo cube
        TexturedModel staticModel = new TexturedModel(model,new ModelTexture(loader.loadTexture("stallTexture")));
 
        Entity entity = new Entity(staticModel, new Vector3f(0,-7,-30),0,0,0,1);
         
        Camera camera = new Camera();
         
        while(!Display.isCloseRequested()){
            entity.increaseRotation(0, 1, 0);
            camera.move();
            renderer.prepare();
            shader.start();
            shader.loadViewMatrix(camera);
            renderer.render(entity,shader);
            shader.stop();
            DisplayManger.updateDisplay();
        }
 
        shader.cleanUp();
        loader.cleanUP();
        DisplayManger.closeDisplay();
 
    }
 
}
