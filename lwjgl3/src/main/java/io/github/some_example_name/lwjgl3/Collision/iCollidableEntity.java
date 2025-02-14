package io.github.some_example_name.lwjgl3.Collision;

import com.badlogic.gdx.math.Rectangle;

public interface iCollidableEntity {
    
    Rectangle getBoundingBox(); // Returns the bounding box for collision detection
    void handleCollision(iCollidableEntity other); // Handles collision with another CollidableEntity
}