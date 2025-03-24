package com.badlogic.crazybalance;

import java.util.ArrayList;
import java.util.Comparator;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RenderingSystem extends EntitySystem{
	
	private UnitsEngine engine;
	private SpriteBatch spriteBatch;
	
	public RenderingSystem(UnitsEngine engine, SpriteBatch spriteBatch) {
		this.engine = engine; this.spriteBatch = spriteBatch;
	}
	
	
	public boolean hasRun = false;
	@SuppressWarnings("unchecked")
	@Override
	public void update(float delta) {
		super.update(delta);
		
		ArrayList<BasicUnit> sortedEntities = (ArrayList<BasicUnit>) engine.units.clone();
		sortedEntities.sort(new Comparator<BasicUnit>() {
			@Override
			public int compare(BasicUnit unit1, BasicUnit unit2) {
				return -Float.compare(unit1.y, unit2.y);
			}
		});
		
		for(BasicUnit unit : sortedEntities) {
			unit.currentSprite.setX(unit.x);
			unit.currentSprite.setY(unit.y);
			unit.currentSprite.draw(spriteBatch);
			
			if (!hasRun) {
				System.out.println(
				unit.currentSprite.getX() + " " +
				unit.currentSprite.getY() + " ");
				System.out.println(" - ");
				System.out.println(
						unit.x + " " +
						unit.y + " ");
				hasRun = true;
			}
		}
		
//		for (Entity entity : getEngine().getEntitiesFor(Family.all(SpriteComponents.class).get())) {
//		    sortedEntities.add(entity);
//		}
		
		
	}
	
}
