package io.github.some_example_name.lwjgl3;
//kk part 2
//jjii
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;


public class Circle extends Entity{
	private Color color;
	private float radius;	
	
	public Circle() {
	    super(0, 0, 0);  // Initializes with default values
	    this.color = Color.WHITE;
	    this.radius = 50;  // Default radius
	}
	
	public Circle(Color color, float radius, float x, float y, float speed) {
		super(x,y,speed);
		this.color = color;
		this.radius = radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}
	public float getRadius() {
		return radius;
	}
	
	@Override
	public void draw(ShapeRenderer shape) {
		shape.setColor(color);
		shape.circle(getX(), getY(), radius);
	}
	
	
	@Override
	public void moveUserControlled() {
        if (Gdx.input.isKeyPressed(Keys.UP)) {
            setY(getY() + getSpeed());
        }
        if (Gdx.input.isKeyPressed(Keys.DOWN)) {
            setY(getY() - getSpeed());
        }
        if (getY() > Gdx.graphics.getHeight()) {
            setY(0); // Appears on the left
        }
        if (getY() < 0) {
            setY(Gdx.graphics.getHeight()); // Appears on the right
        }

	}
	
	@Override
	public void update() {
	    System.out.println("In circle of radius " + radius + " at " + getX() + "," + getY() + " position");
	    moveUserControlled();  
	}
}
	
	