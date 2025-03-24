package com.badlogic.crazybalance;

import java.util.ArrayList;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.math.Vector2;

public class MovementSystem extends EntitySystem{
	
	public ArrayList<BasicUnit> units;
	public UnitsEngine engine;
	
	public MovementSystem(UnitsEngine engine) {
		super();
		this.engine = engine;
		units = engine.units;
	}
	
	@Override
	public void update(float delta) {
		for (BasicUnit unit : units) {
			
			// check unit interactions
			
			
			unit.update();
			
		}
	}
	
	
	
}
