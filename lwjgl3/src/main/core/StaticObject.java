package core;

public class StaticObject extends Entity {
    public StaticObject(float x, float y) {
        super(x, y);
    }

    @Override
    public void update(float deltaTime) {
        // Static objects do not update
    }

    @Override
    public void draw() {
        System.out.println("Drawing static object at (" + x + ", " + y + ")");
    }
}
