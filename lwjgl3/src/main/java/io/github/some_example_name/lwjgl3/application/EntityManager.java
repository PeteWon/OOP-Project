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
        entities.remove(entity);
    }

    public void updateEntities(float deltaTime) {
        entities.removeIf(entity -> !entity.isActive()); // ✅ Removes inactive entities
        for (Entity entity : entities) {
            entity.update(deltaTime);
        }
    }

    // public void updateEntities(float deltaTime) {
    // for (Entity entity : entities) {
    // if (entity.isActive()) { // ✅ Check if entity is active before updating
    // entity.update(deltaTime);
    // }
    // }
    // }

    public void renderEntities() {
        for (Entity entity : entities) {
            entity.draw();
        }
    }

    public List<Entity> getEntities() {
        return entities;
    }
}
