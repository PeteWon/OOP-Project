package io.github.some_example_name.lwjgl3.Collision;

import com.badlogic.gdx.math.Rectangle;

public interface iCollidable {

    void handleCollision(iCollidable other); // Handles collision with another CollidableEntity

    Rectangle getBoundingBox(); // Returns the bounding box for collision detection
}