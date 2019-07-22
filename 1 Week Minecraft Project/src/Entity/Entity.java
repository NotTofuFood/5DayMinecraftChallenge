package Entity;

import org.lwjgl.util.vector.Vector3f;

import Models.TexturedModel;
import Textures.ModelTexture;

public class Entity {
	private Vector3f position;
	private TexturedModel model;
	private float rotX, rotY, rotZ;
	
	private float scale;
	public Entity(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ,float scale) {
		this.model = model;
		this.position = position;
		this.rotX = rotX;
		this.rotY = rotY;
		this.rotZ = rotZ; 
		this.scale = scale; 
	}
	
	public TexturedModel getModel() {
		return model;
	}

	public void setModel(TexturedModel model) {
		this.model = model;
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

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	public void increasePos(float dx, float dy, float dz) {
		this.position.y += dy;
		this.position.x += dx;
		this.position.z += dz; 
		
	}
	
	public void increaseRotation(float dx, float dy, float dz) {
		this.rotX = dx;
		this.rotY = dy;
		this.rotZ = dz; 
	}
	
	public Vector3f getPos() {
		return position;
	}
	
	
}
