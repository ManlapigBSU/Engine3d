package com.engine3d.core.utils;

import org.joml.Vector3f;
import org.joml.Vector4f;

public class Consts {

    public static final String TITLE = "3D Engine";

    public static final float FOV = (float) Math.toRadians(60);
    public static final float Z_NEAR = 0.01f;
    public static final float Z_FAR = 1000f;
    public static final float SPECULAR_POWER = 10f;

    public static final float MOUSE_SENSITIVITY = 0.1f;

    public static final Vector4f DEFAULT_COLOR = new Vector4f(1.0f, 1.0f, 1.0f, 1.0f);

    public static final Vector3f AMBIENT_LIGHT = new Vector3f(1.3f, 1.3f, 1.3f);
}
