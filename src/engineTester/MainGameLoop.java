package engineTester;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.Entity;
import models.RawModel;
import renderEngine.DisplayManger;
import renderEngine.Loader;
import renderEngine.Renderer;
import shaders.StaticShader;
import textures.ModelTexture;

public class MainGameLoop
{

	public static void main(String[] args)
	{
		DisplayManger.createDisplay();
		
		Loader loader = new Loader();
		StaticShader shader = new StaticShader();
		Renderer renderer = new Renderer(shader);
		
		//Triangles
		float[] vertices = {
				  -0.5f, 0.5f, 0,
				  -0.5f, -0.5f, 0,
				  0.5f, -0.5f, 0,
				  0.5f, 0.5f, 0f
		};
			
		//Indices
		int[] indices = {
				  0,1,3,
				  3,1,2
		};
		
		//UV coordinates
		float[] textureCoords = {
			    0,0,
			    0,1,
			    1,1,
			    1,0
			  };
		
		RawModel model  = loader.loadToVAO(vertices,textureCoords,indices);
		ModelTexture texture = new ModelTexture(loader.loadTexture("image"));
		TexturedModel staticModel = new TexturedModel(model, texture);
		Entity entity = new Entity(staticModel, new Vector3f(0,0,-1), 0,0,0,1);
		
		while(!Display.isCloseRequested()) 
		{
			entity.increasePosition(0, 0, -0.1f);
			
			renderer.prepare();			
			//game logic
			
			//render
			shader.start();
			renderer.render(entity,shader);
			shader.stop();
			
			DisplayManger.updateDisplay();
		}
		
		shader.cleanUp();
		loader.cleanUP();
		DisplayManger.closeDisplay();
	}

}
