package abstract_classes;

public abstract class MovableEntity extends Entity {
    protected float speed;

    public MovableEntity(float x, float y, float speed) {
        super(x, y);
        this.speed = speed;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public abstract void move(float deltaTime);
}
