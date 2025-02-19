package io.github.some_example_name.lwjgl3.Scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;

import io.github.some_example_name.lwjgl3.IO.OutputManager;
import io.github.some_example_name.lwjgl3.abstract_classes.Scene;

public class SettingsScene extends Scene {
    private Stage stage;
    private Skin skin;
    private Slider volumeSlider;
    private TextButton backButton;
    private TextButton muteButton;
    private Texture muteTexture, unmuteTexture;
    private boolean isMuted;
    private float lastVolume = 0.5f; // Stores the last volume before muting
    private Table table;
    private float prevVolume = -1f; // Stores the last printed volume

    public SettingsScene(SceneManager game) {
        super(game, "background2.png");

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("uiskin.json"));

        // Load mute state
        isMuted = OutputManager.isMuted();

        // Initialize UI components
        volumeSlider(game);
        muteButton(game);
        backButton(game);

        // Create a table to organize UI elements
        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        // Add UI elements to the table
        table.add(volumeSlider).padBottom(20).row();
        table.add(muteButton).padBottom(20).row();
        table.add(backButton).padBottom(20).row();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        System.out.println("Settings menu shown, input processor set");
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

    private void volumeSlider(SceneManager game) {
        volumeSlider = new Slider(0f, 1f, 0.05f, false, skin); // Create slider

        // Get the current volume from SceneManager instead of IOManager
        lastVolume = game.getBackgroundMusicVolume();
        isMuted = (lastVolume == 0f);

        volumeSlider.setValue(lastVolume); // Set slider to the actual game volume

        volumeSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, com.badlogic.gdx.scenes.scene2d.Actor actor) {
                float volume = volumeSlider.getValue();

                if (volume == 0) {
                    isMuted = true;
                } else {
                    isMuted = false;
                    lastVolume = volume; // Store last non-zero volume
                }

                // Update volume in OutputManager
                OutputManager.setVolume(volume);
                OutputManager.setMuted(isMuted);
                game.setBackgroundMusicVolume(volume); // Update SceneManager's volume
                updateMuteButton();

                // Print volume change only if it's different from previous volume
                if (volume != prevVolume) {
                    System.out.println("Volume changed to: " + volume);
                    prevVolume = volume; // Update the previous volume value
                }
            }
        });
    }

    private void muteButton(SceneManager game) {
        muteButton = new TextButton(isMuted ? "Unmute" : "Mute", skin); // Set initial button text

        muteButton.addListener(new com.badlogic.gdx.scenes.scene2d.utils.ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                isMuted = !isMuted; // Toggle mute state

                if (isMuted) {
                    // Save last volume before muting
                    lastVolume = volumeSlider.getValue() > 0 ? volumeSlider.getValue() : lastVolume;
                    OutputManager.setVolume(0f);
                } else {
                    OutputManager.setVolume(lastVolume); // Restore last volume
                }

                // Update mute state
                OutputManager.setMuted(isMuted);
                game.setBackgroundMusicVolume(isMuted ? 0f : lastVolume);
                volumeSlider.setValue(isMuted ? 0f : lastVolume);
                updateMuteButton();
            }
        });
    }

    private void updateMuteButton() {
        muteButton.setText(isMuted ? "Unmute" : "Mute");
    }

    private void backButton(SceneManager game) {
        backButton = new TextButton("Back", skin);

        backButton.addListener(new com.badlogic.gdx.scenes.scene2d.utils.ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Returning to Main Menu...");
                game.setScene("home");
            }
        });
    }

    @Override
    public void dispose() {
        super.dispose();
        stage.dispose();
        skin.dispose();
        muteTexture.dispose();
        unmuteTexture.dispose();
    }
}