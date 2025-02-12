package io.github.some_example_name.lwjgl3.Entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

public class EntityManager {
    private List<Entity> entityList;

    
    public EntityManager() {
        entityList = new ArrayList<>();
    }

    // Adds an entity to the list
    public void add(Entity entity) {
        entityList.add(entity);
    }

    // Removes an entity at a specific index
    public void remove(int index) {
        if (index >= 0 && index < entityList.size()) {
            entityList.remove(index);
        }
    }

    // Gets an entity at a specific index
    public Entity get(int index) {
        if (index >= 0 && index < entityList.size()) {
            return entityList.get(index);
        }
        return null;
    }

    // Calls the draw method for each entity using SpriteBatch
    public void draw(SpriteBatch batch) {
        for (Entity entity : entityList) {
            entity.draw(batch);
        }
    }

    // Calls the movement method for each entity
    public void moveAIControlled() {
        for (Entity entity : entityList) {
            entity.moveAIControlled();
        }
    }
    
    public void update() {
        for (Entity entity : entityList) {
            entity.update();
        }
    }
    
    public void dispose() {
    	for (Entity entity : entityList) {
    		entity.dispose();
    	}
    }
}
