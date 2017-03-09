package entities;
 
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;
 
public class Camera {
     
    private Vector3f position = new Vector3f(0,10,0);
    private float pitch;
    private float yaw;
    private float roll;
    private float sensitivity;
     
    public Camera(float sensitivity){
    	this.sensitivity = sensitivity;
    }
     
    public void move(){
        if(Keyboard.isKeyDown(Keyboard.KEY_W)){
            position.z-=0.5f;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_D)){
            position.x+=0.5f;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_A)){
            position.x-=0.5f;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_S)){
        	position.z+=0.5f;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_SUBTRACT)){
        	position.y-=0.5f;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_ADD)){
        	position.y+=0.5f;
        }
        
        //A bit wonky, just for testing and object viewing
        yaw += (Mouse.getDX() / (1/sensitivity));
        
        pitch -= (Mouse.getDY() / (1/sensitivity));
        
    }
 
    public Vector3f getPosition() {
        return position;
    }
 
    public float getPitch() {
        return pitch;
    }
 
    public float getYaw() {
        return yaw;
    }
 
    public float getRoll() {
        return roll;
    }
     
     
 
}
