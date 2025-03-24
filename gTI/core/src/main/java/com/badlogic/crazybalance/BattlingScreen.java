package com.badlogic.crazybalance;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class BattlingScreen implements Screen{

	private TowerDefenseMain game;
	private BattlingUI battlingUI;
	private ShapeRenderer shapeRenderer;
	private SpriteBatch spriteBatch;
	
	private FitViewport fightingArea;
	private StretchViewport backgroundArea;
	private static final float viewportWidth = 800f, viewportHeight = 500f;
	
	public static float battlefieldWidth, battlefieldHeight;
	
	private UnitsEngine engine;
	private BasicUnit player;
	
	private MovementSystem movementSystem;
	private RenderingSystem renderingSystem;
	
	public BattlingScreen(TowerDefenseMain game) {
		this.game = game;
		shapeRenderer = game.shapeRenderer;
		spriteBatch = game.batch;
		
		engine = new UnitsEngine();
		
		movementSystem = new MovementSystem();
		renderingSystem = new RenderingSystem(engine, spriteBatch);
		engine.addSystem(movementSystem);
		engine.addSystem(renderingSystem);
		
	}
	
	@Override
	public void show() {
		fightingArea = new FitViewport(viewportWidth, viewportHeight);
		backgroundArea = new StretchViewport(viewportWidth, viewportHeight);
		
		InputMultiplexer inputDetection = new InputMultiplexer();
		
		Gdx.input.setInputProcessor(inputDetection);
		
		battlefieldWidth = fightingArea.getWorldWidth();
		battlefieldHeight = fightingArea.getWorldHeight() / 2;
		
		player = new BasicUnit();
		engine.addEntity(player);
		player.x = battlefieldWidth / 2; // subtract by player dimensions
		player.y = battlefieldHeight / 2;
		
		// set spawning from level
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(Gdx.gl.GL_COLOR_CLEAR_VALUE);
		ScreenUtils.clear(new Color(0.5f,0.75f,1f,1));

		
		drawBackground();
		
		
		spriteBatch.begin();
		engine.update(delta);
		
		spriteBatch.end();
	}

	private void drawBackground() {
		
		backgroundArea.apply();
		shapeRenderer.setProjectionMatrix(backgroundArea.getCamera().combined);
		shapeRenderer.setAutoShapeType(true);
		
		shapeRenderer.begin();
		shapeRenderer.set(ShapeType.Filled);
		
		shapeRenderer.setColor(new Color(
				244/255f, 204/255f, 204/255f, 1f));
		shapeRenderer.triangle(
			xToBackground(600f), yToBackground(0f),
			xToBackground(100f), yToBackground(500f),
			xToBackground(0f), yToBackground(0f));
		
		shapeRenderer.setColor(Color.BLACK);
		shapeRenderer.rect(-1000, -1000, 5000f, 5000f);
		
		shapeRenderer.end();
	}
	
	private float xToBackground(float x) {
		return x - (backgroundArea.getWorldWidth() / 2);
	}
	private float yToBackground(float y) {
		return y - ( backgroundArea.getWorldHeight() / 2 );
	}
	
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		shapeRenderer.dispose();
		spriteBatch.dispose();
	}

}
