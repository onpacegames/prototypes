package com.opg.testing.artemis.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.opg.testing.artemis.components.Position;
import com.opg.testing.artemis.components.Velocity;

public class MovementSystem extends EntityProcessingSystem {
	@Mapper protected ComponentMapper<Position> pm;
	@Mapper protected ComponentMapper<Velocity> vm;
	
	@SuppressWarnings("unchecked")
	public MovementSystem() {
		super(Aspect.getAspectForAll(Position.class, Velocity.class));
	}

	@Override
	protected void process(Entity e) {
		Position position = pm.get(e);
		Velocity velocity = vm.get(e);
		
		position.x += velocity.vx * world.getDelta();
		position.y += velocity.vy * world.getDelta();
	}
}