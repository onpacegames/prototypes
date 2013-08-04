package com.opg.testing.artemis.systems;

import com.artemis.Aspect;
import com.artemis.EntitySystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

// TODO dispose and disposable
public abstract class RenderSystem extends EntitySystem implements Disposable {
	protected OrthographicCamera camera;
	protected SpriteBatch batch;
	
	public RenderSystem(Aspect aspect, OrthographicCamera camera) {
		super(aspect);
		
		this.camera = camera;
	}
	
	@Override
	protected void initialize() {
		batch = new SpriteBatch();
	}
	
	@Override
	protected void begin() {
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
	}

	@Override
	protected void end() {
		batch.end();
	}
	
	@Override
	protected boolean checkProcessing() {
		return true;
	}
	
	@Override
	public void dispose() {
		batch.dispose();
	}
}