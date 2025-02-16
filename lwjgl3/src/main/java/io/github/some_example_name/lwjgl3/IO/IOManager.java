package io.github.some_example_name.lwjgl3.IO;

public class IOManager {
    private static float volume = 1.0f; // Default volume (1.0 = max)

    public static float getVolume() {
        return volume;
    }

    public static void setVolume(float newVolume) {
        volume = Math.max(0, Math.min(newVolume, 1.0f)); // ✅ Ensure 0.0 to 1.0 range
        System.out.println("🎵 Volume set to: " + volume);
    }
}
