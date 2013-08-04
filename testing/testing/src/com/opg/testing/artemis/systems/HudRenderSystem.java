package com.opg.testing.artemis.systems;

import com.artemis.Aspect;
import com.artemis.Entity;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.opg.testing.core.Assets;

// TODO separate render system for FPS?
public class HudRenderSystem extends RenderSystem {
	public HudRenderSystem(OrthographicCamera camera) {
		super(Aspect.getEmpty(), camera);
	}

	protected BitmapFont font;
	
	@Override
	protected void initialize() {
		super.initialize();
		
		font = Assets.getFont(Assets.HUD_FONT);
	}

	@Override
	protected void processEntities(ImmutableBag<Entity> entities) {
		processSystem();
	}
	
	protected void processSystem() {
		batch.setColor(1, 1, 1, 1);
		font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 20, camera.viewportHeight - 20);
		font.draw(batch, "Active entities: " + world.getEntityManager().getActiveEntityCount(), 20, camera.viewportHeight - 40);
		
		// TODO Add this in if necessary
		/*font.draw(batch, "Total created: " + world.getEntityManager().getTotalCreated(), -(SpaceshipWarrior.FRAME_WIDTH / 2) + 20, SpaceshipWarrior.FRAME_HEIGHT / 2 - 60);
		font.draw(batch, "Total deleted: " + world.getEntityManager().getTotalDeleted(), -(SpaceshipWarrior.FRAME_WIDTH / 2) + 20, SpaceshipWarrior.FRAME_HEIGHT / 2 - 80);*/
	}
}