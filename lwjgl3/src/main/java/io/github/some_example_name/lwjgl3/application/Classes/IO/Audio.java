package io.github.some_example_name.lwjgl3.application.Classes.IO;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

import java.util.HashMap;
import java.util.Map;

import io.github.some_example_name.lwjgl3.abstract_classes.AudioHandler;
import io.github.some_example_name.lwjgl3.application.Managers.OutputManager;

public class Audio extends AudioHandler {

    // Singleton instance of the Audio class
    // Only one instance of the Audio class is allowed throughout the app
    // Acts like a global variable with controlled access to a shared resource
    private static Audio instance;

    // Music object for playback
    private Music gameMusic;

    private String music_name;

    private Map<String, Sound> soundEffects;
    private Map<String, Float> soundEffectVolumes;

    private float volume;
    private boolean loop;

    private Audio() {
        this("Music/MainScreenMusic.mp3", 0.1f, true);
    }

    // Plays specific music track with volume and loop settings
    private Audio(String music_name, float volume, boolean loop) {
        this.music_name = music_name;
        this.volume = volume;
        this.loop = loop;
        this.gameMusic = Gdx.audio.newMusic(Gdx.files.internal(this.music_name));
        this.gameMusic.setVolume(this.volume);
        this.gameMusic.setLooping(this.loop);

        this.soundEffects = new HashMap<>();
        this.soundEffectVolumes = new HashMap<>();

        System.out.println("Music started: " + this.music_name);
    }

    public static Audio getInstance() {
        if (instance == null) {
            instance = new Audio();
        }
        return instance;
    }

    // Retrieves or updates the singleton instance with a new track
    public static Audio getInstance(String music_name, float volume, boolean loop) {
        if (instance == null) {
            instance = new Audio(music_name, volume, loop);
        } else {
            instance.setMusicName(music_name);
            instance.setVolume(volume);
            instance.setLoop(loop);
        }
        return instance;
    }

    public void playMusic() {
        this.gameMusic.play();
        System.out.println("Music playing: " + this.music_name);
    }

    public void stopMusic() {
        this.gameMusic.stop();
        System.out.println("Music stopped: " + this.music_name);
    }

    public void pauseMusic() {
        if (gameMusic.isPlaying()) { // Only pause if the music is actually playing
            gameMusic.pause();
            System.out.println("Music Paused: " + music_name);
        } else {
            System.out.println("Music is not playing, cannot pause.");
        }
    }

    public void resumeMusic() {
        if (!gameMusic.isPlaying()) { // Only resume if the music is not playing
            gameMusic.play();
            System.out.println("Music Resumed: " + music_name);
        } else {
            System.out.println("Music is already playing.");
        }
    }

    public static void setGlobalVolume(float volume) {
        OutputManager.setVolume(volume);
    }

    public void setMusicName(String music_name) {
        this.music_name = music_name;
        this.gameMusic = Gdx.audio.newMusic(Gdx.files.internal(this.music_name));
        this.gameMusic.setVolume(this.volume);
        this.gameMusic.setLooping(this.loop);
        System.out.println("Music started: " + this.music_name);
    }

    public void setVolume(float volume) {
        this.volume = volume;
        this.gameMusic.setVolume(this.volume);
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
        this.gameMusic.setLooping(this.loop);
    }

    public String getMusicName() {
        return this.music_name;
    }

    public float getVolume() {
        return this.volume;
    }

    public boolean getLoop() {
        return this.loop;
    }

    public boolean isPlayingMusic() {
        return this.gameMusic.isPlaying();
    }

    // Load a sound effect into memory
    public void loadSoundEffect(String key, String filePath) {
        if (!soundEffects.containsKey(key)) {
            Sound sound = Gdx.audio.newSound(Gdx.files.internal(filePath));
            soundEffects.put(key, sound);
            System.out.println("Sound effect loaded: " + filePath);
        }
    }

    // Play a specific sound effect
    public void playSoundEffect(String key) {
        Sound sound = soundEffects.get(key);
        if (sound != null) {
            float volume = soundEffectVolumes.getOrDefault(key, 0.5f); // Get the volume, default is 1.0
            sound.play(volume);
            System.out.println("Playing sound effect: " + key);
        } else {
            System.out.println("Sound effect not found: " + key);
        }
    }

    // Set the volume of a specific sound effect
    public void setSoundEffectVolume(String key, float volume) {
        if (soundEffects.containsKey(key)) {
            soundEffectVolumes.put(key, volume);
        } else {
            System.out.println("Sound effect not found: " + key);
        }
    }

    // Disposes of all audio resources
    public void dispose() {
        this.gameMusic.dispose();
        for (Sound sound : soundEffects.values()) {
            sound.dispose();
        }
        soundEffects.clear();
    }

}