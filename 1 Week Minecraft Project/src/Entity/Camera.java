package Entity;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

public class Camera {
	Vector3f position;
	Vector3f oldPos; 
	float rotX;
	float rotY;
	float rotZ;
	float turn = 0.18f;
	float Camspeed = 0.78f;
	
	public Camera(Vector3f position, float rotX, float rotY, float rotZ) {
		this.position = position;
		this.rotX = rotX;
		this.rotY = rotY;
		this.rotZ = rotZ;
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public float getRotX() {
		return rotX;
	}

	public void setRotX(float rotX) {
		this.rotX = rotX;
	}

	public float getRotY() {
		return rotY;
	}

	public void setRotY(float rotY) {
		this.rotY = rotY;
	}

	public float getRotZ() {
		return rotZ;
	}

	public void setRotZ(float rotZ) {
		this.rotZ = rotZ;
	}

	public float getTurn() {
		return turn;
	}

	public void setTurn(float turn) {
		this.turn = turn;
	}

	public float getCamspeed() {
		return Camspeed;
	}

	public void setCamspeed(float camspeed) {
		Camspeed = camspeed;
	}
	public Vector3f getOldPos() {
		return position;
	}
	public void move() { 
		
		if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
			position.z -= Camspeed;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
			position.z += Camspeed;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
			position.x -= Camspeed;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
			position.x += Camspeed;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			position.y += Camspeed;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_RETURN)) {
			position.y -= Camspeed;
		}
		rotX += Mouse.getDY() * turn;
		rotY += Mouse.getDX() * turn;

		//System.out.println(rotX);
		if(Keyboard.isKeyDown(Keyboard.KEY_E)) {
		Mouse.setGrabbed(true);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
		Display.destroy();
		}
	}
}
