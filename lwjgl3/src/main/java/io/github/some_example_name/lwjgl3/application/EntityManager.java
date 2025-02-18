package io.github.some_example_name.lwjgl3.application;

import io.github.some_example_name.lwjgl3.abstract_classes.Entity;
import java.util.ArrayList;
import java.util.List;

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
