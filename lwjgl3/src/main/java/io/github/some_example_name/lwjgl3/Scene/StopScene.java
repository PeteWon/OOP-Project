package io.github.some_example_name.lwjgl3.Scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.utils.ScreenUtils;

public class StopScene extends Scene {
    private Stage stage;
    private Skin skin;
    private TextButton resumeButton;

    public StopScene(SceneManager game) {
        super(game, "background.png");  // Change to your actual background image
        
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        // Load UI Skin (Ensure uiskin.json exists in your assets folder)
        skin = new Skin(Gdx.files.internal("uiskin.json"));

        // Create "Resume Game" button
        resumeButton = new TextButton("Resume Game", skin);
        resumeButton.setPosition(Gdx.graphics.getWidth() / 2f - 75, Gdx.graphics.getHeight() / 2f);
        
        // Add button click listener
        resumeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("✅ Resume Button Clicked! Returning to game...");
                game.setScene("play");  // ✅ Switch back to the game scene
            }
        });

        // Add button to the stage
        stage.addActor(resumeButton);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        System.out.println("✅ Stop Scene shown");
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.1f, 0.1f, 0.1f, 1);

        batch.begin();
        batch.draw(tex, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        stage.act(delta);
        stage.draw();

        // Allow ESC key to resume the game
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            System.out.println("✅ Escape key pressed! Returning to game...");
            game.setScene("play");
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        stage.dispose();
        skin.dispose();
    }
}
