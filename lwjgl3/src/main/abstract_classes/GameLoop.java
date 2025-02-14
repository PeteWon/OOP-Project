package abstract_classes;

public abstract class GameLoop {
    private boolean running = false;
    private final int TARGET_FPS = 60;
    private final long NS_PER_FRAME = 1000000000 / TARGET_FPS;

    public void start() {
        running = true;
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        int frames = 0;

        while (running) {
            long now = System.nanoTime();
            double delta = (now - lastTime) / (double) NS_PER_FRAME;
            lastTime = now;

            update(delta);
            render();

            frames++;

            // ✅ Improved FPS control
            long timeToSleep = (lastTime + NS_PER_FRAME) - System.nanoTime();
            if (timeToSleep > 0) {
                try {
                    Thread.sleep(timeToSleep / 1000000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (System.currentTimeMillis() - timer > 1000) {
                System.out.println("FPS: " + frames);
                frames = 0;
                timer += 1000;
            }
        }
    }

    public void stop() {
        running = false;
    }

    protected abstract void update(double deltaTime);

    protected abstract void render();
}
