package io.github.some_example_name.lwjgl3.application;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.some_example_name.lwjgl3.abstract_classes.Entity;
import io.github.some_example_name.lwjgl3.Movement.iMovable;

public class Player extends Entity implements iMovable {
    private float speed;  // ✅ Fix: Define speed
    private Texture texture;
    private float width = 50, height = 50; // Adjust size as needed

    public Player(float x, float y, float speed) {
        super(x, y);
        this.speed = speed;
        texture = new Texture(Gdx.files.internal("player.png")); // ✅ Load player image
    }

    @Override
    public void update(float deltaTime) {
        moveUserControlled();
    }

    @Override
    public void draw() {  // ✅ Fix: Ensure correct method signature
        // System.out.println("Drawing Player at (" + x + ", " + y + ")");
    }

    public void draw(SpriteBatch batch) {  // ✅ Draw player image
        batch.draw(texture, x, y, width, height);
    }

    @Override
    public void moveUserControlled() {
        float deltaTime = Gdx.graphics.getDeltaTime();
        
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            x -= speed * deltaTime;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            x += speed * deltaTime;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            y += speed * deltaTime;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            y -= speed * deltaTime;
        }
    }

    @Override
    public void moveAIControlled() {
        // Not used for Player
    }

    public void dispose() {
        texture.dispose(); // Free memory when game exits
    }
}
