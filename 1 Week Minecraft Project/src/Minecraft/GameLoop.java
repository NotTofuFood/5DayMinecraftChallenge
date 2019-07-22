package Minecraft;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import Chunks.Chunk;
import Chunks.ChunkMesh;
import Cubes.Block;
import Cubes.Blocks;

import Entity.Camera;
import Entity.Entity;
import Maths.NoiseMap;
import Maths.RemoveBlockRayCasting;
import Models.RawModel;
import Models.TexturedModel;
import Renderer.Loader;
import Renderer.MainDisplay;
import Renderer.Renderer;
import Shaders.StaticShader;
import Textures.ModelTexture;

public class GameLoop {
	public static List<ChunkMesh> chunks = Collections.synchronizedList(new ArrayList<ChunkMesh>());

	public static Vector3f cameraPos = new Vector3f(0,0,0);
	public static List<Vector3f> getBlockPos = new ArrayList<Vector3f>();
	public static List<Entity> entities = new ArrayList<Entity>();
	static final int WORLD = 5 * 32; 
	static int oldX;
	static int oldY;
	static int oldZ;

	static int x = 0; 
	public static void main(String[] args) {
		MainDisplay.createDisplay();
		Loader loader = new Loader();
		Loader loader2 = new Loader();
		StaticShader shader = new StaticShader();
		
		Renderer renderer = new Renderer();
		
		
		RawModel model = loader.loadToVao(Blocks.vertices, Blocks.indices, Blocks.uv);
	
		ModelTexture texture = new ModelTexture(loader.loadTexture("WeekCraftTexturePack"));

		TexturedModel texturedModel = new TexturedModel(model, texture);
		

		//TexturedModel texturedChicken = new TexturedModel(chickenModel, chicken);
		Camera camera = new Camera(new Vector3f(0,30,0),0,0,0);
		
		NoiseMap gen = new NoiseMap();
		new Thread(new Runnable() {
			public void run() { 
				while(!Display.isCloseRequested()) {
					for(int i = (int)(cameraPos.x - WORLD) / 80; i < (cameraPos.x + WORLD) / 80; i++) {
						for(int z = (int)(cameraPos.z - WORLD) / 80; z < (cameraPos.z + WORLD) / 80; z++) {
							if(!getBlockPos.contains(new Vector3f(i * 80,0 * 80,z * 80))) {
									
								List<Block> blocks = new ArrayList<Block>();
								List<Block> treeLog = new ArrayList<Block>();
								List<Block> treeLeaf = new ArrayList<Block>();
								List<Block> water = new ArrayList<Block>();
								for(int x = 0; x < 80; x++) {
									for(int j = 0; j < 80; j++) {
									
											
											//blocks.add(new Block(x, (int)gen.generateHeight(x + (i * 80), j + (z*80)),j, Block.GRASS));	
											if(gen.getHeight() > 10) {
												blocks.add(new Block(x, (int)gen.generateHeight(x + (i * 80), j + (z*80)),j, Block.GRASS));	

											} else if(gen.getHeight() == -17) {
												blocks.add(new Block(x,(int)gen.generateHeight(x + (i * 80), j + (z*80)),j,Block.SAND));	
												
											}	else if(gen.getHeight() <=-18) {
												blocks.add(new Block(x,(int)gen.generateHeight(x + (i * 80), j + (z*80)),j,Block.WATER));	
												
												
											}
		
											if(gen.getHeight() > 10) {
											

											}else  {
												blocks.add(new Block(x, (int)gen.generateHeight(x + (i * 80), j + (z*80)),j, Block.GRASS));	
											} 
									
									}
								}
								//Water
						
								//Trees
							
								if(gen.getHeight() == 10) {
								int treeX = 10;
								int treeZ = 10;
								
								int treeY = 10;
								treeLog.add(new Block(treeX, treeY ,treeZ, Block.TREELOG));	
								treeLog.add(new Block(treeX, treeY-1 ,treeZ, Block.TREELOG));	
								treeLog.add(new Block(treeX, treeY-2 ,treeZ, Block.TREELOG));	
								treeLog.add(new Block(treeX, treeY-3 ,treeZ, Block.TREELOG));	
								treeLog.add(new Block(treeX, treeY-4 ,treeZ, Block.TREELOG));	
								treeLog.add(new Block(treeX, treeY-5 ,treeZ, Block.TREELOG));
								treeLog.add(new Block(treeX, treeY - 6 ,treeZ, Block.TREELOG));	
								treeLog.add(new Block(treeX, treeY-7 ,treeZ, Block.TREELOG));	
								treeLog.add(new Block(treeX, treeY-8 ,treeZ, Block.TREELOG));	
								treeLog.add(new Block(treeX, treeY + 1 ,treeZ, Block.TREELOG));	
								treeLog.add(new Block(treeX, treeY + 2 ,treeZ, Block.TREELOG));	
								treeLog.add(new Block(treeX, treeY +3 ,treeZ, Block.TREELOG));	
								treeLog.add(new Block(treeX, treeY + 4 ,treeZ, Block.TREELOG));	
								treeLeaf.add(new Block(treeX, treeY + 5, treeZ, Block.TREELEAVES));
								treeLeaf.add(new Block(treeX+1, treeY + 5 , treeZ, Block.TREELEAVES));
								treeLeaf.add(new Block(treeX+2,  treeY + 5, treeZ, Block.TREELEAVES));
								treeLeaf.add(new Block(treeX, treeY + 5, treeZ+1, Block.TREELEAVES));
								treeLeaf.add(new Block(treeX, treeY + 5, treeZ+2, Block.TREELEAVES));
								
								treeLeaf.add(new Block(treeX-1, treeY + 5, treeZ, Block.TREELEAVES));
								treeLeaf.add(new Block(treeX-2, treeY + 5, treeZ, Block.TREELEAVES));
								treeLeaf.add(new Block(treeX, treeY + 5, treeZ-1, Block.TREELEAVES));
								treeLeaf.add(new Block(treeX, treeY + 5, treeZ-2, Block.TREELEAVES));
								
								
								treeLeaf.add(new Block(treeX, treeY + 6, treeZ, Block.TREELEAVES));
								treeLeaf.add(new Block(treeX, treeY + 7, treeZ, Block.TREELEAVES));
								
								
								
								}
								
							
								//
								Chunk chunk = new Chunk(blocks, new Vector3f(i*80,0,z*80));
								ChunkMesh mesh = new ChunkMesh(chunk);
									chunks.add(mesh);
									getBlockPos.add(new Vector3f(i * 80,0 * 80,z * 80));
									Chunk chunk2 = new Chunk(treeLog, new Vector3f(i*80,0,z*80));
									ChunkMesh mesh2 = new ChunkMesh(chunk2);
										chunks.add(mesh2);
										getBlockPos.add(new Vector3f(i * 80,0 * 80,z * 80));
										Chunk chunk3 = new Chunk(treeLeaf, new Vector3f(i*80,0,z*80));
										ChunkMesh mesh3 = new ChunkMesh(chunk3);
											chunks.add(mesh3);
											getBlockPos.add(new Vector3f(i * 80,0 * 80,z * 80));
										
							}
						}
						
						
					}
				}
			}
		}).start();

		RemoveBlockRayCasting picker = new RemoveBlockRayCasting(camera, renderer.getProjectionMatrix());
		int index = 0;
		while(!Display.isCloseRequested()) {
	
			List<Block> blocks2 = new ArrayList<Block>();

			camera.move();
			picker.update();
			System.out.println(picker.getCurrentRay().x);
			cameraPos = camera.getPosition();

		

				System.out.println("TEST");
				ChunkMesh chunkmesh;
	
	

			
			
	

				System.out.println("TEST");
		
				List<Block> blocks = new ArrayList<Block>();
		
				if(Mouse.isButtonDown(0)) {
				blocks.add(new Block((int)picker.getCurrentRay().x, (int)picker.getCurrentRay().y - 5, (int)picker.getCurrentRay().z, Block.STONE));
				}
				Chunk blockChunk = new Chunk(blocks, new Vector3f(camera.getPosition()));
				chunkmesh = new ChunkMesh(blockChunk);
				if(Mouse.isButtonDown(0)) {
					chunks.add(chunkmesh);
				}
			/*	getBlockPos.add(new Vector3f(camera.getPosition()));
				Entity entity = new Entity(texturedModel, picker.getCurrentRay(),0,0,0, 1);
				entities.add(entity);
				*/
				if(Mouse.isButtonDown(1)) {
					
					removeBlock(chunkmesh);
				}
		
		
			if(index < chunks.size()) {
				RawModel blockModel = loader.loadToVao(chunks.get(index).positions,chunks.get(index).uvs);
			TexturedModel blocktexture = new TexturedModel(blockModel, texture);
				Entity entity = new Entity(blocktexture, chunks.get(index).chunk.center,0,0,0,1);
				entities.add(entity);
				
				chunks.get(index).positions = null;
				chunks.get(index).normals = null;
				chunks.get(index).uvs = null;
				index++;
			}
			
			
			for(int i = 0; i < entities.size(); i++) {
				Vector3f center = entities.get(i).getPosition();
					int dx = (int)(cameraPos.x - center.x);
					int dz = (int)(cameraPos.z - center.z); 
					if(dx < 0) {
						dx =-dx;
					}
					if(dz < 0) {
						dz =-dz;
					}
					if((dx <= WORLD) && (dz <= WORLD)) {
						renderer.addBlocks(entities.get(i));
						}
					}
			
				
		//	renderer.addBlocks(entity);
			renderer.render(camera);
			MainDisplay.updateDisplay();
			
			
		}
		MainDisplay.closeDisplay();
	}
	
	public static  int getOldX() {
		Camera camera = new Camera(new Vector3f(0,30,0),0,0,0);
	
		oldX = (int)(camera.getPosition().x);
		
		return oldX;
	}
	public static  int getOldY() {
		Camera camera = new Camera(new Vector3f(0,30,0),0,0,0);
		
		oldY = (int)(camera.getPosition().y);
		
		return oldY;
	}
	public static  int getOldZ() {
		Camera camera = new Camera(new Vector3f(0,30,0),0,0,0);
		
		oldZ = (int)(camera.getPosition().z);
		
		return oldZ;
	}
	public static void removeBlock(ChunkMesh mesh) {
		chunks.remove(mesh);

	}

}
