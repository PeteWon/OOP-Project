package io.github.some_example_name.lwjgl3.abstract_classes;

public abstract class SoundManager {
    
    //sound effects
    public abstract void loadSound(String soundId, String filepath);
    public abstract void playSound(String soundId, boolean loop);
    public abstract void stopSound(String soundId);
    public abstract void pauseSound(String soundId);
    public abstract void resumeSound(String soundId);

    //background music
    public abstract void loadMusic(String filepath);
    public abstract void playMusic(boolean loop);
    public abstract void stopMusic();
    public abstract void pauseMusic();
    public abstract void resumeMusic();

    //volume control
    public abstract void setSoundVolume(float volume);
    public abstract void setMusicVolume(float volume);

    //mute control
    public abstract void muteSounds();
    public abstract void unmuteSounds();
    public abstract void muteMusic();
    public abstract void unmuteMusic();

    //cleanup
    public abstract void cleanup();

}
