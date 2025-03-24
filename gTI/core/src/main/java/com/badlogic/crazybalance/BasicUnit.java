package com.badlogic.crazybalance;

import com.badlogic.ashley.core.Entity;

public class BasicUnit extends Entity{
	
	public String name;
	public byte teamNumber;
	public boolean alive;
	
	public int health;
	
	// Position Components
	public float x, y;
	public float vx, vy;
	public float ax, ay;
	
	public float width, height;
	
	// Sprite components
	
}
