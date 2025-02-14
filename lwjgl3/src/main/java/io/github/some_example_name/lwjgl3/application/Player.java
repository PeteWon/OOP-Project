package io.github.some_example_name.lwjgl3.application;

public class Player extends Actor {
    private float lastLoggedSpeed = -1; // ✅ Track previous speed to prevent spam logs

    public Player(float x, float y, float speed) {
        super(x, y, speed);
    }

    @Override
    public void update(float deltaTime) {
        move(deltaTime);

        if (getSpeed() != lastLoggedSpeed) {
            System.out.println("Player moving at speed: " + getSpeed());
            lastLoggedSpeed = getSpeed();
        }
    }

    @Override
    public void draw() {
        System.out.println("Rendering Player at (" + x + ", " + y + ")");
    }

    @Override
    public void performAction() {
        System.out.println("Player is performing an action!");
    }

    public void increaseSpeed(float amount) {
        float newSpeed = getSpeed() + amount;
        float MAX_SPEED = 10.0f; // ✅ Define a max speed limit
        if (newSpeed > MAX_SPEED) {
            newSpeed = MAX_SPEED;
        }
        setSpeed(newSpeed);
    }

    // public void increaseSpeed(float amount) {
    // setSpeed(getSpeed() + amount);
    // }
}
