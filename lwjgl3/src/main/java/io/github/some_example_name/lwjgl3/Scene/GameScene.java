package io.github.some_example_name.lwjgl3.Scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.some_example_name.lwjgl3.application.Player;
import io.github.some_example_name.lwjgl3.application.Enemy;
import io.github.some_example_name.lwjgl3.application.EntityManager;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

public class GameScene extends Scene {
    private Stage stage;
    private Skin skin;
    private ImageButton pauseButton;
    private Player player;
    private Enemy enemy;
    private EntityManager entityManager;
    private SpriteBatch batch;

    public GameScene(SceneManager game) {
        super(game, "background.png");

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        batch = new SpriteBatch();

        // Load UI Skin
        skin = new Skin(Gdx.files.internal("uiskin.json"));

        // ✅ Only Keep Pause Button (Remove "Back to Main Menu")
        pauseButton = new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("pause.png"))));
        pauseButton.setPosition(Gdx.graphics.getWidth() - 100, Gdx.graphics.getHeight() - 100);
        pauseButton.setSize(50, 50);

        pauseButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScene("stop");
            }
        });

        stage.addActor(pauseButton);

        // ✅ Initialize EntityManager and Player
        entityManager = new EntityManager();
        player = new Player(200, 300, 200);
        entityManager.addEntity(player);

        enemy = new Enemy(100, 100, 200);
        entityManager.addEntity(enemy);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        batch.begin();
        batch.draw(tex, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        player.draw(batch);
        enemy.draw(batch);
        batch.end();

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
        batch.dispose();
    }
}
