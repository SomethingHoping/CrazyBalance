package com.badlogic.crazybalance;

import java.util.ArrayList;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RenderingSystem extends EntitySystem{
	
	private UnitsEngine engine;
	private SpriteBatch spriteBatch;
	
	public RenderingSystem(UnitsEngine engine, SpriteBatch spriteBatch) {
		this.engine = engine; this.spriteBatch = spriteBatch;
	}
	
	@Override
	public void update(float delta) {
		super.update(delta);
		
//		ArrayList<Entity> sortedEntities = new ArrayList<>();
//		for (Entity entity : getEngine().getEntitiesFor(Family.all(SpriteComponents.class).get())) {
//		    sortedEntities.add(entity);
//		}
		
		
	}
	
}
