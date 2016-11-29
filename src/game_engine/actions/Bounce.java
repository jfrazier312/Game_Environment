package game_engine.actions;

import game_data.Sprite;

public class Bounce implements Action {
	
	private double myBounceSpeed;
	private Sprite myPlayerSprite;
	

	public Bounce(double bouncerSpeed, Sprite player) {
		myBounceSpeed = bouncerSpeed;
		myPlayerSprite = player;
	}

	@Override
	public void act() {
		
		myPlayerSprite.setMyVelocity((-myPlayerSprite.getMyVelocity()) + myBounceSpeed);
		}

	}


