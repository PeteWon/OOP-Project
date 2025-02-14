package core;

import abstract_classes.Entity;

public class StaticObject extends Entity {
    public StaticObject(float x, float y) {
        super(x, y);
    }

    @Override
    public void update(float deltaTime) {
        // ✅ Optional: If static objects should have behavior (e.g., doors opening)
    }

    @Override
    public void draw() {
        System.out.println("Drawing static object at (" + x + ", " + y + ")");
    }

    @Override
    public boolean isActive() {
        return true; // ✅ Always active unless explicitly changed
    }
}
