/*  Copyright (C) 2013 by Jan-Christoph Klie, Inc. All rights reserved.
 *  Released under the terms of the GNU General Public License version 3 or later.
 *  
 *  Contributors:
 *  Jan-Christoph Klie - First, ugly and very basic version
 *  Benedict Holste - First fancy version, all other things
 */

package de.dhbw.td.core.game;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;
import static playn.core.PlayN.log;

import java.util.ArrayList;

import playn.core.Canvas;
import playn.core.CanvasImage;
import playn.core.Font;
import playn.core.Image;
import playn.core.Keyboard.TypedEvent;
import playn.core.Mouse.ButtonEvent;
import playn.core.util.Callback;
import playn.core.Surface;
import playn.core.TextFormat;
import playn.core.TextLayout;
import de.dhbw.td.core.TowerDefense;
import de.dhbw.td.core.event.ICallbackFunction;
import de.dhbw.td.core.event.IKeyboardObserver;
import de.dhbw.td.core.event.IMouseObserver;

/**
 * Jan-Christoph Klie - <jcklie@de.ibm.com>
 * @author Benedict Holste <benedict@bholste.net>
 *
 */
public class HUD implements IDrawable, IMouseObserver, IKeyboardObserver {
	
	private static final float fontSize = 32f;
	
	private static final int TILE_SIZE = 64;
	
	private final int OFFSET_HEAD = 0;
	private final int OFFSET_FOOT = 9;
	
	private final int OFFSET_IMAGE_CLOCK 	= 0;
	private final int OFFSET_IMAGE_HEART 	= 7;
	private final int OFFSET_IMAGE_CREDITS 	= 9;
	private final int OFFSET_IMAGE_COG	 	= 13;
	private final int OFFSET_IMAGE_PAUSE	= 0;
	private final int OFFSET_IMAGE_PLAY	= 1;
	
	private final int OFFSET_IMAGE_MATH 	= 8;
	private final int OFFSET_IMAGE_CODE 	= 9;
	private final int OFFSET_IMAGE_WIWI 	= 10;
	private final int OFFSET_IMAGE_SOCIAL = 11;
	private final int OFFSET_IMAGE_TECHINF = 12;
	private final int OFFSET_IMAGE_THEOINF 	= 13;
	
	private final int OFFSET_TEXT_CREDITS = 10;
	private final int OFFSET_TEXT_CLOCK = 1;
	private final int OFFSET_TEXT_HEART = 8;
	
	private CanvasImage canvasImage;
	private Canvas canvas;
	private TextFormat textFormat;
	
	private Image clockImage;
	private Image heartImage;
	private Image cogImage;
	private Image creditsImage;
	private Image pauseImage;
	private Image playImage;
	
	private Image mathTowerImage;
	private Image codeTowerImage;
	private Image socialTowerImage;
	private Image wiwiTowerImage;
	private Image techinfTowerImage;
	private Image theoinfTowerImage;
	
	private GameState stateOfTheWorld;
	
	private ArrayList<Button> buttons;
	
	/**
	 * 
	 * @param state
	 */
	public HUD(GameState state) {
		
		stateOfTheWorld = state;
		
		buttons = new ArrayList<Button>();
		createButtons();
		
		canvasImage = graphics().createImage( graphics().width(), graphics().height() );
		canvas = canvasImage.canvas();
		
		Font miso = graphics().createFont("Miso", Font.Style.PLAIN, fontSize);
		textFormat = new TextFormat().withFont(miso);
		
		// load HUD images
		clockImage = assets().getImageSync(TowerDefense.PATH_IMAGES + "clock.bmp");
		heartImage = assets().getImageSync(TowerDefense.PATH_IMAGES + "heart.bmp");
		//cogImage = assets().getImageSync(TowerDefense.PATH_IMAGES + "cog.bmp");
		creditsImage= assets().getImageSync(TowerDefense.PATH_IMAGES + "credits.bmp");
		pauseImage= assets().getImageSync(TowerDefense.PATH_IMAGES + "pause.png");
		playImage= assets().getImageSync(TowerDefense.PATH_IMAGES + "play.png");
		
		// load tower images
		//mathTowerImage = assets().getImageSync(TowerDefense.PATH_TOWERS + "math.png");
		//codeTowerImage = assets().getImageSync(TowerDefense.PATH_TOWERS + "code.png");
		//wiwiTowerImage = assets().getImageSync(TowerDefense.PATH_TOWERS + "wiwi.png");
//		socialTowerImage = assets().getImageSync(TowerDefense.PATH_TOWERS + "social.png");
//		techinfTowerImage = assets().getImageSync(TowerDefense.PATH_TOWERS + "techinf.png");
//		theoinfTowerImage = assets().getImageSync(TowerDefense.PATH_TOWERS + "theoinf.png");
		
		
	}
	
	private void createButtons() {
		
		Button cog = new Button(OFFSET_IMAGE_COG*TILE_SIZE, OFFSET_HEAD, TILE_SIZE, TILE_SIZE,
				TowerDefense.PATH_IMAGES + "cog.bmp", new ICallbackFunction() {
					
					@Override
					public void execute() {
						log().debug("Clicked Settings");
					}
				});
		TowerDefense.getMouse().addObserver(cog);
		buttons.add(cog);
		
		Button mathTower = new Button(OFFSET_IMAGE_MATH*TILE_SIZE, OFFSET_FOOT*TILE_SIZE, TILE_SIZE, TILE_SIZE,
				TowerDefense.PATH_TOWERS + "math.png", new ICallbackFunction() {
					
					@Override
					public void execute() {
						log().debug("Clicked MathTower");
					}
				});
		TowerDefense.getMouse().addObserver(mathTower);
		buttons.add(mathTower);
		
		Button codeTower = new Button(OFFSET_IMAGE_CODE*TILE_SIZE, OFFSET_FOOT*TILE_SIZE, TILE_SIZE, TILE_SIZE,
				TowerDefense.PATH_TOWERS + "code.png", new ICallbackFunction() {
					
					@Override
					public void execute() {
						log().debug("Clicked CodeTower");
					}
				});
		TowerDefense.getMouse().addObserver(codeTower);
		buttons.add(codeTower);
		
		Button wiwiTower = new Button(OFFSET_IMAGE_WIWI*TILE_SIZE, OFFSET_FOOT*TILE_SIZE, TILE_SIZE, TILE_SIZE,
				TowerDefense.PATH_TOWERS + "wiwi.png", new ICallbackFunction() {
					
					@Override
					public void execute() {
						log().debug("Clicked WiwiTower");
					}
				});
		TowerDefense.getMouse().addObserver(wiwiTower);
		buttons.add(wiwiTower);
		
		Button theoinfTower = new Button(OFFSET_IMAGE_THEOINF*TILE_SIZE, OFFSET_FOOT*TILE_SIZE, TILE_SIZE, TILE_SIZE,
				TowerDefense.PATH_TOWERS + "theoinf.png", new ICallbackFunction() {
					
					@Override
					public void execute() {
						log().debug("Clicked theoinfTower");
					}
				});
		TowerDefense.getMouse().addObserver(theoinfTower);
		buttons.add(theoinfTower);
		
		Button techinfTower = new Button(OFFSET_IMAGE_TECHINF*TILE_SIZE, OFFSET_FOOT*TILE_SIZE, TILE_SIZE, TILE_SIZE,
				TowerDefense.PATH_TOWERS + "techinf.png", new ICallbackFunction() {
					
					@Override
					public void execute() {
						log().debug("Clicked techinfTower");
					}
				});
		TowerDefense.getMouse().addObserver(techinfTower);
		buttons.add(techinfTower);
		
		Button socialTower = new Button(OFFSET_IMAGE_SOCIAL*TILE_SIZE, OFFSET_FOOT*TILE_SIZE, TILE_SIZE, TILE_SIZE,
				TowerDefense.PATH_TOWERS + "social.png", new ICallbackFunction() {
					
					@Override
					public void execute() {
						log().debug("Clicked socialTower");
					}
				});
		TowerDefense.getMouse().addObserver(socialTower);
		buttons.add(socialTower);
	}

	@Override
	public void draw(Surface surf) {
		
		// check, if world has changed
		if(stateOfTheWorld.hasChanged()) {
			
			// clear the surface
			surf.clear();
			
			canvas.clear();
			
			// draw HUD head
			canvas.drawImage(clockImage, OFFSET_IMAGE_CLOCK*TILE_SIZE, OFFSET_HEAD);
			canvas.drawImage(heartImage, OFFSET_IMAGE_HEART*TILE_SIZE, OFFSET_HEAD);
			canvas.drawImage(creditsImage, OFFSET_IMAGE_CREDITS*TILE_SIZE, OFFSET_HEAD);
			//canvas.drawImage(cogImage, OFFSET_IMAGE_COG*TILE_SIZE, OFFSET_HEAD);
			
			TextLayout clockText = graphics().layoutText(String.format("%s. Semester - %s.Woche", stateOfTheWorld.getLevelCount(),
					stateOfTheWorld.getWaveCount()), textFormat);
			canvas.fillText(clockText, OFFSET_TEXT_CLOCK*TILE_SIZE, 16);
			
			TextLayout healthText = graphics().layoutText(String.valueOf(stateOfTheWorld.getLifepoints()), textFormat);
			canvas.fillText(healthText, OFFSET_TEXT_HEART*TILE_SIZE, 16);
			
			TextLayout creditsText = graphics().layoutText(String.valueOf(stateOfTheWorld.getCredits()), textFormat);
			canvas.fillText(creditsText, OFFSET_TEXT_CREDITS*TILE_SIZE, 16);
			
			// draw HUD foot
			canvas.drawImage(pauseImage, OFFSET_IMAGE_PAUSE*TILE_SIZE, OFFSET_FOOT*TILE_SIZE);
			canvas.drawImage(playImage, OFFSET_IMAGE_PLAY*TILE_SIZE, OFFSET_FOOT*TILE_SIZE);
			
			//canvas.drawImage(mathTowerImage, OFFSET_IMAGE_MATH*TILE_SIZE, OFFSET_FOOT*TILE_SIZE);
			//canvas.drawImage(codeTowerImage, OFFSET_IMAGE_CODE*TILE_SIZE, OFFSET_FOOT*TILE_SIZE);
			//canvas.drawImage(wiwiTowerImage, OFFSET_IMAGE_WIWI*TILE_SIZE, OFFSET_FOOT*TILE_SIZE);
			//canvas.drawImage(socialTowerImage, OFFSET_IMAGE_SOCIAL*TILE_SIZE, OFFSET_FOOT*TILE_SIZE);
			//canvas.drawImage(techinfTowerImage, OFFSET_IMAGE_TECHINF*TILE_SIZE, OFFSET_FOOT*TILE_SIZE);
			//canvas.drawImage(theoinfTowerImage, OFFSET_IMAGE_THEOINF*TILE_SIZE, OFFSET_FOOT*TILE_SIZE);
			
			// draw the canvas onto the surface
			surf.drawImage(canvasImage, 0, 0);
			
			for(Button b : buttons) {
				b.draw(surf);
			}
		}		
	}

	@Override
	public void alert(ButtonEvent e) {
		// TODO Auto-generated method stub
		log().info(this.toString() + " " + e.toString());
	}
	
	@Override
	public void alert(TypedEvent e) {
		// TODO Auto-generated method stub
		log().info(this.toString() + " " + e.toString());
	}
}
