package io.github.some_example_name.lwjgl3.Collision;

import java.util.ArrayList;
import java.util.List;
import com.badlogic.gdx.math.Rectangle;

public class CollisionManager {

    // Method to identify collisions in a list of game objects
    public void evaluateCollisions(List<iCollidable> gameElements) {
        for (int i = 0; i < gameElements.size(); i++) {
            iCollidable entityA = gameElements.get(i);
            Rectangle boundsA = entityA.getBoundingBox();

            for (int j = i + 1; j < gameElements.size(); j++) {
                iCollidable entityB = gameElements.get(j);
                Rectangle boundsB = entityB.getBoundingBox();

                if (isIntersecting(boundsA, boundsB)) {
                    handleCollision(entityA, entityB);
                }
            }
        }
    }

    // Merges two lists of collidable entities and checks for collisions
    public void mergeAndEvaluate(List<iCollidable> players, List<iCollidable> obstacles) {
        List<iCollidable> collidableList = new ArrayList<>(players);
        collidableList.addAll(obstacles);
        evaluateCollisions(collidableList);
    }

    private void handleCollision(iCollidable entityA, iCollidable entityB) {
        entityA.handleCollision(entityB);
        entityB.handleCollision(entityA);
        System.out.println("Collision detected between: "
                + entityA.getClass().getSimpleName() + " and " + entityB.getClass().getSimpleName());
    }

    private boolean isIntersecting(Rectangle boxA, Rectangle boxB) {
        return boxA.overlaps(boxB);
    }
}