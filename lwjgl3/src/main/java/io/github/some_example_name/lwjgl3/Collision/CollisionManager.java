package io.github.some_example_name.lwjgl3.Collision;

import java.util.ArrayList;
import java.util.List;
import com.badlogic.gdx.math.Rectangle;

import io.github.some_example_name.lwjgl3.application.Enemy;
import io.github.some_example_name.lwjgl3.application.EntityManager;
import io.github.some_example_name.lwjgl3.application.Player;
import io.github.some_example_name.lwjgl3.abstract_classes.Entity;
import io.github.some_example_name.lwjgl3.application.Tree;

public class CollisionManager {

    private EntityManager entityManager;

    public CollisionManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void checkCollisions() {
        List<Player> players = entityManager.getPlayers(); // Get all players
        List<Tree> trees = entityManager.getTrees();

        for (int i = 0; i < players.size(); i++) { // Iterate over all players
            Player player = players.get(i);

            for (Entity entity : entityManager.getEntities()) {
                if (entity instanceof Enemy) {
                    Enemy enemy = (Enemy) entity;

                    enemy.collisionBounce(trees);

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

        // Prevent players from moving into trees
        for (Player player : players) {
            boolean isColliding = false; // ✅ Track if the player is colliding

            for (Tree tree : trees) {
                if (player.getBoundingBox().overlaps(tree.getBoundingBox())) {
                    isColliding = true; // ✅ Player is colliding

                    if (!player.hasCollided()) { // ✅ Only print the first time per new collision
                        System.out.println("Player collided with a tree!");
                        player.setCollided(true); // ✅ Mark as colliding
                    }

                    // ✅ Move player back to prevent moving into the tree
                    player.setX(player.getPreviousX());
                    player.setY(player.getPreviousY());

                    break; // ✅ No need to check further trees if already colliding
                }
            }

            // ✅ Reset `hasCollided` only when the player moves away from all trees
            if (!isColliding && player.hasCollided()) {
                player.setCollided(false);
            }
        }

        // // Prevent players from moving into trees
        // for (Player player : players) {
        // for (Tree tree : trees) {
        // if (player.getBoundingBox().overlaps(tree.getBoundingBox())) {
        // if (!player.hasCollided()) { // Only print once per instance
        // } else {
        // if (player.hasCollided()) {
        // player.setCollided(false); // Reset when no longer colliding
        // }
        // }
        // System.out.println("Player collided with a tree!");
        // player.setX(player.getPreviousX()); // Move back to previous position
        // player.setY(player.getPreviousY());
        // }
        // }
        // }

        for (Entity entity : entityManager.getEntities()) {
            if (entity instanceof Enemy) {
                Enemy enemy = (Enemy) entity;

                for (Tree tree : trees) {
                    if (enemy.getBoundingBox().overlaps(tree.getBoundingBox()))
                        if (enemy.getBoundingBox().overlaps(tree.getBoundingBox())) {
                            System.out.println("Enemy collided with a tree!");

                            // ✅ Reverse movement direction (bounce effect)
                            float newX = enemy.getPreviousX();
                            float newY = enemy.getPreviousY();

                            enemy.setX(newX);
                            enemy.setY(newY);

                            enemy.collisionBounce(trees); // Ensure enemy moves away
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