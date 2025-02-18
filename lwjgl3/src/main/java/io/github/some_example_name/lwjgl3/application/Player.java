package io.github.some_example_name.lwjgl3.application;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import io.github.some_example_name.lwjgl3.abstract_classes.Entity;
import io.github.some_example_name.lwjgl3.IO.IOManager;
import io.github.some_example_name.lwjgl3.IO.Input.ControllerInput;
import io.github.some_example_name.lwjgl3.Movement.iMovable;

public class Player extends Entity implements iMovable {
    private float speed; // Fix: Define speed
    private Texture texture;
    private float width = 50, height = 50; // Adjust size as needed
    private ControllerInput controllerInput;

    public Player(float x, float y, float speed) {
        super(x, y);
        this.speed = speed;
        texture = new Texture(Gdx.files.internal("player.png")); // Load player image
        controllerInput = new ControllerInput(); // Initialize controller input
    }

    @Override
    public void moveUserControlled(float deltaTime) {
        float horizontal = IOManager.getMoveX(); // Uses IOManager for movement input
        float vertical = IOManager.getMoveY();

        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();

        // Move left/right
        if (horizontal != 0) {
            x += speed * deltaTime * horizontal;
            x = Math.max(0, Math.min(x, screenWidth - width)); // Prevent going out of bounds
        }

        // Move up/down
        if (vertical != 0) {
            y += speed * deltaTime * vertical;
            y = Math.max(0, Math.min(y, screenHeight - height)); // Prevent going out of bounds
        }
    }

    @Override
    public void moveAIControlled() {
        // Not used for Player
    }

    public Rectangle getBoundingBox() {
        return new Rectangle(x, y, 50, 50); // Adjust width & height if needed
    }

    public void setPosition(float x, float y) { // Fix: Add this method
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw() { // Fix: Ensure correct method signature
        // System.out.println("Drawing Player at (" + x + ", " + y + ")");
    }

    public void draw(SpriteBatch batch) { // Draw player image
        batch.draw(texture, x, y, width, height);
    }

    @Override
    public void update(float deltaTime) {
        moveUserControlled(deltaTime);
    }

    public void dispose() {
        texture.dispose(); // Free memory when game exits
    }
}
