package com.engine3d.test;

import com.engine3d.core.entity.Entity;

public class GameLogic {

    private Entity entity;

    public GameLogic(Entity entity) {
        this.entity = entity;
    }

    public void move(int isPressed) {
        moveLeft(isPressed);
        moveRight(isPressed);
        moveUp(isPressed);
        moveDown(isPressed);
    }

    public void screenWrap() {
        float wrapLimit = 1.5f;

        if(entity.getPos().x < -wrapLimit)
            entity.getPos().x = wrapLimit;
        else if(entity.getPos().x > wrapLimit)
            entity.getPos().x = -wrapLimit;
        else if(entity.getPos().y < -wrapLimit)
            entity.getPos().y = wrapLimit;
        else if(entity.getPos().y    > wrapLimit)
            entity.getPos().y = -wrapLimit;
    }

    public void moveLeft(int isPressed) {
        if(isPressed == 1)
            entity.getPos().x -= 0.01f;
    }

    public void moveRight(int isPressed) {
        if(isPressed == -1)
            entity.getPos().x += 0.01f;
    }

    public void moveUp(int isPressed) {
        if(isPressed == 2)
            entity.getPos().y += 0.01f;
    }

    public void moveDown(int isPressed) {
        if(isPressed == -2)
            entity.getPos().y -= 0.01f;
    }
}
