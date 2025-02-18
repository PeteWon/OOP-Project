package io.github.some_example_name.lwjgl3.abstract_classes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import io.github.some_example_name.lwjgl3.Movement.iMovable;

public abstract class MovableEntity extends Entity implements iMovable {
    protected float speed;
    protected float width = 50, height = 50;

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

    // Extracted common movement logic
    protected void move(float deltaTime, float directionX, float directionY) {
        this.x += speed * deltaTime * directionX;
        this.y += speed * deltaTime * directionY;

        // Ensure entity stays within screen bounds
        this.x = Math.max(0, Math.min(this.x, Gdx.graphics.getWidth() - width));
        this.y = Math.max(0, Math.min(this.y, Gdx.graphics.getHeight() - height));
    }

    // public void move(float deltaTime) {
    // this.x += speed * deltaTime;
    // }

    @Override
    public void moveAIControlled() {
        System.out.println("AI moving entity...");
    }

    @Override
    public void moveUserControlled(float deltaTime) {
        System.out.println("User controlling movement...");
    }

    public abstract void draw(SpriteBatch batch);
}
