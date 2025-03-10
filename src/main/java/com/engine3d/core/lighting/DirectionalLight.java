package com.engine3d.core.lighting;

import org.joml.Vector3f;

public class DirectionalLight {

    private Vector3f color, direction;
    private float intensity;

    public DirectionalLight(float intensity, Vector3f direction, Vector3f color) {
        this.intensity = intensity;
        this.direction = direction;
        this.color = color;
    }

    public Vector3f getColor() {
        return color;
    }

    public void setColor(Vector3f color) {
        this.color = color;
    }

    public Vector3f getDirection() {
        return direction;
    }

    public void setDirection(Vector3f direction) {
        this.direction = direction;
    }

    public float getIntensity() {
        return intensity;
    }

    public void setIntensity(float intensity) {
        this.intensity = intensity;
    }
}
