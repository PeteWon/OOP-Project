package io.github.some_example_name.lwjgl3.Collision;

import java.util.ArrayList;
import java.util.List;
import com.badlogic.gdx.math.Rectangle;

public class CollisionManager {

    // Method to identify collisions in a list of game objects
    public void evaluateCollisions(List<CollidableEntity> gameElements) {
        for (int i = 0; i < gameElements.size(); i++) {
            CollidableEntity entityA = gameElements.get(i);
            Rectangle boundsA = entityA.getBoundingBox();

            for (int j = i + 1; j < gameElements.size(); j++) {
                CollidableEntity entityB = gameElements.get(j);
                Rectangle boundsB = entityB.getBoundingBox();

                if (isIntersecting(boundsA, boundsB)) {
                    handleCollision(entityA, entityB);
                }
            }
        }
    }

    // Merges two lists of collidable entities and checks for collisions
    public void mergeAndEvaluate(List<CollidableEntity> players, List<CollidableEntity> obstacles) {
        List<CollidableEntity> collidableList = new ArrayList<>(players);
        collidableList.addAll(obstacles);
        evaluateCollisions(collidableList);
    }

    // Handles collision response between two entities
    private void handleCollision(CollidableEntity entityA, CollidableEntity entityB) {
        entityA.handleCollision(entityB);
        entityB.handleCollision(entityA);
        System.out.println("Collision occurred between: " + entityA + " and " + entityB);
    }

    private boolean isIntersecting(Rectangle boxA, Rectangle boxB) {
        return boxA.overlaps(boxB);
    }
}