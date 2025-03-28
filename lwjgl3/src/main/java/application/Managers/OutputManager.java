package application.Managers;

public class OutputManager {
    private static float volume = 1.0f; // Default volume
    private static boolean isMuted = false; // Default mute state

    public static void setMuted(boolean muted) {
        isMuted = muted;
    }

    public static void setVolume(float newVolume) {
        volume = Math.max(0, Math.min(newVolume, 1.0f)); // Ensure 0.0 to 1.0 range
    }

    public static float getVolume() {
        return volume;
    }

    public static boolean isMuted() {
        return isMuted;
    }

    public void dispose() {
    }
}
