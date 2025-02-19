package io.github.some_example_name.lwjgl3.application;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import io.github.some_example_name.lwjgl3.abstract_classes.MovableEntity;
import java.util.Random;

public class Enemy extends MovableEntity {
    private Texture texture;
    private float width = 50, height = 50;
    private float directionX, directionY; // Movement direction
    private Random random = new Random();
    private boolean hasCollided = false;

    public Enemy(float x, float y, float speed) {
        super(x, y, speed);
        this.speed = speed;
        texture = new Texture(Gdx.files.internal("enemy.png"));

        // Start moving in a random direction
        directionX = random.nextBoolean() ? 1 : -1;
        directionY = random.nextBoolean() ? 1 : -1;
    }

    @Override
    public void draw() {
        // Required override, but not used
    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, x, y, width, height);
    }

    @Override
    public void update(float deltaTime) {
        moveAIControlled();
    }

    @Override
    public void moveAIControlled() {
        float deltaTime = Gdx.graphics.getDeltaTime();

        move(deltaTime, directionX, directionY); // Reuse movement logic

    }

    @Override
    public void moveUserControlled(float deltaTime) {
        // Enemy should not be controlled by the player, so leave this empty
    }

    public void reverseXDirection() {
        directionX *= -1;
    }

    public void reverseYDirection() {
        directionY *= -1;
    }

    // Ensure direction is updated after bouncing
    public void updateDirection() {
        move(Gdx.graphics.getDeltaTime(), directionX, directionY);
    }

    public Rectangle getBoundingBox() {
        return new Rectangle(x, y, width, height);
    }

    public boolean hasCollided() {
        return hasCollided;
    }

    public void setCollided(boolean collided) {
        this.hasCollided = collided;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public void dispose() {
        if (texture != null) {
            texture.dispose();
        }
    }

}
