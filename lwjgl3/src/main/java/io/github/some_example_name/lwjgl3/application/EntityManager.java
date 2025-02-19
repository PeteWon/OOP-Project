package io.github.some_example_name.lwjgl3.application;

import io.github.some_example_name.lwjgl3.abstract_classes.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class EntityManager {
    private List<Entity> entities;
    private List<Player> players; // New list to track multiple players
    private List<Tree> trees;

    public EntityManager() {
        this.entities = new ArrayList<>();
        this.players = new ArrayList<>();
        this.trees = new ArrayList<>();
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

    // Add enemy spawning method
    public void spawnEnemies(int count) {
        for (int i = 0; i < count; i++) {
            float enemyX = MathUtils.random(50, 750); // Adjust as needed
            float enemyY = MathUtils.random(50, 550); // Adjust as needed
            Enemy enemy = new Enemy(enemyX, enemyY, 200);
            addEntity(enemy); // Enemies are added to EntityManager
        }
    }

    public void spawnPlayers(int count) {
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();

        // Define fixed spawn positions for up to 4 players
        float[][] spawnPositions = {
                { screenWidth * 0.25f, screenHeight * 0.75f }, // Player 1 (Top Left)
                { screenWidth * 0.75f, screenHeight * 0.75f }, // Player 2 (Top Right)
                { screenWidth * 0.25f, screenHeight * 0.25f }, // Player 3 (Bottom Left)
                { screenWidth * 0.75f, screenHeight * 0.25f } // Player 4 (Bottom Right)
        };

        for (int i = 0; i < count; i++) {
            if (i >= spawnPositions.length) {
                System.err.println("Too many players! Max allowed: " + spawnPositions.length);
                break; // Prevent adding more than 4 players
            }

            float playerX = spawnPositions[i][0];
            float playerY = spawnPositions[i][1];

            Player player = new Player(playerX, playerY, 200);
            players.add(player);
            addEntity(player);

            System.out.println("Spawned Player " + (i + 1) + " at: " + playerX + ", " + playerY);
        }
    }

    public void spawnTrees(int count) {
        Random random = new Random();
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

                // ✅ Check if tree overlaps with any existing players or enemies
                for (Entity entity : entities) {
                    if (newTreeBounds.overlaps(entity.getBoundingBox())) {
                        validPosition = false;
                        break;
                    }
                }
            } while (!validPosition); // ✅ Keep retrying until a valid position is found

            Tree tree = new Tree(x, y);
            trees.add(tree);
            addEntity(tree);
        }
    }

    // // Add a method to spawn trees
    // public void spawnTrees() {
    // trees.add(new Tree(400, 300)); // Tree at fixed location
    // trees.add(new Tree(600, 400)); // Another tree

    // for (Tree tree : trees) {
    // addEntity(tree); // Add to entity list
    // }
    // }

    public void updateEntities(float deltaTime) {
        entities.removeIf(entity -> {
            if (!entity.isActive()) {
                entity.dispose(); // Dispose of inactive entities
                return true;
            }
            return false;
        });

        for (Entity entity : entities) {
            entity.update(deltaTime);
        }
    }

    public void renderEntities(SpriteBatch batch) {
        for (Entity entity : entities) {
            entity.draw(batch); // Now draws ALL entities, including StaticObjects (Trees)
        }
        // for (Entity entity : entities) {
        // if (entity instanceof MovableEntity) {
        // ((MovableEntity) entity).draw(batch);
        // }
        // }
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
