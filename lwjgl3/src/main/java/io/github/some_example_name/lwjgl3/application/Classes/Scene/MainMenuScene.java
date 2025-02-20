package io.github.some_example_name.lwjgl3.application.Classes.Scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;

import io.github.some_example_name.lwjgl3.abstract_classes.Scene;
import io.github.some_example_name.lwjgl3.application.Managers.SceneManager;

public class MainMenuScene extends Scene {
    private Stage stage;
    private Skin skin;
    private TextButton startButton;
    private boolean isMuted = false;
    private ImageButton muteButton;

    public MainMenuScene(SceneManager game) {
        super(game, "background2.png");

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("uiskin.json"));

        // Use centralized background music
        game.playBackgroundMusic();

        startButton();
        settingsButton();
        stage.addActor(startButton);

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        // System.out.println("Main menu shown, input processor set");

        if (!stage.getActors().contains(startButton, true)) {
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
    }

    private void startButton() {
        startButton = new TextButton("Start Game", skin);
        startButton.setPosition(Gdx.graphics.getWidth() / 2f - 50, Gdx.graphics.getHeight() / 2f);
        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Start Button Clicked! Switching to game scene...");
                game.setScene("play", true);
            }
        });
    }

    private void settingsButton() {
        TextButton settingsButton = new TextButton("Settings", skin);
        settingsButton.setPosition(Gdx.graphics.getWidth() / 2f - 50, 200);

        settingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Opening Settings...");
                game.setScene("settings"); // Go to settings scene
            }
        });

        stage.addActor(settingsButton); // Add to stage
    }

    @Override
    public void dispose() {
        super.dispose();
        stage.dispose();
        skin.dispose();
    }
}