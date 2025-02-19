package io.github.some_example_name.lwjgl3.Collision;

import java.util.List;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import io.github.some_example_name.lwjgl3.application.Enemy;
import io.github.some_example_name.lwjgl3.application.EntityManager;
import io.github.some_example_name.lwjgl3.application.Player;
import io.github.some_example_name.lwjgl3.abstract_classes.Entity;
import io.github.some_example_name.lwjgl3.application.Tree;
import io.github.some_example_name.lwjgl3.IO.Output.Audio;

public class CollisionManager {
    private EntityManager entityManager;
    private Audio audio = Audio.getInstance();

    public CollisionManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void checkCollisions() {
        List<Player> players = entityManager.getPlayers(); // Get all players
        List<Tree> trees = entityManager.getTrees();

        for (Entity entity : entityManager.getEntities()) {
            if (entity instanceof Enemy) {
                Enemy enemy = (Enemy) entity;
                boolean hasCollidedWithPlayer = false; // Track if the enemy collides with any player

                for (int i = 0; i < players.size(); i++) { // Iterate over all players
                    Player player = players.get(i);

                    if (enemy.getBoundingBox().overlaps(player.getBoundingBox())) {
                        if (!enemy.hasCollided()) { // Print only the first time per new collision
                            enemy.handleCollision(player);
                            audio.playSoundEffect("player");  // This will print "Enemy collided with Player!"
                            enemy.setCollided(true);
                        }
                        hasCollidedWithPlayer = true;
                        break; // Stop checking once an enemy collides with one player
                    }
                }

                // Reset `hasCollided` only when the enemy is no longer colliding with any player
                if (!hasCollidedWithPlayer) {
                    enemy.setCollided(false);
                }
            }
        }

        // Prevent players from moving into trees
        for (Player player : players) {
            boolean isColliding = false; // Track if the player is colliding

            for (Tree tree : trees) {
                if (player.getBoundingBox().overlaps(tree.getBoundingBox())) {
                    isColliding = true; // Player is colliding

                    if (!player.hasCollided()) { // Only print the first time per new collision
                        System.out.println("Player collided with a tree!");
                        audio.playSoundEffect("tree");
                        player.setCollided(true); // Mark as colliding
                    }

                    // Move player back to prevent moving into the tree
                    player.setX(player.getPreviousX());
                    player.setY(player.getPreviousY());

                    break; // No need to check further trees if already colliding
                }
            }

            // Reset `hasCollided` only when the player moves away from all trees
            if (!isColliding && player.hasCollided()) {
                player.setCollided(false);
            }
        }

        for (Entity entity : entityManager.getEntities()) {
            if (entity instanceof Enemy) {
                Enemy enemy = (Enemy) entity;

                for (Tree tree : trees) {
                    if (enemy.getBoundingBox().overlaps(tree.getBoundingBox())) {
                        enemy.handleCollision(tree);  // This will print "Enemy collided with a tree!"

                        // Reverse movement direction (bounce effect)
                        float newX = enemy.getPreviousX();
                        float newY = enemy.getPreviousY();

                        enemy.setX(newX);
                        enemy.setY(newY);

                        collisionBounce(enemy, trees);
                    }
                }
            }
        }
    }

    public void collisionBounce(Enemy enemy, List<Tree> trees) {
        boolean bounced = false;

        if (enemy.getX() <= 0 || enemy.getX() + enemy.getWidth() >= Gdx.graphics.getWidth()) {
            enemy.reverseXDirection();
            bounced = true;
        }
        if (enemy.getY() <= 0 || enemy.getY() + enemy.getHeight() >= Gdx.graphics.getHeight()) {
            enemy.reverseYDirection();
            bounced = true;
        }

        for (Tree tree : trees) {
            if (enemy.getBoundingBox().overlaps(tree.getBoundingBox())) {
                if (Math.abs(enemy.getPreviousX() - tree.getX()) < Math.abs(enemy.getPreviousY() - tree.getY())) {
                    enemy.reverseYDirection();
                } else {
                    enemy.reverseXDirection();
                }

                enemy.setX(enemy.getPreviousX());
                enemy.setY(enemy.getPreviousY());
                bounced = true;
            }
        }

        if (bounced) {
            enemy.updateDirection();
        }
    }

    public void dispose() {
        // Nothing to dispose for collision handling
    }
}