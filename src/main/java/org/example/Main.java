package org.example;


import org.lwjgl.glfw.GLFW;

public class Main {
    public static void main(String[] args) {
        GLFW.glfwInit();
        String version = GLFW.glfwGetVersionString();
        System.out.println("GLFW Version" + version);

        GLFW.glfwTerminate();
    }
}