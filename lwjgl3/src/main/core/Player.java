public class Player extends Actor {
    private InputHandler inputHandler;

    public Player(float x, float y, float speed, int health, int experience, InputHandler inputHandler) {
        super(x, y, speed, health, experience);
        this.inputHandler = inputHandler;
    }

    @Override
    public void move(float deltaTime) {
        Vec2 direction = inputHandler.getMovementDirection();
        this.x += direction.x * speed * deltaTime;
        this.y += direction.y * speed * deltaTime;
    }

    @Override
    public void update(float deltaTime) {
        move(deltaTime);
    }

    @Override
    public void draw() {
        System.out.println("Rendering Player at (" + x + ", " + y + ")");
    }
}
