package io.github.some_example_name.lwjgl3.application;

import io.github.some_example_name.lwjgl3.Collision.CollisionManager;
import io.github.some_example_name.lwjgl3.abstract_classes.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class EntityManager {
    // Use list to track
    private List<Entity> entities;
    private List<Player> players;
    private List<Tree> trees;
    private Random random = new Random();

    // Reference CollisionManager to access the methods
    private CollisionManager collisionManager;

    public EntityManager() {
        this.entities = new ArrayList<>();
        this.players = new ArrayList<>();
        this.trees = new ArrayList<>();
        this.collisionManager = new CollisionManager(this);
    }

    public void updateEntities(float deltaTime) {
        // Removes inactive entities from list
        entities.removeIf(entity -> {
            if (!entity.isActive()) {
                entity.dispose();
                return true;
            }
            return false;
        });

        // Updates the behavior of each entity
        // : means "for each element in the list of"
        for (Entity entity : entities) {
            entity.update(deltaTime);

            // If entity is enemy, apply collisionBounce method
            if (entity instanceof Enemy) {
                Enemy enemy = (Enemy) entity;
                if (collisionManager != null) {
                    collisionManager.collisionBounce(enemy, trees);
                }
            }
        }

    }

    public void renderEntities(SpriteBatch batch) {
        for (Entity entity : entities) {
            entity.draw(batch); // Now draws ALL entities, including StaticObjects (Trees)
        }
    }

    public void addEntity(Entity entity) {
        if (!entities.contains(entity)) {
            entities.add(entity);
        }
    }

    public void removeEntity(Entity entity) {
        entity.dispose(); // Dispose before removing
        entities.remove(entity);
    }

    public void spawnEnemies(int count) {
        int maxWidth = Gdx.graphics.getWidth();
        int maxHeight = Gdx.graphics.getHeight();
        int enemySize = 50; // Adjust based on actual enemy size

        for (int i = 0; i < count; i++) {
            float x, y;
            boolean validPosition;

            do {
                // Randomly generates x and y within screen boundary
                validPosition = true;
                x = MathUtils.random(50, maxWidth - enemySize);
                y = MathUtils.random(50, maxHeight - enemySize);
                Rectangle newEnemyBounds = new Rectangle(x, y, enemySize, enemySize);

                // Check if new enemy position overlaps any existing enemies
                for (Entity entity : entities) {
                    if (entity instanceof Enemy && newEnemyBounds.overlaps(entity.getBoundingBox())) {
                        validPosition = false;
                        break;
                    }
                }

            } while (!validPosition); // Keep retrying until a valid position is found

            Enemy enemy = new Enemy(x, y, 200, this);
            addEntity(enemy);
        }
    }

    public void spawnPlayers(int count) {
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        int playerSize = 50; // Adjust based on actual player size
    
        // Define spawn zones (e.g., quadrants of the screen)
        float[][] spawnZones = {
                { 0, screenWidth / 2, screenHeight / 2, screenHeight }, // Top Left
                { screenWidth / 2, screenWidth, screenHeight / 2, screenHeight }, // Top Right
                { 0, screenWidth / 2, 0, screenHeight / 2 }, // Bottom Left
                { screenWidth / 2, screenWidth, 0, screenHeight / 2 } // Bottom Right
        };
    
        for (int i = 0; i < count; i++) {
            // Ensure max player count is 4 (Number of spawn zones)
            if (i >= spawnZones.length) {
                System.err.println("Too many players! Max allowed: " + spawnZones.length);
                break;
            }
    
            float[] zone = spawnZones[i];
            float playerX = MathUtils.random(zone[0] + playerSize, zone[1] - playerSize);
            float playerY = MathUtils.random(zone[2] + playerSize, zone[3] - playerSize);
    
            Player player = new Player(playerX, playerY, 200);
            players.add(player);
            addEntity(player);
    
            System.out.println("Spawned Player " + (i + 1) + " at: " + playerX + ", " + playerY);
        }
    }

    public void spawnTrees(int count) {
        int maxWidth = Gdx.graphics.getWidth();
        int maxHeight = Gdx.graphics.getHeight();
        int treeSize = 50;

        for (int i = 0; i < count; i++) {
            float x, y;
            boolean validPosition;

            do {
                validPosition = true;
                x = random.nextInt(maxWidth - treeSize);
                y = random.nextInt(maxHeight - treeSize);
                Rectangle newTreeBounds = new Rectangle(x, y, treeSize, treeSize);

                // Check if tree overlaps with any existing players or enemies
                for (Entity entity : entities) {
                    if (newTreeBounds.overlaps(entity.getBoundingBox())) {
                        validPosition = false;
                        break;
                    }
                }
            } while (!validPosition); // Keep retrying until a valid position is found

            Tree tree = new Tree(x, y);
            trees.add(tree);
            addEntity(tree);
        }
    }

    public List<Entity> getEntities() {
        return entities;
    }

    // Provide access to the list of players
    public List<Player> getPlayers() {
        return players;
    }

    public List<Tree> getTrees() {
        return trees;
    }

    public void dispose() {
        for (Entity entity : entities) {
            entity.dispose();
        }
        entities.clear(); // Prevent memory leaks
    }
}
