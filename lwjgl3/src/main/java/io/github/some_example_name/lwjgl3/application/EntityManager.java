package io.github.some_example_name.lwjgl3.application;

import io.github.some_example_name.lwjgl3.abstract_classes.Entity;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.MathUtils;

public class EntityManager {
    private List<Entity> entities;

    public EntityManager() {
        this.entities = new ArrayList<>();
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

    public void renderEntities() {
        for (Entity entity : entities) {
            entity.draw();
        }
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void dispose() {
        for (Entity entity : entities) {
            entity.dispose();
        }
        entities.clear(); // Prevent memory leaks
    }
}
