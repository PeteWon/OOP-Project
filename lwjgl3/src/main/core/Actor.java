package core;

public class Actor extends MovableEntity {
    protected int health;
    protected int experience;

    public Actor(float x, float y, float speed, int health, int experience) {
        super(x, y, speed);
        this.health = health;
        this.experience = experience;
    }

    public int getHealth() {
        return health;
    }

    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health <= 0) {
            this.isActive = false;
            System.out.println("Actor has died!");
        }
    }

    @Override
    public void move(float deltaTime) {
        this.x += speed * deltaTime; // AI movement logic
    }

    @Override
    public void update(float deltaTime) {
        move(deltaTime);
    }

    @Override
    public void draw() {
        System.out.println("Rendering NPC at (" + x + ", " + y + ")");
    }
}
