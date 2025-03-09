package com.engine3d.test;

import com.engine3d.core.EngineManager;
import com.engine3d.core.WindowManager;
import com.engine3d.core.utils.Consts;
import org.lwjgl.Version;
import org.lwjgl.glfw.GLFW;

public class Launcher {

    private static WindowManager window;
    private static TestGame game;

    public static void main(String[] args){
        GLFW.glfwInit();
        String version = GLFW.glfwGetVersionString();
        System.out.println("GLFW Version" + version);
        GLFW.glfwTerminate();
        System.out.println(Version.getVersion());
        window = new WindowManager(Consts.TITLE,0, 0, false);
        game = new TestGame();
        EngineManager engine = new EngineManager();
        try {
            engine.start();
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public static WindowManager getWindow() {
        return window;
    }

    public static TestGame getGame() {
        return game;
    }
}
