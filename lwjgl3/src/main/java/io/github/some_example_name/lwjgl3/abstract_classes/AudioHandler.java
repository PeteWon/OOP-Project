package io.github.some_example_name.lwjgl3.abstract_classes;

public abstract class AudioHandler {

    //audio controls for background music
    public abstract void playMusic();
    public abstract void stopMusic();
    public abstract boolean isPlayingMusic();

    // getter/setters for volume controls
    public abstract void setVolume(float volume);
    public abstract float getVolume();

    // getter/setters for backgroud music loop
    public abstract void setLoop(boolean loop);
    public abstract boolean getLoop();

    // cleanup
    public abstract void dispose();

}
