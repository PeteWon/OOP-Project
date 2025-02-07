package io.github.some_example_name.lwjgl3;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;

	
public class GameMaster extends ApplicationAdapter {
    private SpriteBatch batch;
    private ShapeRenderer shape;
    private EntityManager entityManager;
    private Entity bucket;
    private Entity circle;
    private Entity triangle;
    private Entity triangle2;


	@Override
	public void create() {

		batch = new SpriteBatch();
		shape = new ShapeRenderer();
        entityManager = new EntityManager();
		
        bucket = new Bucket("bucket.png",Gdx.graphics.getWidth()/2,0,10);
        entityManager.add(bucket);
		
        for (int i=0;i<10;i++) {
        	float randomX= (float)(Math.random() * (Gdx.graphics.getWidth() - 30)) ;
        	float randomY = (float)(Math.random() * (Gdx.graphics.getHeight() - 30)) ;
        	entityManager.add(new Drop("droplet.png",randomX,randomY,2));
        }
        
        
        circle = new Circle(Color.RED, 50, 300, 100, 10); 
        triangle = new Triangle(Color.GREEN, 100, 100 , 5);
        triangle2 = new Triangle(Color.BLUE, 200, 100, 10, 150, 150, 250, 150, 200, 300);
	}
	
	@Override
	public void render() {

		ScreenUtils.clear(0, 0, 0.2f, 1);
		//Movement
        entityManager.moveAIControlled();
        circle.moveUserControlled();
        triangle.moveUserControlled();
        triangle2.moveUserControlled();
	    entityManager.update();
        
		
		batch.begin();
		entityManager.draw(batch);
		batch.end();
		
		//Shapes
        shape.begin(ShapeRenderer.ShapeType.Filled);
        circle.draw(shape);
        triangle.draw(shape);
        triangle2.draw(shape);
        shape.end();
	}
	
    @Override
    public void dispose() {
    	batch.dispose();
    	shape.dispose();
    	entityManager.dispose();

    }
}



