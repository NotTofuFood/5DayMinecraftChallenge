package Cubes;

import org.lwjgl.util.vector.Vector3f;

public class Block {
	public int x,y,z;
	public static int GRASS = 0;
	public static int STONE = 1;
	public static int TREELOG = 2; 
	public static int TREELEAVES = 3; 
	public static int WATER = 5; 
	public static int SAND = 4; 
	public int type;
	Vector3f pos;
	public Block(int x, int y, int z, int type) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.type = type; 
	}
	public Vector3f getPositions() {
		pos = new Vector3f(x,y,z);
		return pos;
	}
	public static void getPos() {
		Block b = new Block(0,0,0,1);
		 b.getPositions();
	}
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

}
