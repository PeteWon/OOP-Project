package io.github.some_example_name.lwjgl3.IO.Output;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

import io.github.some_example_name.lwjgl3.IO.OutputManager;
import io.github.some_example_name.lwjgl3.abstract_classes.AudioHandler;

public class Audio extends AudioHandler {

    // Singleton instance of the Audio class
    // Only one instance of the Audio class is allowed throughout the app
    // Acts like a global variable with controlled access to a shared resource
    private static Audio instance;

    // Music object for playback
    private Music gameMusic;

    private String music_name;
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

    public void dispose() {
        this.gameMusic.dispose();
    }
}