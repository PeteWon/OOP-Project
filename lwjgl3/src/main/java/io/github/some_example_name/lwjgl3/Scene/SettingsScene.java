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

import io.github.some_example_name.lwjgl3.IO.IOManager;

public class SettingsScene extends Scene {
    private Stage stage;
    private Skin skin;
    private Slider volumeSlider;
    private TextButton backButton;
    private TextButton muteButton;
    private Texture muteTexture, unmuteTexture;
    private boolean isMuted;
    private float lastVolume = 0.5f;
    private Table table;

    public SettingsScene(SceneManager game) {
        super(game, "background2.png");

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("uiskin.json"));

        // Load mute state
        isMuted = IOManager.isMuted();

        setupVolumeSlider(game);
        setupMuteButton(game);
        setupBackButton(game);

        // Create a table for layout
        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        // Add UI elements to the table
        table.add(volumeSlider).padBottom(20).row();
        table.add(muteButton).padBottom(20).row();
        table.add(backButton).padBottom(20).row();
    }

    private float prevVolume = -1f; // Stores the last printed volume

    private void setupVolumeSlider(SceneManager game) {
        volumeSlider = new Slider(0f, 1f, 0.05f, false, skin);

        // 🔹 Get the current volume from SceneManager instead of IOManager
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

                IOManager.setVolume(volume); // Persist volume
                IOManager.setMuted(isMuted);
                game.setBackgroundMusicVolume(volume); // 🔹 Update SceneManager's volume
                updateMuteButton();

                // 🔹 Print volume change only if it's different from previous volume
                if (volume != prevVolume) {
                    System.out.println("🔊 Volume changed to: " + volume);
                    prevVolume = volume; // Update the previous volume value
                }
            }
        });
    }

    // private void setupVolumeSlider(SceneManager game) {
    // volumeSlider = new Slider(0f, 1f, 0.05f, false, skin);
    // lastVolume = IOManager.getVolume(); // Retrieve last stored volume
    // volumeSlider.setValue(isMuted ? 0f : lastVolume);

    // volumeSlider.addListener(new ChangeListener() {
    // @Override
    // public void changed(ChangeEvent event, com.badlogic.gdx.scenes.scene2d.Actor
    // actor) {
    // float volume = volumeSlider.getValue();

    // if (volume == 0) {
    // isMuted = true;
    // } else {
    // isMuted = false;
    // lastVolume = volume; // Save the last non-zero volume
    // }

    // IOManager.setVolume(volume);
    // IOManager.setMuted(isMuted);
    // game.setBackgroundMusicVolume(volume);
    // updateMuteButton();
    // }
    // });
    // }

    private void setupMuteButton(SceneManager game) {
        muteButton = new TextButton(isMuted ? "Unmute" : "Mute", skin);

        muteButton.addListener(new com.badlogic.gdx.scenes.scene2d.utils.ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                isMuted = !isMuted;

                if (isMuted) {
                    lastVolume = volumeSlider.getValue() > 0 ? volumeSlider.getValue() : lastVolume; // Save last volume
                                                                                                     // before muting
                    IOManager.setVolume(0f);
                } else {
                    IOManager.setVolume(lastVolume); // Restore last volume
                }

                IOManager.setMuted(isMuted);
                game.setBackgroundMusicVolume(isMuted ? 0f : lastVolume);
                volumeSlider.setValue(isMuted ? 0f : lastVolume);
                updateMuteButton();
            }
        });
    }

    private void updateMuteButton() {
        muteButton.setText(isMuted ? "Unmute" : "Mute");
    }

    private void setupBackButton(SceneManager game) {
        backButton = new TextButton("Back", skin);

        backButton.addListener(new com.badlogic.gdx.scenes.scene2d.utils.ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("🔙 Returning to Main Menu...");
                game.setScene("home");
            }
        });
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

    @Override
    public void dispose() {
        super.dispose();
        stage.dispose();
        skin.dispose();
        muteTexture.dispose();
        unmuteTexture.dispose();
    }
}