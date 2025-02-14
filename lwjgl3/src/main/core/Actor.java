package abstract_classes;

import abstract_classes.MovableEntity;

public abstract class Actor extends MovableEntity {
    public Actor(float x, float y, float speed) {
        super(x, y, speed);
    }

    public abstract void performAction(); // Generic method for AI or player control
}
