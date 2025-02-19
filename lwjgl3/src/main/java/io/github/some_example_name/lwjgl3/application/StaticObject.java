package io.github.some_example_name.lwjgl3.application;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;

import io.github.some_example_name.lwjgl3.abstract_classes.Entity;

public class StaticObject extends Entity {
    protected Texture texture;
    protected Rectangle boundingBox;

    public StaticObject(float x, float y, float width, float height, String texturePath) {
        super(x, y);
        this.texture = new Texture(Gdx.files.internal(texturePath));
        this.boundingBox = new Rectangle(x, y, width, height); // ✅ Create bounding box for collisions
    }

    @Override
    public void draw(SpriteBatch batch) { // ✅ Implementing the abstract method correctly
        if (texture != null) {
            batch.draw(texture, x, y, boundingBox.width, boundingBox.height);
        }
    }

    @Override
    public void draw() {
        // Optional: If static objects should have behavior (e.g., doors opening)
    }

    @Override
    public void update(float deltaTime) {
        // Static objects don’t move, so no update logic needed
    }

    public Rectangle getBoundingBox() {
        return boundingBox; // ✅ Ensure bounding box can be accessed for collisions
    }

    @Override
    public void dispose() {
        if (texture != null) {
            texture.dispose();
        }
    }
}

// public class StaticObject extends Entity {
// public StaticObject(float x, float y) {
// super(x, y);
// }

// @Override
// public void update(float deltaTime) {
// // Optional: If static objects should have behavior (e.g., doors opening)
// }

// @Override
// public void draw() {
// System.out.println("Drawing static object at (" + x + ", " + y + ")");
// }

// @Override
// public boolean isActive() {
// return true; // Always active unless explicitly changed
// }

// @Override
// public void dispose() {
// // Nothing to dispose for static objects.
// }
// }
