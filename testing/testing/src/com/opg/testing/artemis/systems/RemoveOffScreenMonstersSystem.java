package com.opg.testing.artemis.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.opg.testing.artemis.components.Monster;
import com.opg.testing.artemis.components.Position;
import com.opg.testing.artemis.components.Size;

public class RemoveOffScreenMonstersSystem extends EntityProcessingSystem {
	@Mapper protected ComponentMapper<Position> pm;
	@Mapper protected ComponentMapper<Size> sm;
	
	protected OrthographicCamera camera;
	
	@SuppressWarnings("unchecked")
	public RemoveOffScreenMonstersSystem(OrthographicCamera camera) {
		super(Aspect.getAspectForAll(Monster.class, Position.class, Size.class));
		
		this.camera = camera;
	}

	@Override
	protected void process(Entity e) {
		Position position = pm.get(e);
		Size size = sm.get(e);
		
		if (position.x + size.width < 0 ||
			position.x - size.width > camera.viewportWidth ||
			position.y + size.height < 0 ||
			position.y - size.height > camera.viewportHeight) {
			e.deleteFromWorld();
		}
	}
}