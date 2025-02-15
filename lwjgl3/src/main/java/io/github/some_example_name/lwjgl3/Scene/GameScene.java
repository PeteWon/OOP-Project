package io.github.some_example_name.lwjgl3.Scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScene extends Scene {
    private Stage stage;
    private Skin skin;
    private ImageButton pauseButton;
    private TextButton backToMenuButton;
    private Texture pauseTexture;

    public GameScene(SceneManager game) {
        super(game, "background.png");  // Make sure "background.png" exists in assets

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        // Load UI Skin (Make sure "uiskin.json" exists in assets)
        skin = new Skin(Gdx.files.internal("uiskin.json"));

        // Load Pause Icon from image.png
        pauseTexture = new Texture(Gdx.files.internal("pause.png")); 
        TextureRegionDrawable pauseDrawable = new TextureRegionDrawable(pauseTexture);
        pauseButton = new ImageButton(pauseDrawable);

        // Position the Pause Button (Top-Right)
        pauseButton.setPosition(Gdx.graphics.getWidth() - 100, Gdx.graphics.getHeight() - 100);
        pauseButton.setSize(50, 50);  // Adjust size as needed

        // Pause button click -> Go to StopScene
        pauseButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("✅ Pause Button Clicked! Opening StopScene...");
                game.setScene("stop");
            }
        });

        // Create "Back to Main Menu" Button (Bottom-Left)
        backToMenuButton = new TextButton("Back to Main Menu", skin);
        backToMenuButton.setPosition(50, 50);

        // Back button click -> Go to MainMenuScene
        backToMenuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("✅ Back to Menu Button Clicked! Returning to MainMenuScene...");
                game.setScene("home");
            }
        });

        // Add buttons to the stage
        stage.addActor(pauseButton);
        stage.addActor(backToMenuButton);
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
        batch.draw(tex, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

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
        pauseTexture.dispose();
    }
}
