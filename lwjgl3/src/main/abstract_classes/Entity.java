package lwjgl3.src.main.abstract_classes;

public abstract class Entity {
    protected float x, y; // Position in the game world
    protected boolean isActive; // Determines if the entity is currently active

    public Entity(float x, float y) {
        this.x = x;
        this.y = y;
        this.isActive = true; // Default to active when created
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    // Forces subclasses to implement their own behavior
    public abstract void update(float deltaTime);

    public abstract void draw();
}
