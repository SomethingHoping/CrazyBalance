package com.badlogic.crazybalance;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
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
	private PlayerUnit player;
	
	private MovementSystem movementSystem;
	private RenderingSystem renderingSystem;
	
	public BattlingScreen(TowerDefenseMain game) {
		this.game = game;
		shapeRenderer = game.shapeRenderer;
		spriteBatch = game.batch;
		
		engine = new UnitsEngine();
		
		movementSystem = new MovementSystem(engine);
		renderingSystem = new RenderingSystem(engine, spriteBatch);
		engine.addSystem(movementSystem);
		engine.addSystem(renderingSystem);
		
	}
	
	@Override
	public void show() {
		fightingArea = new FitViewport(viewportWidth, viewportHeight);
		backgroundArea = new StretchViewport(viewportWidth, viewportHeight);
		battlingUI = new BattlingUI(fightingArea, game);
		
		InputMultiplexer inputDetection = new InputMultiplexer();
		inputDetection.addProcessor(battlingUI);
		
		Gdx.input.setInputProcessor(inputDetection);
		
		battlefieldWidth = fightingArea.getWorldWidth();// * (13f/16f);
		System.out.println(battlefieldWidth);
		battlefieldHeight = fightingArea.getWorldHeight() / 2;
		
		player = new PlayerUnit();
		engine.addEntity(player);
		player.x = battlefieldWidth / 2; // subtract by player dimensions
		player.y = battlefieldHeight / 2 - player.currentSprite.getHeight();
		
		
		// set spawning from level
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(Gdx.gl.GL_COLOR_CLEAR_VALUE);
		ScreenUtils.clear(new Color(0.5f,0.75f,1f,1));
		drawBackground();
		
		fightingArea.apply();
		spriteBatch.begin();
		playerMovementUpdate();
		engine.update(delta);
		spriteBatch.end();
		
		
		battlingUI.act();
		battlingUI.draw();
		
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
			xToBackground(0f), yToBackground(500f),
			xToBackground(0f), yToBackground(0f));
		
		shapeRenderer.setColor(new Color(
				11/255f, 83/255f, 148/255f, 1f));
		shapeRenderer.triangle(
			xToBackground(500f), yToBackground(0f),
			xToBackground(800f), yToBackground(700f),
			xToBackground(1000f), yToBackground(0f));
		
		shapeRenderer.setColor(Color.LIGHT_GRAY);
		shapeRenderer.rect(
			xToBackground(0f), yToBackground(0f),
			backgroundArea.getWorldWidth(), fightingArea.getWorldHeight() / 2);
		
		shapeRenderer.end();
	}	
	private float xToBackground(float x) {
		return x - (backgroundArea.getWorldWidth() / 2);
	}
	private float yToBackground(float y) {
		return y - (backgroundArea.getWorldHeight() / 2 );
	}
	
	@Override
	public void resize(int width, int height) {
	    backgroundArea.update(width, height);
	    fightingArea.update(width, height);
	}
	
	public void playerMovementUpdate() {
		
		float vx = 0, vy = 0;
		if (Gdx.input.isKeyPressed(Input.Keys.W)) vy += 5f;
		if (Gdx.input.isKeyPressed(Input.Keys.S)) vy += -5f;
		if (Gdx.input.isKeyPressed(Input.Keys.D)) vx += 5f;
		if (Gdx.input.isKeyPressed(Input.Keys.A)) vx += -5f;
		
		
		player.locus.set(vx, vy);
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
	}

}
