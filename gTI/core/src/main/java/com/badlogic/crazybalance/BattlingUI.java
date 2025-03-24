package com.badlogic.crazybalance;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;

public class BattlingUI extends Stage{
	
	private Skin skin;
	private final TowerDefenseMain game;
	
	// figure out how to link summoning units to here...
	// maybe it's done in the entity system instead?
//	private UnitsEngine engine;
	
	private Viewport viewport;
	
	public Table root, uiElements, unitElements, abilityElements;
	public NinePatchDrawable battleDecisionsBackground;
	
	private float xUiElements, yUiElements;
	private boolean debugOn;
	
	public BattlingUI(Viewport viewport, TowerDefenseMain game) {
		super();
		this.viewport = viewport;
		this.game = game;
		skin = new Skin(Gdx.files.internal("metalui/metal-ui.json"));
		debugOn = true;
		
		addUIElements();
		addActor(root);
	}
	
	public void addUIElements() {
		root = new Table();
		root.setTouchable(Touchable.enabled);
		root.left();
		
		uiElements = new Table();
		uiElements.setTouchable(Touchable.enabled);
		uiElements.left().top();
		uiElements.pad(10f);
		
		NinePatch uiElementsNinePatch =
			new NinePatch(new Texture("battleOptionsMenuRegion.png"), 20, 20, 20, 20);
		NinePatchDrawable uiElementsBackground = new NinePatchDrawable(uiElementsNinePatch);
		uiElements.setBackground(uiElementsBackground);
		
		battleDecisionsBackground = new NinePatchDrawable(
			new NinePatch(new Texture("battleDecisionsMenuRegion.png"), 5, 5, 5, 5));
		
		root.add(uiElements).growX();
		
		drawAvailableUnits();
		drawAvailableAbilities();
		
		ImageButton settingsButton = new ImageButton(skin);
		uiElements.add(settingsButton).width(settingsButton.getHeight());
		
		uiElements.row();
		
//		drawBaseHealthAndMoney();
//		drawPlayerHealth();
//		drawWaveAndEnemiesValue();
		
		uiElements.row();
		
		
		uiElements.row();
		
		// uiElements.add(base health and money);
		// uiElements.add(big heart icon);
		// uiElements.add(progress bar for health);
		// uiElements.add(wave number and wave percent);
		
		settingsButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				debugOn = !debugOn;
				root.setDebug(debugOn);
				uiElements.setDebug(debugOn);
				unitElements.setDebug(debugOn);
				abilityElements.setDebug(debugOn);
			}
		});
		
		uiElements.add();
		
		root.add();
		
//		unitsLabel.addListener(new ClickListener() {
//			@Override
//			public void clicked(InputEvent event, float x, float y ) {
//				game.setScreen(new ActualCampaignScreen(game));
//				System.out.println("I was clicked!");
//			}
//		});
		
		// This is to leave space for the stuff below 
		root.row();
		root.add().growY();
		
		root.setFillParent(true);
		
		
		uiElements.setDebug(debugOn);
		
		xUiElements = uiElements.getX();
		yUiElements = uiElements.getY();
		
//		System.out.println("UIElements at: " +
//				uiElements.getX() + " " + uiElements.getY());
//		
		
		
		root.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
//				System.out.println("Clicked detected at: " + x + " " + y);
				System.out.println("UIElements at: " +
				uiElements.getX() + " " + uiElements.getY());
			}
		});
		
		root.setDebug(debugOn);
	}
	
private void drawAvailableUnits() {
		
		unitElements = new Table();
		unitElements.setTouchable(Touchable.enabled);
		unitElements.setBackground(battleDecisionsBackground);
		unitElements.left().top();
//		Label unitsLabel = new Label("Units", skin);
//		uiElements.add(unitsLabel).left();
		
		Label unitsLabel = new Label("Units", skin);
		unitElements.add(unitsLabel);
		
		// make array of unit circles that can be summoned
		
		addTableToUiElements(unitElements);
	}
	
	
	private void drawAvailableAbilities() {
		abilityElements = new Table();
		abilityElements.setTouchable(Touchable.enabled);
		abilityElements.setBackground(battleDecisionsBackground);
		abilityElements.left().top();
		
		Label abilityLabel = new Label("Abilities", skin);
		abilityElements.add(abilityLabel);
		
		addTableToUiElements(abilityElements);
	}
	
	private void addTableToUiElements(Table table) {
		// pad(top left bottom right)
		uiElements.add(table).growX().pad(2f, 5f, 15f, 8f);
	}
	
}
