package com.opg.testing.screens;

import com.artemis.World;
import com.artemis.managers.GroupManager;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.opg.testing.artemis.EntityFactory;
import com.opg.testing.artemis.systems.CollisionSystem;
import com.opg.testing.artemis.systems.HudRenderSystem;
import com.opg.testing.artemis.systems.MonsterSpawnSystem;
import com.opg.testing.artemis.systems.MovementSystem;
import com.opg.testing.artemis.systems.PlayerInputSystem;
import com.opg.testing.artemis.systems.RemoveOffScreenMonstersSystem;
import com.opg.testing.artemis.systems.SpriteRenderSystem;

public class TestingGameScreen extends TestingScreen {
	protected World world;
	
	protected SpriteRenderSystem spriteRenderSystem;
	protected HudRenderSystem hudRenderSystem;
	
	public TestingGameScreen(Game game, float width, float height) {
		super(game, width, height);
		
		world = new World();
		
		world.setManager(new GroupManager());
		
		world.setSystem(new MovementSystem());
		world.setSystem(new PlayerInputSystem(camera));
		world.setSystem(new RemoveOffScreenMonstersSystem(camera));
		world.setSystem(new MonsterSpawnSystem(camera));
		world.setSystem(new CollisionSystem(game));
		
		spriteRenderSystem = world.setSystem(new SpriteRenderSystem(camera));
		hudRenderSystem = world.setSystem(new HudRenderSystem(camera));
		
		world.initialize();
		
		EntityFactory.createPlayer(world, camera.viewportWidth / 2f, camera.viewportHeight / 2f).addToWorld();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			Gdx.app.exit();
		}
		
		camera.update();
		
		world.setDelta(delta);
		world.process();
		
		spriteRenderSystem.process();
		hudRenderSystem.process();
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
	}
}