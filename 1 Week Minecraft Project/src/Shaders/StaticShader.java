package Shaders;

import org.lwjgl.util.vector.Matrix4f;

import Entity.Camera;
import Maths.Maths;

public class StaticShader extends ShaderProgram {

	private static final String VertexFile = "src/Shaders/vertexShader.txt";
	private static final String FragmentFile = "src/Shaders/fragmentShader.txt";
	private int location_transformationMatrix;
	private int location_projectionMatrix;
	private int location_viewMatrix;
	
	public StaticShader() {
		super(VertexFile, FragmentFile);
		
	}

	@Override
	protected void getAllUniformLocations() {
		location_projectionMatrix = super.getUniformLocation("projectionMatrix");
		location_viewMatrix = super.getUniformLocation("viewMatrix");
		location_transformationMatrix = super.getUniformLocation("transformationMatrix");
	}
	
	public void loadViewMatrix(Camera camera) {
		Matrix4f viewMatrix = Maths.createViewMatrix(camera);
		super.loadMatrix(location_viewMatrix, viewMatrix);
	}
	
	public void loadProjectionMatrix(Matrix4f projection) {
		super.loadMatrix(location_projectionMatrix, projection);
	}
	
	public void loadTransformationMatrix(Matrix4f transformation) {
	
		super.loadMatrix(location_transformationMatrix, transformation);
	}
	@Override
	protected void bindAttributes() {
		super.bindAttribute(0,"position");
		super.bindAttribute(1, "textureCoordinates");
	}
	
	

}
