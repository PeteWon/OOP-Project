package io.github.some_example_name.lwjgl3.abstract_classes;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class Entity implements iCollidable {
    protected float x, y; // Position in the game world
    protected boolean isActive; // Determines if the entity is currently active
    protected float width = 50, height = 50;
    protected boolean hasCollided = false;

    public Entity(float x, float y) {
        this.x = x;
        this.y = y;
        this.isActive = true; // Default to active when created
    }

    public abstract void draw();

    public abstract void draw(SpriteBatch batch);

    // Forces subclasses to implement their own behavior
    public abstract void update(float deltaTime);

    public boolean hasCollided() {
        return hasCollided;
    }

    public void setCollided(boolean collided) {
        this.hasCollided = collided;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    // For collision detection
    public Rectangle getBoundingBox() {
        return new Rectangle(x, y, width, height);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    @Override
    public abstract void handleCollision(iCollidable other);

    // New abstract dispose method
    public abstract void dispose();
}