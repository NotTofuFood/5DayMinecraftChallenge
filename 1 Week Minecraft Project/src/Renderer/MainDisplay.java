package Renderer;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

public class MainDisplay {
	private static final int WIDTH = 1200;
	private static final int HEIGHT = 800;
	private static final int FPS = 60;
	
	public static void createDisplay() {
		ContextAttribs attribs = new ContextAttribs(3,2).withForwardCompatible(true).withProfileCompatibility(true);
		try {
		Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
		Display.create(new PixelFormat(), attribs);
		Display.setTitle("Minecraft");
		} catch(LWJGLException e) {
			e.printStackTrace();
		}
		GL11.glViewport(0, 0, WIDTH, HEIGHT);
	}
	
	public static void updateDisplay() {
		Display.sync(FPS);
		Display.update();
	}
	
	public static void closeDisplay() {
		Display.destroy();
	}

	public static int getFrameTimeSeconds() {
		int i = (int) System.nanoTime();
		return i;
	}
}
