package com.engine3d.core;

import com.engine3d.test.Launcher;
import org.lwjgl.egl.EGL10;
import org.lwjgl.opengl.GL11;

public class RenderManager {

    private final WindowManager window;

    public RenderManager() {
        window = Launcher.getWindow();
    }

    public  void init() throws Exception {

    }

    public void render() {

    }

    public void clear() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
    }

    public void cleanup() {

    }
}
