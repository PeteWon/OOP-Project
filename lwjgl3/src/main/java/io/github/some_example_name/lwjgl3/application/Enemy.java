package io.github.some_example_name.lwjgl3.application;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import io.github.some_example_name.lwjgl3.abstract_classes.Entity;
import io.github.some_example_name.lwjgl3.Movement.iMovable;
import java.util.Random;

public class Enemy extends Entity implements iMovable {
    private Texture texture;
    private float speed;
    private float width = 50, height = 50;
    private float directionX, directionY; // Movement direction
    private Random random = new Random();
    private boolean hasCollided = false;

    public Enemy(float x, float y, float speed) {
        super(x, y);
        this.speed = speed;
        texture = new Texture(Gdx.files.internal("enemy.png"));

        // Start moving in a random direction
        directionX = random.nextBoolean() ? 1 : -1;
        directionY = random.nextBoolean() ? 1 : -1;
    }

    @Override
    public void moveAIControlled() {
        float deltaTime = Gdx.graphics.getDeltaTime();
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();

        // Move in the current direction
        x += directionX * speed * deltaTime;
        y += directionY * speed * deltaTime;

        // Bounce off walls
        if (x <= 0 || x + width >= screenWidth) {
            directionX *= -1; // Reverse horizontal direction
            x = Math.max(0, Math.min(x, screenWidth - width)); // Ensure within bounds
        }
        if (y <= 0 || y + height >= screenHeight) {
            directionY *= -1; // Reverse vertical direction
            y = Math.max(0, Math.min(y, screenHeight - height)); // Ensure within bounds
        }
    }

    @Override
    public void moveUserControlled(float deltaTime) {
        // Enemy should not be controlled by the player, so leave this empty
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

    public void draw(SpriteBatch batch) {
        batch.draw(texture, x, y, width, height);
    }

    @Override
    public void draw() {
        // Required override, but not used
    }

    @Override
    public void update(float deltaTime) {
        moveAIControlled();
    }

    public void dispose() {
        texture.dispose();
    }

}
