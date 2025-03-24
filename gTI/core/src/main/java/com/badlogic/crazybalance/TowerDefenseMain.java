package com.badlogic.crazybalance;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

public class TowerDefenseMain extends Game {
	
    public SpriteBatch batch;
    public ShapeRenderer shapeRenderer;

    @Override
    public void create() {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        setScreen(new FirstScreen(this));
    }

    @Override
    public void dispose() {
        batch.dispose();
        shapeRenderer.dispose();
    }
}
