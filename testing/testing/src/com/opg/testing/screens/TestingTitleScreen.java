package com.opg.testing.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.opg.testing.Testing.Constants;
import com.opg.testing.core.Assets;

// TODO handle screens with an entity system?
public class TestingTitleScreen extends TestingScreen {
	protected SpriteBatch batch;
	
	protected BitmapFont font;
	
	public TestingTitleScreen(Game game, float width, float height) {
		super(game, width, height);
		
		batch = new SpriteBatch();
		
		font = Assets.getFont(Assets.HUD_FONT);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			Gdx.app.exit();
		}
		
		if (Gdx.input.justTouched()) {
			game.setScreen(new TestingGameScreen(game, Constants.Game.FRAME_WIDTH, Constants.Game.FRAME_HEIGHT));
		}
		
		camera.update();
		
		String titleText = "MONSTAH DAWJAH";
		TextBounds titleTextBounds = font.getBounds(titleText);
		
		batch.setProjectionMatrix(camera.combined);
		
		batch.begin();
		
		batch.setColor(1, 1, 1, 1);
		font.draw(batch, titleText, (camera.viewportWidth - titleTextBounds.width) / 2, (camera.viewportHeight - titleTextBounds.height) / 2);
		
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		batch.dispose();
		font.dispose();
	}
}