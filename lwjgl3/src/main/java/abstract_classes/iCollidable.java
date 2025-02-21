package abstract_classes;

import com.badlogic.gdx.math.Rectangle;

public interface iCollidable {

    void handleCollision(iCollidable other); // Handles collision with another CollidableEntity

    Rectangle getBoundingBox(); // Returns the bounding box for collision detection
}