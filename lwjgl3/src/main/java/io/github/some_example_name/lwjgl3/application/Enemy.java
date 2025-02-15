package io.github.some_example_name.lwjgl3.application;

import com.badlogic.gdx.Gdx;
// import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.some_example_name.lwjgl3.abstract_classes.Entity;
import io.github.some_example_name.lwjgl3.Movement.iMovable;

public class Enemy extends Entity implements iMovable {
    private float speed;  // ✅ Fix: Define speed
    private Texture texture;
    private float width = 50, height = 50; // Adjust size as needed

    public Enemy(float x, float y, float speed) {
        super(x, y);
        this.speed = speed;
        texture = new Texture(Gdx.files.internal("enemy.png")); // ✅ Load player image
    }

    @Override
    public void update(float deltaTime) {
        moveAIControlled();
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
    }

    @Override
    public void moveAIControlled() {
    }

    public void dispose() {
        texture.dispose(); // Free memory when game exits
    }
}
