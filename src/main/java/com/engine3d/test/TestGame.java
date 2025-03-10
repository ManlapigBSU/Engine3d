package com.engine3d.test;

import com.engine3d.core.*;
import com.engine3d.core.entity.Entity;
import com.engine3d.core.entity.Model;
import com.engine3d.core.entity.Texture;
import com.engine3d.core.lighting.DirectionalLight;
import com.engine3d.core.utils.Consts;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.CallbackI;

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

    private float lightAngle;
    private DirectionalLight directionalLight;


    public TestGame() {
        renderer = new RenderManager();
        window = Launcher.getWindow();
        loader = new ObjectLoader();
        camera = new Camera();
        cameraInc = new Vector3f(0, 0, 0);
        lightAngle = -90;
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


        Model model = loader.loadOBJ("/models/cube6.obj");
        model.setTexture(new Texture(loader.loadTexture("textures/grassblock.png")), 1f);
        entity = new Entity(model, new Vector3f(0, 0, -5), new Vector3f(0,0,0), 1);

        float lightIntensity = 0.0f;
        Vector3f lightPosition = new Vector3f(-1, -10, 0);
        Vector3f lightColor = new Vector3f(1, 1, 1);
        directionalLight = new DirectionalLight(lightIntensity, lightPosition, lightColor);//
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

        lightAngle += 1.05f;
        if(lightAngle > 90) {
            directionalLight.setIntensity(0);
            if(lightAngle >= 360) {
                lightAngle = -90;
            } else if (lightAngle <= -80 || lightAngle >= 80) {
                float factor = 1 - (float) (Math.abs(lightAngle) -80 / 10.0f);
                directionalLight.setIntensity(factor);
                directionalLight.getColor().y = Math.max(factor, 0.9f);
                directionalLight.getColor().z = Math.max(factor, 0.5f);
            } else {
                directionalLight.setIntensity(1);
                directionalLight.getColor().x =1;
                directionalLight.getColor().y =1;
                directionalLight.getColor().z =1;
            }
            double angRad = Math.toRadians(lightAngle);
            directionalLight.getDirection().x = (float) Math.sin(angRad);
            directionalLight.getDirection().y = (float) Math.cos(angRad);

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
        renderer.render(entity, camera, directionalLight);
    }

    @Override
    public void cleanup() {
        renderer.cleanup();
        loader.cleanup();
    }
}
