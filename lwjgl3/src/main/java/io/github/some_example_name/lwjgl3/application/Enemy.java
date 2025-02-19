package io.github.some_example_name.lwjgl3.application;

import java.util.List;

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
    public void moveAIControlled() {
        float deltaTime = Gdx.graphics.getDeltaTime();

        move(deltaTime, directionX, directionY); // Reuse movement logic

        // // Randomly change direction when hitting the screen edge
        // if (x <= 0 || x + width >= Gdx.graphics.getWidth()) {
        // directionX *= -1;
        // }
        // if (y <= 0 || y + height >= Gdx.graphics.getHeight()) {
        // directionY *= -1;
        // }

    }

    // Handles bouncing offs edges and trees
    public void collisionBounce(List<Tree> trees) {
        // ✅ Bounce off screen edges
        if (x <= 0 || x + width >= Gdx.graphics.getWidth()) {
            directionX *= -1; // Reverse X direction
            x = Math.max(0, Math.min(x, Gdx.graphics.getWidth() - width)); // Prevent sticking to edges
        }
        if (y <= 0 || y + height >= Gdx.graphics.getHeight()) {
            directionY *= -1; // Reverse Y direction
            y = Math.max(0, Math.min(y, Gdx.graphics.getHeight() - height)); // Prevent sticking to edges
        }

        // ✅ Bounce off trees the same way as walls
        for (Tree tree : trees) {
            if (getBoundingBox().overlaps(tree.getBoundingBox())) {
                System.out.println("Enemy collided with a tree!");

                // ✅ Reverse direction like hitting a wall
                if (Math.abs(previousX - tree.getX()) < Math.abs(previousY - tree.getY())) {
                    directionY *= -1; // More vertical overlap, bounce vertically
                } else {
                    directionX *= -1; // More horizontal overlap, bounce horizontally
                }

                // ✅ Move away from the tree slightly to prevent getting stuck
                x = previousX;
                y = previousY;
            }
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
        if (texture != null) {
            texture.dispose();
        }
    }

}
