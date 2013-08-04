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

public class TestingGameOverScreen extends TestingScreen {
	protected SpriteBatch batch;
	
	protected BitmapFont font;
	
	public TestingGameOverScreen(Game game, float width, float height) {
		super(game, width, height);
		
		batch = new SpriteBatch();
		
		font = Assets.getFont(Assets.HUD_FONT);
	}

	@Override
	public void render(float delta) {
		// TODO move this to the abstract parent class
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// TODO handle this globally
		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			Gdx.app.exit();
		}
		
		if (Gdx.input.justTouched()) {
			game.setScreen(new TestingTitleScreen(game, Constants.Game.FRAME_WIDTH, Constants.Game.FRAME_HEIGHT));
		}
		
		camera.update();
		
		String gameOverText = "GAME OVER";
		TextBounds gameOverTextBounds = font.getBounds(gameOverText);
		
		batch.setProjectionMatrix(camera.combined);
		
		batch.begin();
		
		batch.setColor(1, 1, 1, 1);
		font.draw(batch, gameOverText, (camera.viewportWidth - gameOverTextBounds.width) / 2, (camera.viewportHeight - gameOverTextBounds.height) / 2);
		
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
	}
}