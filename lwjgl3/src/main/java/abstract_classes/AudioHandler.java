package abstract_classes;

public abstract class AudioHandler {

    // audio controls for background music
    public abstract void playMusic();

    public abstract void stopMusic();

    public abstract boolean isPlayingMusic();

    // audio controls for sound effects
    public abstract void loadSoundEffect(String key, String filePath);

    public abstract void playSoundEffect(String key);

    public abstract void setSoundEffectVolume(String key, float volume);

    // getter/setters for volume controls
    public abstract void setVolume(float volume);

    public abstract float getVolume();

    // getter/setters for backgroud music loop
    public abstract void setLoop(boolean loop);

    public abstract boolean getLoop();

    // cleanup
    public abstract void dispose();

}
