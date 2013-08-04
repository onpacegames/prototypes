package com.opg.testing.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;

public abstract class TestingScreen implements Screen {
	protected Game game;
	
	protected OrthographicCamera camera;
	
	public TestingScreen(Game game, float width, float height) {
		this.game = game;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, width, height);
	}
}