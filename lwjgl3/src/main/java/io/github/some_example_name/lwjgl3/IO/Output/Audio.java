package io.github.some_example_name.lwjgl3.IO.Output;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

import io.github.some_example_name.lwjgl3.IO.IOManager;
import io.github.some_example_name.lwjgl3.abstract_classes.AudioHandler;

public class Audio extends AudioHandler {

    private Music gameMusic;
    private String music_name;
    private float volume;
    private boolean loop;

    public Audio() { // plays default music
        this.music_name = "Music/MainScreenMusic.mp3";
        this.volume = .1f;
        this.loop = true;
        this.gameMusic = Gdx.audio.newMusic(Gdx.files.internal(this.music_name));
        this.gameMusic.setVolume(this.volume);
        this.gameMusic.setLooping(this.loop);
    }

    public Audio(String music_name, float volume, boolean loop) { // plays selected music
        this.music_name = music_name;
        this.volume = volume;
        this.loop = loop;
        this.gameMusic = Gdx.audio.newMusic(Gdx.files.internal(this.music_name));
        this.gameMusic.setVolume(this.volume);
        this.gameMusic.setLooping(this.loop);
    }

    public static void setGlobalVolume(float volume) {
        IOManager.setVolume(volume);
    }

    public void setMusicName(String music_name) {
        this.music_name = music_name;
        this.gameMusic = Gdx.audio.newMusic(Gdx.files.internal(this.music_name));
    }

    public String getMusicName() {
        return this.music_name;
    }

    public void setVolume(float volume) {
        this.volume = volume;
        this.gameMusic.setVolume(this.volume);
    }

    public float getVolume() {
        return this.volume;
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
        this.gameMusic.setLooping(this.loop);
    }

    public boolean getLoop() {
        return this.loop;
    }

    public void playMusic() {
        this.gameMusic.play();
    }

    public void stopMusic() {
        this.gameMusic.stop();
    }

    public boolean isPlayingMusic() {
        return this.gameMusic.isPlaying();
    }

    public void pauseMusic() {
        if (gameMusic.isPlaying()) { // Only pause if the music is actually playing
            gameMusic.pause();
            System.out.println("Music Paused: " + music_name);
        }
    }

    public void dispose() {
        this.gameMusic.dispose();
    }

}
