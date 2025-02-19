package io.github.some_example_name.lwjgl3.abstract_classes;

public abstract class AudioHandler {

    // audio controls
    public abstract void playMusic();

    public abstract void stopMusic();

    public abstract boolean isPlayingMusic();

    // get-set for volume
    public abstract void setVolume(float volume);

    public abstract float getVolume();

    // get-set for loop
    public abstract void setLoop(boolean loop);

    public abstract boolean getLoop();

    // cleanup
    public abstract void dispose();

}
