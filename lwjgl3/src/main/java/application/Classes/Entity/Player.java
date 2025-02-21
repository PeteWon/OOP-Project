package application.Classes.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import abstract_classes.MovableEntity;
import abstract_classes.iCollidable;
import application.Classes.IO.ControllerInput;
import application.Managers.InputManager;

public class Player extends MovableEntity {
    private Texture texture;
    private float width = 50, height = 50; // Adjust size as needed
    private ControllerInput controllerInput;

    public Player(float x, float y, float speed) {
        super(x, y, speed);
        this.speed = speed;
        texture = new Texture(Gdx.files.internal("player.png")); // Load player image
        controllerInput = new ControllerInput(); // Initialize controller input
    }

    @Override
    public void draw() {

    }

    public void draw(SpriteBatch batch) { // Draw player image
        batch.draw(texture, x, y, width, height);
    }

    @Override
    public void update(float deltaTime) {
        moveUserControlled(deltaTime);
    }

    @Override
    public void moveUserControlled(float deltaTime) {
        float horizontal = InputManager.getMoveX(); // Uses IOManager for movement input
        float vertical = InputManager.getMoveY();

        move(deltaTime, horizontal, vertical); // Reuse movement logic
    }

    @Override
    public void moveAIControlled() {
        // Not used for Player
    }

    public Rectangle getBoundingBox() {
        return new Rectangle(x, y, 50, 50); // Adjust width & height if needed
    }

    public void setPosition(float x, float y) { // Fix: Add this method
        this.x = x;
        this.y = y;
    }

    @Override
    public void handleCollision(iCollidable other) {
        if (other instanceof Tree) {
            System.out.println("Player collided with a tree!");
        }
    }

    public void dispose() {
        if (texture != null) {
            texture.dispose();
        }
    }
}
