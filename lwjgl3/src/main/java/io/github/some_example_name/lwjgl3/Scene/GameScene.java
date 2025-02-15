package io.github.some_example_name.lwjgl3.Scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.some_example_name.lwjgl3.application.Player;
import io.github.some_example_name.lwjgl3.application.EntityManager;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

public class GameScene extends Scene {
    private Stage stage;
    private Skin skin;
    private ImageButton pauseButton;
    private TextButton backToMenuButton;
    private Player player;
    private EntityManager entityManager;
    private SpriteBatch batch;  // ✅ Add SpriteBatch for rendering

    public GameScene(SceneManager game) {
        super(game, "background.png");

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        batch = new SpriteBatch(); // ✅ Initialize SpriteBatch

        // Load UI Skin
        skin = new Skin(Gdx.files.internal("uiskin.json"));

        // Load Pause Button
        pauseButton = new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("pause.png"))));
        pauseButton.setPosition(Gdx.graphics.getWidth() - 100, Gdx.graphics.getHeight() - 100);
        pauseButton.setSize(50, 50);

        pauseButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("✅ Pause Button Clicked! Opening StopScene...");
                game.setScene("stop");
            }
        });

        // Create "Back to Main Menu" Button
        backToMenuButton = new TextButton("Back to Main Menu", skin);
        backToMenuButton.setPosition(50, 50);

        backToMenuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("✅ Back to Menu Button Clicked! Returning to MainMenuScene...");
                game.setScene("home");
            }
        });

        stage.addActor(pauseButton);
        stage.addActor(backToMenuButton);

        // ✅ Initialize EntityManager and Player
        entityManager = new EntityManager();
        player = new Player(400, 300, 200); // Spawn player in the center
        entityManager.addEntity(player);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        System.out.println("✅ GameScene shown");
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        batch.begin();
        batch.draw(tex, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); // Draw background
        player.draw(batch); // ✅ Draw player sprite
        batch.end();

        // ✅ Update & Draw Player
        entityManager.updateEntities(delta);
        entityManager.renderEntities();

        stage.act(delta);
        stage.draw();

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            game.setScene("stop");
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        stage.dispose();
        skin.dispose();
        batch.dispose(); // ✅ Dispose batch
    }
}
