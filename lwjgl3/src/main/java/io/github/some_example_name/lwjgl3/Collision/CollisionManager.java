package io.github.some_example_name.lwjgl3.Collision;

import java.util.ArrayList;
import java.util.List;
import com.badlogic.gdx.math.Rectangle;

import io.github.some_example_name.lwjgl3.application.Enemy;
import io.github.some_example_name.lwjgl3.application.EntityManager;
import io.github.some_example_name.lwjgl3.application.Player;
import io.github.some_example_name.lwjgl3.abstract_classes.Entity;

public class CollisionManager {

    private EntityManager entityManager;

    public CollisionManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void checkCollisions() {
        List<Player> players = entityManager.getPlayers(); // Get all players

        for (int i = 0; i < players.size(); i++) { // Iterate over all players
            Player player = players.get(i);

            for (Entity entity : entityManager.getEntities()) {
                if (entity instanceof Enemy) {
                    Enemy enemy = (Enemy) entity;

                    if (player.getBoundingBox().overlaps(enemy.getBoundingBox())) {
                        if (!enemy.hasCollided()) {
                            System.out.println("Enemy collided with Player " + (i + 1) + "!");
                            enemy.setCollided(true);
                        }
                    } else {
                        enemy.setCollided(false);
                    }
                }
            }
        }
    }

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

    public void dispose() {
        // Nothing to dispose for collision handling
    }

}