package com.badlogic.crazybalance;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class FirstScreen implements Screen {
	
	private final TowerDefenseMain game;
	private Skin skin;
	private Stage stage;
	
	public FirstScreen(TowerDefenseMain game) {
		this.game = game;
		
	}
	
	@Override
	public void show() {
		skin = new Skin(Gdx.files.internal("metalui/metal-ui.json"));
		
		stage = new Stage(new ScreenViewport());
		setInput();
		makeUI(stage);
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act();
		stage.draw();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		stage.getViewport().update(width, height, true);
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
		stage.dispose();
	}
	
	private void setInput() {
		InputMultiplexer inputDistributor = new InputMultiplexer();
		inputDistributor.addProcessor(stage);
		Gdx.input.setInputProcessor(inputDistributor);
	}
	
	private void makeUI(Stage stage) {
		Table root = new Table();
		root.setTouchable(Touchable.enabled);
		
		TextButton testButton = new TextButton("Start Game", skin);
		root.add(testButton).center();
		
		testButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y ) {
				game.setScreen(new BattlingScreen(game));
				System.out.println("I was clicked!");
			}
		});
		
		root.add();
		root.setFillParent(true);
		
		
		stage.addActor(root);
	}
	
}
