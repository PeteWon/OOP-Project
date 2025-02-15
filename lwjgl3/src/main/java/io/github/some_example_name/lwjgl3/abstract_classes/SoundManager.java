package io.github.some_example_name.lwjgl3.abstract_classes;

public abstract class SoundManager {
    
    //get-set for volume
    public abstract void setVolume(float volume);
    public abstract float getVolume();

    //get-set for loop
    public abstract void setLoop();
    public abstract boolean getLoop(boolean loop);

    //audio controls
    public abstract void playMusic();
    public abstract void stopMusic();
    public abstract boolean isPlayingMusic();

    //cleanup
    public abstract void dispose();

}
