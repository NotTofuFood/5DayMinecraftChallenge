package Chunks;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

import Cubes.Block;
import Cubes.Blocks;
import Cubes.Vertex;

public class ChunkMesh {
	private List<Vertex> verticies;
	private List<Float> positionList;
	private List<Float> uvsList;
	public List<Float> normalsList;
	
	public float[] positions,uvs,normals;
	
	public Chunk chunk;
	
	public ChunkMesh(Chunk chunk) {
		this.chunk = chunk;
		
		verticies = new ArrayList<Vertex>();
		
		positionList = new ArrayList<Float>();
		
		uvsList = new ArrayList<Float>();
		
		normalsList = new ArrayList<Float>();
		
		buildMesh();
		
		populateList();

	}
	
	public void update(Chunk chunk) {
		this.chunk = chunk;
		
		buildMesh();
		
		populateList();
	}
	
	private void buildMesh() {
		//Build Detection
		for(int i = 0; i < chunk.blocks.size(); i++) {
			Block blockI = chunk.blocks.get(i);
			boolean px = false, nx = false, py = false, ny = false, pz = false, nz = false; 
			
			for(int j = 0; j < chunk.blocks.size(); j++) {
				Block blockJ = chunk.blocks.get(j);
				
				if(((blockI.x + 1) == (blockJ.x)) && ((blockI.y) == (blockJ.y) && (blockI.z) == (blockJ.z))) {
					px= true;
				}
				if(((blockI.x - 1) == (blockJ.x)) && ((blockI.y) == (blockJ.y) && (blockI.z) == (blockJ.z))) {
					nx= true;
				}
				if(((blockI.x) == (blockJ.x)) && ((blockI.y + 1) == (blockJ.y) && (blockI.z) == (blockJ.z))) {
					py= true;
				}
				if(((blockI.x) == (blockJ.x)) && ((blockI.y - 1) == (blockJ.y) && (blockI.z) == (blockJ.z))) {
					ny= true;
				}
				if(((blockI.x) == (blockJ.x)) && ((blockI.y) == (blockJ.y) && (blockI.z + 1) == (blockJ.z))) {
					pz= true;
				}
				if(((blockI.x) == (blockJ.x)) && ((blockI.y) == (blockJ.y) && (blockI.z - 1) == (blockJ.z))) {
					nz= true;
				}
			}
			if(!px) {
				for(int k = 0; k < 6; k++) {
					verticies.add(new Vertex(new Vector3f(Blocks.PX_POS[k].x + blockI.x, Blocks.PX_POS[k].y + blockI.y, Blocks.PX_POS[k].z + blockI.z),Blocks.UV_PX[(blockI.type * 6) + k],Blocks.NORMALS[k]));
				}
			}
			if(!nx) {
				for(int k = 0; k < 6; k++) {
					verticies.add(new Vertex(new Vector3f(Blocks.NX_POS[k].x + blockI.x, Blocks.NX_POS[k].y + blockI.y, Blocks.NX_POS[k].z + blockI.z),Blocks.UV_NX[(blockI.type * 6) + k],Blocks.NORMALS[k]));
				}
			}
			if(!py) {
				for(int k = 0; k < 6; k++) {
					verticies.add(new Vertex(new Vector3f(Blocks.PY_POS[k].x + blockI.x, Blocks.PY_POS[k].y + blockI.y, Blocks.PY_POS[k].z + blockI.z),Blocks.UV_PY[(blockI.type * 6) + k],Blocks.NORMALS[k]));
				}
			}
			if(!ny) {
				for(int k = 0; k < 6; k++) {
					verticies.add(new Vertex(new Vector3f(Blocks.NY_POS[k].x + blockI.x, Blocks.NY_POS[k].y + blockI.y, Blocks.NY_POS[k].z + blockI.z),Blocks.UV_NY[(blockI.type * 6) + k],Blocks.NORMALS[k]));
				}
			}
			if(!pz) {
				for(int k = 0; k < 6; k++) {
					verticies.add(new Vertex(new Vector3f(Blocks.PZ_POS[k].x + blockI.x, Blocks.PZ_POS[k].y + blockI.y, Blocks.PZ_POS[k].z + blockI.z),Blocks.UV_PZ[(blockI.type * 6) + k],Blocks.NORMALS[k]));
				}
			}
			if(!nz) {
				for(int k = 0; k < 6; k++) {
					verticies.add(new Vertex(new Vector3f(Blocks.NZ_POS[k].x + blockI.x, Blocks.NZ_POS[k].y + blockI.y, Blocks.NZ_POS[k].z + blockI.z),Blocks.UV_NZ[(blockI.type * 6) + k],Blocks.NORMALS[k]));
				}
			}
		}
	}
	
	private void populateList() {
		for(int i = 0; i < verticies.size(); i++) {
			positionList.add(verticies.get(i).positions.x);
			positionList.add(verticies.get(i).positions.y);
			positionList.add(verticies.get(i).positions.z);
			uvsList.add(verticies.get(i).uvs.x);
			uvsList.add(verticies.get(i).uvs.y);
			normalsList.add(verticies.get(i).normals.x);
			normalsList.add(verticies.get(i).normals.y);
			normalsList.add(verticies.get(i).normals.z);
			
		}
		
		positions = new float[positionList.size()];
		uvs = new float[uvsList.size()];
		normals = new float[normalsList.size()];
		
		for(int i = 0; i < positionList.size(); i++) {
			positions[i] = positionList.get(i);
		}
		for(int i = 0; i < uvsList.size(); i++) {
			uvs[i] = uvsList.get(i);
		}
		for(int i = 0; i < normalsList.size(); i++) {
			normals[i] = normalsList.get(i);
		}
		cleanup();
	}
	public void cleanup() {
		normalsList.clear();
		uvsList.clear();
		positionList.clear();
		verticies.clear();
	}

}
