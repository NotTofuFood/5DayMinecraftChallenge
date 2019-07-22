package Chunks;

import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import Cubes.Block;
import Entity.Entity;

public class Chunk {
	public List<Block> blocks;
	public Vector3f center;
	
	
	public Chunk(List<Block> blocks, Vector3f center) { 
		this.blocks = blocks;
		this.center = center;
	}



}
