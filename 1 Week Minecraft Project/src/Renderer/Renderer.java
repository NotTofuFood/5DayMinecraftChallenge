package Renderer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;

import Entity.Camera;
import Entity.Entity;
import Models.RawModel;
import Models.TexturedModel;
import Shaders.StaticShader;
import SkyBoxRenderer.SkyboxRenderer;
import Textures.ModelTexture;
import Maths.Maths;

public class Renderer {
	private static final float FOV = 70;
	private static final float NEAR_PLANE = 0.1f;
	private static final float FAR_PLANE = 1000;
	private Matrix4f projectionMatrix;
	private Matrix4f SkyprojectionMatrix;
	EntityRenderer renderer = new EntityRenderer();
	StaticShader shader = new StaticShader();
	Map<TexturedModel,List<Entity>> entities = new HashMap<TexturedModel,List<Entity>>();
	private SkyboxRenderer  skybox;
	private float time = 8000;
	Loader loader = new Loader();
	public Renderer() {
		createProjectionMatrix();
		
		shader.start();
		
		//skybox = new SkyboxRenderer(loader, projectionMatrix);
		shader.loadProjectionMatrix(projectionMatrix);
		shader.stop();
	}
	
	public void setBackground() {
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		time += 10;
		time %= 24000;
		
		if(time >= 0 && time < 5000){
			//NightTime
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);
			GL11.glClearColor(0,0,0,1);
		}else if(time >= 5000 && time < 7000){
			//NightTime
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);
			GL11.glClearColor(0,0,0,1);
		}else if(time >= 7000 && time < 21000){
			//MidTime
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);
			GL11.glClearColor(0.57f, 0.152f, 1.146f,  1);
			
		} else if(time >= 8000 && time < 21000){
			//DayTime
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);
			GL11.glClearColor(0.4f, 0.6f, 1.0f,  1);
		} else{
			//DayTime
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);
			GL11.glClearColor(0.4f, 0.6f, 1.0f,  1);
		} 
	}
	
	public void render(Camera camera) {
		setBackground();
		shader.start();
		
		shader.loadViewMatrix(camera);
		
		//skybox.render(camera);
		renderer.render(entities);
	
		shader.stop();
		
		entities.clear();

	}
	public Matrix4f getProjectionMatrix() {
		return projectionMatrix;
	}
	public void addBlocks(Entity entity) {
		TexturedModel model = entity.getModel();
		List<Entity>  group = entities.get(model);
		if(group != null) {
			group.add(entity);
			//group.remove(entity);
			
		} else {
			List<Entity> newGroup = new ArrayList<Entity>();
			newGroup.add(entity);
			entities.put(model, newGroup);
		}
	}

	public void doDaylightCycle() {
		time += 10;
		time %= 24000;
		
		if(time >= 0 && time < 5000){
			//NightTime
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);
			GL11.glClearColor(0,0,0,1);
		}else if(time >= 5000 && time < 8000){
			//NightTime
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);
			GL11.glClearColor(0,0,0,1);
		}else if(time >= 8000 && time < 21000){
			//DayTime
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);
			GL11.glClearColor(0.4f, 0.6f, 1.0f,  1);
		}else{
			//DayTime
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);
			GL11.glClearColor(0.4f, 0.6f, 1.0f,  1);
		}

	}
	private void createProjectionMatrix() {
		float aspectRatio = (float) Display.getWidth() / (float) Display.getHeight();
		float y_scale = (float) ((1f / Math.tan(Math.toRadians(FOV / 2f))) * aspectRatio);
		
		float x_scale = y_scale / aspectRatio;
		float frustumLength = FAR_PLANE - NEAR_PLANE;
		projectionMatrix = new Matrix4f();
		projectionMatrix.m00 = x_scale;
		projectionMatrix.m11 = y_scale;
		projectionMatrix.m22 = -((FAR_PLANE + NEAR_PLANE) / frustumLength);
		projectionMatrix.m23 = -1;
		projectionMatrix.m32 = -((2*NEAR_PLANE * FAR_PLANE) / frustumLength);
		projectionMatrix.m33 = 0;
	}
}
