package io.github.some_example_name.lwjgl3.Scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainMenuScene extends Scene {
    private Stage stage;
    private Skin skin;
    private TextButton startButton;

    public MainMenuScene(SceneManager game) {
        super(game, "background.png"); // Change to your actual background image

        stage = new Stage();
        skin = new Skin(Gdx.files.internal("uiskin.json")); // Make sure uiskin.json exists

        // Create Start Button
        startButton = new TextButton("Start Game", skin);
        startButton.setPosition(Gdx.graphics.getWidth() / 2f - 50, Gdx.graphics.getHeight() / 2f);
        // Add button click listener
        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("✅ Start Button Clicked! Attempting to switch to play scene...");
                game.setScene("play");  // ✅ Ensure correct scene transition
            }
        });

        stage.addActor(startButton);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        System.out.println("✅ Main menu shown, input processor set");
    
        if (!stage.getActors().contains(startButton, true)) { 
            System.out.println("🔄 Re-adding Start Button to MainMenuScene");
            stage.addActor(startButton);
        }
    }
    

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        batch.begin();
        batch.draw(tex, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        stage.act(delta);
        stage.draw();
        // Allow "Enter" key to start the game
        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
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