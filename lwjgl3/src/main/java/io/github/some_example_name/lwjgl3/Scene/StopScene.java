package io.github.some_example_name.lwjgl3.Scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;

import io.github.some_example_name.lwjgl3.abstract_classes.Scene;
import io.github.some_example_name.lwjgl3.IO.Output.Audio;

public class StopScene extends Scene {
    private Stage stage;
    private Skin skin;
    private TextButton resumeButton;
    private TextButton quitButton;
    private TextButton restartButton;
    private Audio audio;

    public StopScene(SceneManager game) {
        super(game, "background2.png");

        // Initialize stage
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        // Load UI Skin
        skin = new Skin(Gdx.files.internal("uiskin.json"));

        audio = Audio.getInstance(); // Get the singleton instance of Audio

        // Initialize buttons
        resumeButton();
        quitGameButton();
        restartButton();

        // Add button to the stage
        stage.addActor(resumeButton);
        stage.addActor(quitButton);
        stage.addActor(restartButton);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        System.out.println("Stop Scene shown");
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.1f, 0.1f, 0.1f, 1);

        batch.begin();
        batch.draw(tex, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        stage.act(delta);
        stage.draw();
    }

    public void resumeButton() {
        // Create "Resume Game" button
        resumeButton = new TextButton("Resume Game", skin);
        resumeButton.setPosition(Gdx.graphics.getWidth() / 2f - 75, Gdx.graphics.getHeight() / 2f);
        // Add button click listener
        resumeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Resume Button Clicked! Returning to game...");
                audio.resumeMusic(); // Resume the music
                game.setScene("play", false); // Switch back to the game scene
            }
        });
    }

    public void quitGameButton() {
        quitButton = new TextButton("Quit Game", skin);
        quitButton.setPosition(Gdx.graphics.getWidth() / 2f - 75, Gdx.graphics.getHeight() / 2f - 100);

        // Add button click listener
        quitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Back to Main Menu Button Clicked! Returning to Main Menu...");
                audio.stopMusic(); // Stop the current music
                audio.playMusic(); // Restart the music
                game.setScene("home"); // Switch back to the game scene
            }
        });
    }

    public void restartButton() {
        restartButton = new TextButton("Restart Game", skin);
        restartButton.setPosition(Gdx.graphics.getWidth() / 2f - 75, Gdx.graphics.getHeight() / 2f - 50);

        // Add button click listener
        restartButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Restart Game Button Clicked! Restarting game...");
                audio.stopMusic(); // Stop the current music
                audio.playMusic(); // Restart the music
                game.setScene("play", true); // Switch back to the game scene
            }
        });
    }

    @Override
    public void dispose() {
        super.dispose();
        stage.dispose();
        skin.dispose();
    }
}