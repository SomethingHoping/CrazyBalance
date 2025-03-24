package com.badlogic.crazybalance;

import com.badlogic.gdx.math.Vector2;

public class PlayerUnit extends BasicUnit{
	
	public Vector2 locus;
	
	public PlayerUnit() {
		super();
		locus = new Vector2();
		velocity_augends.add(locus);
	}
	
}
