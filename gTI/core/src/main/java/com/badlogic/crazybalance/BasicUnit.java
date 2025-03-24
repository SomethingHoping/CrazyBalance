package com.badlogic.crazybalance;

import java.util.ArrayList;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class BasicUnit extends Entity{
	
	public String name;
	public short ID;
	public byte teamNumber;
	public boolean alive;
	
	public int health;
	
	// Position Components
	public float x, y;
	public float vx, vy;
	public float ax, ay;
	// Position Vector components
	public Vector2 position;
	public Vector2 velocity; 
	public boolean velocity_is_overriden; public Vector2 overriden_velocity;
	public Vector2 acceleration;
	public boolean acceleration_is_overriden; public Vector2 overriden_acceleration;
	public Vector2 acceleration_collector;
	// Position Vector augends
	public ArrayList<Vector2> velocity_augends;
	public ArrayList<Vector2> acceleration_augends;
	// Position limits
	public float max_velocity;
	public float max_acceleration;
	
	// Hitbox
	public Rectangle hitbox;
	
	// Sprite components	damn how are we gonna get animations??? :(
	public TextureRegion defaultTextureRegion;
	public Texture defaultTexture;
	public Sprite defaultSprite;
	
	public Texture[] imageSheet;
	public TextureRegion[] animationFrames;
	public Animation<TextureRegion> animation;
	public Sprite currentSprite;
	
	public BasicUnit() {
		name = "Default";
		startingPosition();
		velocity = new Vector2(); velocity_is_overriden = false;
		acceleration = new Vector2(); acceleration_is_overriden = false;
		velocity_augends = new ArrayList<Vector2>();
		acceleration_augends = new ArrayList<Vector2>();
		acceleration_collector = new Vector2();
		max_velocity = 5;
		max_acceleration = 1;
		animationSetup();
	}
	
	public void startingPosition() {
		position = new Vector2(x , y);
	}
	
	public void animationSetup() {
		imageSheet = new Texture[2];
		imageSheet[0] = new Texture("unitAssets/defaultTexture01.png");
		imageSheet[1] = new Texture("unitAssets/defaultTexture02.png");
		
		animationFrames = new TextureRegion[2];
		animationFrames[0] = new TextureRegion(imageSheet[0]);
		animationFrames[1] = new TextureRegion(imageSheet[1]);
		
		animation = new Animation<TextureRegion>(1f, animationFrames);
		
		// just show the god damn sprite
		currentSprite = new Sprite(animationFrames[0]);
	}
	
	public boolean hasRun = false;
	public void update() {
		if(!hasRun) {System.out.println(name + " update has been reached"); hasRun = true;}
		// clamp velocities
		Vector2 totalVelocities = new Vector2(), totalAccelerations = new Vector2();
		if (!velocity_is_overriden) {
			for (Vector2 vector : velocity_augends) totalVelocities.add(vector);
		}
		
		if (!acceleration_is_overriden) {
			for (Vector2 vector : acceleration_augends) totalAccelerations.add(vector);
			acceleration_collector.add(totalAccelerations);
		}
		totalVelocities.add(acceleration_collector);
		totalVelocities.clamp(-max_velocity, max_velocity);
		totalAccelerations.clamp(-max_acceleration, max_acceleration);
		
		if (!acceleration_is_overriden) { 
			ax = totalAccelerations.x;
			ay = totalAccelerations.y;
		} else {
			ax = overriden_acceleration.x;
			ay = overriden_acceleration.y;
		}
		
		if (!velocity_is_overriden) {
			vx = totalVelocities.x + ax;
			vy = totalVelocities.y + ay;
		} else {
			vx = overriden_velocity.x;
			vy = overriden_velocity.y;
		}
		
		x += vx;
		y += vy;
		
		x = MathUtils.clamp(x, 0, BattlingScreen.battlefieldWidth - currentSprite.getWidth()); // for 0, is working, but for the right end, not working
		y = MathUtils.clamp(y, 0, BattlingScreen.battlefieldHeight - currentSprite.getHeight()); // this is working
	}
	
}
