package io.github.some_example_name.lwjgl3.Scene;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;

import io.github.some_example_name.lwjgl3.IO.Input.Keyboard;
import io.github.some_example_name.lwjgl3.abstract_classes.Scene;
import io.github.some_example_name.lwjgl3.application.Enemy;
import io.github.some_example_name.lwjgl3.application.EntityManager;
import io.github.some_example_name.lwjgl3.application.Player;

public class GameScene extends Scene {
    private Stage stage;
    private Skin skin;
    private ImageButton pauseButton;
    private Player player;
    private EntityManager entityManager;
    private SpriteBatch batch;
    private List<Enemy> enemies; // Store multiple enemies

    public GameScene(SceneManager game) {
        super(game, "background2.png");

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        batch = new SpriteBatch();

        // Load UI Skin
        skin = new Skin(Gdx.files.internal("uiskin.json"));

        // Load Pause Button
        pauseButton = new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("pause.png"))));
        pauseButton.setPosition(Gdx.graphics.getWidth() - 60, Gdx.graphics.getHeight() - 60);
        pauseButton.setSize(50, 50);

        pauseButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Pause Button Clicked! Opening StopScene...");
                game.setScene("stop");
            }
        });

        stage.addActor(pauseButton);

        initializeGame();

    }

    private void initializeGame() {
        // Initialize EntityManager and Player
        entityManager = new EntityManager();
        player = new Player(200, 300, 200);
        entityManager.addEntity(player);

        // Initialize Enemies (Spawn 5 at random locations)
        enemies = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            float enemyX = MathUtils.random(50, Gdx.graphics.getWidth() - 50);
            float enemyY = MathUtils.random(50, Gdx.graphics.getHeight() - 50);
            Enemy enemy = new Enemy(enemyX, enemyY, 200);
            enemies.add(enemy);
            entityManager.addEntity(enemy);
        }

    }

    // Collision detection between player and enemies
    private void checkCollisions() {
        for (Enemy enemy : enemies) {
            if (player.getBoundingBox().overlaps(enemy.getBoundingBox())) {
                if (!enemy.hasCollided()) { // Only print the first time collision happens
                    System.out.println("Enemy collided with player!");
                    enemy.setCollided(true); // Mark as collided
                }
            } else {
                enemy.setCollided(false); // Reset collision flag when separated
            }
        }
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        batch.begin();
        batch.draw(tex, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        player.draw(batch);

        // Draw all enemies
        for (Enemy enemy : enemies) {
            enemy.draw(batch);
        }
        batch.end();

        // Update & Move Entities
        entityManager.updateEntities(delta);

        // Collision Detection
        checkCollisions();

        Gdx.input.setInputProcessor(stage);
        // Ensure UI Elements (Buttons) Render Last
        stage.act(delta);
        stage.draw(); // Move this to the end

        if (Keyboard.isKeyPressed(Input.Keys.ESCAPE)) {
            game.setScene("stop");
        }

    }

    @Override
    public void dispose() {
        super.dispose();
        stage.dispose();
        skin.dispose();
        batch.dispose();
    }
}
