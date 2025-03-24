package com.badlogic.crazybalance;

import java.util.ArrayList;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;

public class UnitsEngine extends Engine{
	
	public ArrayList<BasicUnit> units = new ArrayList<BasicUnit>();
	
	public UnitsEngine() {
		super();
	}
	
	
	public void addEntity(BasicUnit e) {
		addEntity((Entity)(e));
		units.add(e);
	}
	
	private boolean test = true;
	@Override
	public void update(float delta) {
		super.update(delta);
		if(test) {System.out.println("UnitsEngine update() ran"); test = false;}
	}
}
