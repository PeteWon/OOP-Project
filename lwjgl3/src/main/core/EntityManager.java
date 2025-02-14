package core;

import abstract_classes.Entity;
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
        for (Entity entity : entities) {
            if (entity.isActive()) { // ✅ Check if entity is active before updating
                entity.update(deltaTime);
            }
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
}
