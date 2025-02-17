package io.github.some_example_name.lwjgl3.abstract_classes;

import io.github.some_example_name.lwjgl3.Movement.iMovable;

public abstract class MovableEntity extends Entity implements iMovable {
    protected float speed;

    public MovableEntity(float x, float y, float speed) {
        super(x, y);
        this.speed = speed;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void move(float deltaTime) {
        this.x += speed * deltaTime;
    }

    @Override
    public void moveAIControlled() {
        System.out.println("AI moving entity...");
    }

    @Override
    public void moveUserControlled(float deltaTime) {
        System.out.println("User controlling movement...");
    }
}

