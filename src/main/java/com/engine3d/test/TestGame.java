package com.engine3d.test;

import com.engine3d.core.*;
import com.engine3d.core.entity.Entity;
import com.engine3d.core.entity.Model;
import com.engine3d.core.entity.Texture;
import com.engine3d.core.utils.Consts;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

public class TestGame implements ILogic {

    private static final float CAMERA_MOVE_SPEED = 0.1f;

    private int direction = 0;
    private float color = 0.0f;

    private final RenderManager renderer;
    private final ObjectLoader loader;
    private final WindowManager window;

    private Entity entity;
    private Camera camera;
    private GameLogic movement;

    Vector3f cameraInc;


    public TestGame() {
        renderer = new RenderManager();
        window = Launcher.getWindow();
        loader = new ObjectLoader();
        camera = new Camera();
        cameraInc = new Vector3f(0, 0, 0);
    }

    @Override
    public void init() throws Exception {
        renderer.init();

//        float[] vertices = {
//                -0.5f,  0.5f, 0f,
//                -0.5f, -0.5f, 0f,
//                0.5f, -0.5f, 0f,
//                0.5f,  0.5f, 0f,
//        };
//
//        int[] indices = {
//                0,1,3,
//                3,1,2
//        };
//
//        float[] textureCoords = {
//                0,0,
//                0,1,
//                1,1,
//                1,0
//        };


        Model model = loader.loadOBJ("/models/bunny.obj");
        model.setTexture(new Texture(loader.loadTexture("textures/dirt.png")));
        entity = new Entity(model, new Vector3f(0, 0, -5), new Vector3f(0,0,0), 10);

        //        movement = new GameLogic(entity);
    }

    @Override
    public void input() {
        cameraInc.set(0, 0, 0);
        if(window.isKeyPressed(GLFW.GLFW_KEY_W))
            cameraInc.z = -1;
        if(window.isKeyPressed(GLFW.GLFW_KEY_S))
            cameraInc.z = 1;

        if(window.isKeyPressed(GLFW.GLFW_KEY_A))
            cameraInc.x = -1;
        if(window.isKeyPressed(GLFW.GLFW_KEY_D))
            cameraInc.x = 1;

        if(window.isKeyPressed(GLFW.GLFW_KEY_LEFT_SHIFT))
            cameraInc.y = -1;
        if(window.isKeyPressed(GLFW.GLFW_KEY_SPACE))
            cameraInc.y = 1;
    //        if(window.isKeyPressed(GLFW.GLFW_KEY_LEFT))
    //            direction = 1;
    //        else if(window.isKeyPressed(GLFW.GLFW_KEY_RIGHT))
    //            direction = -1;
    //        else if (window.isKeyPressed(GLFW.GLFW_KEY_UP))
    //            direction = 2;
    //        else if (window.isKeyPressed(GLFW.GLFW_KEY_DOWN))
    //            direction = -2;
    //        else
    //            direction = 0;
    }

    @Override
    public void update(float interval, MouseInput mouseInput) {
        camera.movePosition(cameraInc.x * CAMERA_MOVE_SPEED, cameraInc.y * CAMERA_MOVE_SPEED, cameraInc.z * CAMERA_MOVE_SPEED);

        if(mouseInput.isRightButtonPress()) {
            Vector2f rotVec = mouseInput.getDisplayVector();
            camera.moveRotation(rotVec.x * Consts.MOUSE_SENSITIVITY, rotVec.y * Consts.MOUSE_SENSITIVITY, 0);
        }

        entity.incRotation(0.0f, 0.0f,0.0f);
//        color += direction * 0.01f;
//        if(color > 1)
//            color = 1.0f;
//        else if (color <= 0)
//            color = 0.0f;
//
//        if(entity.getPos().x < -1.5f)
//            entity.getPos().x = 1.5f;
//        entity.getPos().x -=0.01f;
        //        movement.move(direction);
        //        movement.screenWrap();
    }

    @Override
    public void render() {
        if(window.isResize()) {
            GL11.glViewport(0, 0, window.getWidth(), window.getHeight());
            window.setResize(false);
        }

        window .setClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        renderer.render(entity, camera);
    }

    @Override
    public void cleanup() {
        renderer.cleanup();
        loader.cleanup();
    }
}
