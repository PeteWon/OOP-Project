package io.github.some_example_name.lwjgl3.Scene;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;

import io.github.some_example_name.lwjgl3.IO.Input.Keyboard;
import io.github.some_example_name.lwjgl3.IO.Output.Audio;
import io.github.some_example_name.lwjgl3.abstract_classes.Entity;
import io.github.some_example_name.lwjgl3.abstract_classes.MovableEntity;
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
    private Audio audio;

    public GameScene(SceneManager game) {
        super(game, "background2.png");

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        batch = new SpriteBatch();

        // Load UI Skin
        skin = new Skin(Gdx.files.internal("uiskin.json"));

        // Initialize Audio
        audio = Audio.getInstance();

        // Load Pause Button
        pauseButton = new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("pause.png"))));
        pauseButton.setPosition(Gdx.graphics.getWidth() - 60, Gdx.graphics.getHeight() - 60);
        pauseButton.setSize(50, 50);

        pauseButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Pause Button Clicked! Opening StopScene...");
                audio.pauseMusic();
                game.setScene("stop");
            }
        });

        stage.addActor(pauseButton);

        initializeGame();

    }

    private void initializeGame() {
        // Initialize EntityManager and Player
        entityManager = new EntityManager();
        entityManager.spawnPlayers(4); // Spawn players using EntityManager
        entityManager.spawnEnemies(7); // Spawn enemies using EntityManager

    }

    private void checkCollisions() {
        List<Player> players = entityManager.getPlayers(); // Get all players

        for (int i = 0; i < players.size(); i++) { // Use index + 1 for correct player numbering
            Player player = players.get(i);

            for (Entity entity : entityManager.getEntities()) {
                if (entity instanceof Enemy) {
                    Enemy enemy = (Enemy) entity;

                    if (player.getBoundingBox().overlaps(enemy.getBoundingBox())) {
                        if (!enemy.hasCollided()) {
                            System.out.println("Enemy collided with Player " + (i + 1) + "!"); // Fix player
                                                                                               // numbering
                            enemy.setCollided(true);
                        }
                    } else {
                        enemy.setCollided(false);
                    }
                }
            }
        }
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        batch.begin();
        batch.draw(tex, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        // Draw all entities (Players & Enemies)
        for (Entity entity : entityManager.getEntities()) {
            if (entity instanceof MovableEntity) {
                ((MovableEntity) entity).draw(batch);
            }
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
        entityManager.dispose();
    }
}
