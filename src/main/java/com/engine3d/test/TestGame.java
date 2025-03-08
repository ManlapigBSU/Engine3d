package com.engine3d.test;

import com.engine3d.core.ILogic;
import com.engine3d.core.ObjectLoader;
import com.engine3d.core.RenderManager;
import com.engine3d.core.WindowManager;
import com.engine3d.core.entity.Entity;
import com.engine3d.core.entity.Model;
import com.engine3d.core.entity.Texture;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

public class TestGame implements ILogic {

    private int direction = 0;
    private float color = 0.0f;

    private final RenderManager renderer;
    private final ObjectLoader loader;
    private final WindowManager window;

    private Entity entity;
    private GameLogic movement;


    public TestGame() {
        renderer = new RenderManager();
        window = Launcher.getWindow();
        loader = new ObjectLoader();
    }

    @Override
    public void init() throws Exception {
        renderer.init();

//        float[] vertices = {
//                -0.5f, -0.5f,  // v0
//                0.5f, -0.5f,  // v1
//                0.5f,  0.5f,  // v2
//                -0.5f,  0.5f   // v3
//        };
//
//        int[] indices = {
//                0, 1, 2,  // First triangle
//                0, 2, 3   // Second triangle
//        };

        float[] vertices = {
                -0.5f,  0.5f, 0f,
                -0.5f, -0.5f, 0f,
                0.5f, -0.5f, 0f,
                0.5f,  0.5f, 0f,
        };

        int[] indices = {
                0,1,3,
                3,1,2
        };

        float[] textureCoords = {
                0,0,
                0,1,
                1,1,
                1,0
        };

        Model model = loader.loadModel(vertices, textureCoords, indices);
        model.setTexture(new Texture(loader.loadTexture("textures/grassblock.png")));
        entity = new Entity(model, new Vector3f(0, 0, 0), new Vector3f(0,0,0), 1);

        movement = new GameLogic(entity);
    }

    @Override
    public void input() {
        if(window.isKeyPressed(GLFW.GLFW_KEY_LEFT))
            direction = 1;
        else if(window.isKeyPressed(GLFW.GLFW_KEY_RIGHT))
            direction = -1;
        else if (window.isKeyPressed(GLFW.GLFW_KEY_UP))
            direction = 2;
        else if (window.isKeyPressed(GLFW.GLFW_KEY_DOWN))
            direction = -2;
        else
            direction = 0;
    }

    @Override
    public void update() {
//        color += direction * 0.01f;
//        if(color > 1)
//            color = 1.0f;
//        else if (color <= 0)
//            color = 0.0f;
//
//        if(entity.getPos().x < -1.5f)
//            entity.getPos().x = 1.5f;
//        entity.getPos().x -=0.01f;
        movement.move(direction);
        movement.screenWrap();
    }

//    public void screenWrap() {
//
//    }

//    public void movement(int direction) {
//        moveLeft(direction);
//        MoveRight(direction);
//        MoveUp(direction);
//        MoveDown(direction);
//    }
//
//    public void moveLeft(int isPressed) {
//        if(isPressed == 1)
//            entity.getPos().x -= 0.01f;
//    }
//
//    public void moveRight(int isPressed) {
//        if(isPressed == -1)
//            entity.getPos().x += 0.01f;
//    }

    @Override
    public void render() {
        if(window.isResize()) {
            GL11.glViewport(0, 0, window.getWidth(), window.getHeight());
            window.setResize(false);
        }

        window .setClearColor(color, color, color, 0.0f);
        renderer.render(entity);
    }

    @Override
    public void cleanup() {
        renderer.cleanup();
        loader.cleanup();
    }
}
