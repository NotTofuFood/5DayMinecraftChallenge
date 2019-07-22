package SkyBoxRenderer;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import Entity.Camera;
import Maths.Maths;
import Renderer.MainDisplay;
import Shaders.ShaderProgram;

public class SkyboxShader extends ShaderProgram{
	 
    private static final String VERTEX_FILE = "src/skyboxrenderer/skyboxVertexShader.txt";
    private static final String FRAGMENT_FILE = "src/skyboxrenderer/skyboxFragmentShader.txt";
    private static float skySpeed = 1f; 
    private int location_projectionMatrix;
    private int location_viewMatrix;
    private int location_cubeMap;
    private int location_cubeMap2;
    private int location_blendFactor;
    private float rotation = 0;
    public SkyboxShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }
     
    public void loadProjectionMatrix(Matrix4f matrix){
        super.loadMatrix(location_projectionMatrix, matrix);
    }
    public void connectTextureUnits() {
    	super.loadInt(location_cubeMap, 0);
    	super.loadInt(location_cubeMap2, 1);
    }
    public void loadBlend(float blend) { 
    	super.loadFloat(location_blendFactor, blend);
    }
    public void loadViewMatrix(Camera camera){
        Matrix4f matrix = Maths.createViewMatrix(camera);
        matrix.m30 = 0;
        matrix.m31 = 0;
        matrix.m32 = 0;
        rotation += skySpeed * 0.007 * 2;
        Matrix4f.rotate((float) Math.toRadians(rotation), new Vector3f(0,1,0), matrix, matrix);
        super.loadMatrix(location_viewMatrix, matrix);
    }
     
    @Override
    protected void getAllUniformLocations() {
        location_projectionMatrix = super.getUniformLocation("projectionMatrix");
        location_viewMatrix = super.getUniformLocation("viewMatrix");
        location_blendFactor = super.getUniformLocation("blendFactor");
        location_cubeMap = super.getUniformLocation("cubeMap");
        location_cubeMap2 = super.getUniformLocation("cubeMap2");
    }
 
    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
    }
}
