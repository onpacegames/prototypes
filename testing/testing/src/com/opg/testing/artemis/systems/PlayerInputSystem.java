package com.opg.testing.artemis.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.opg.testing.Testing.Constants;
import com.opg.testing.artemis.components.Player;
import com.opg.testing.artemis.components.Position;

// TODO a better way to control the player - finger touching is a bit janky
public class PlayerInputSystem extends EntityProcessingSystem implements InputProcessor {
	@Mapper protected ComponentMapper<Position> pm;
	
	protected OrthographicCamera camera;
	protected Vector3 touchVector = new Vector3();
	
	protected boolean touchDown = false;
	protected boolean touchUp = false;
	protected boolean attachedToTouch = false;
	
	@SuppressWarnings("unchecked")
	public PlayerInputSystem(OrthographicCamera camera) {
		super(Aspect.getAspectForAll(Player.class, Position.class));
		
		this.camera = camera;
	}
	
	@Override
	protected void initialize() {
		// TODO handle input better - wrapper or management class of some kind?
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		touchDown = true;
		
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		touchUp = true;
		
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

	@Override
	protected void process(Entity e) {
		Position position = pm.get(e);
		
		touchVector.set(Gdx.input.getX(), Gdx.input.getY(), 0);
		camera.unproject(touchVector);
		
		// Don't let the player teleport around if he is on android and teleporting is not enabled
		if (!Constants.Game.ALLOW_PLAYER_TELEPORTING && Gdx.app.getType() == ApplicationType.Android) {
			if (touchDown) {
				touchDown = false;
				
				if (Math.abs(Vector3.dst(touchVector.x, touchVector.y, 0, position.x, position.y, 0)) < Constants.Game.TELEPORT_BUFFER_DISTANCE) {
					attachedToTouch = true;
				}
			}
			
			if (touchUp) {
				touchUp = false;
				attachedToTouch = false;
			}
		} else {
			attachedToTouch = true;
		}
		
		if (attachedToTouch) {
			position.x = touchVector.x;
			position.y = touchVector.y;
		}
	}
}