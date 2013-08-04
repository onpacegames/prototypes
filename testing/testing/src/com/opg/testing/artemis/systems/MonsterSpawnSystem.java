package com.opg.testing.artemis.systems;

import com.artemis.systems.VoidEntitySystem;
import com.artemis.utils.Timer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.opg.testing.artemis.EntityFactory;

public class MonsterSpawnSystem extends VoidEntitySystem {
	protected OrthographicCamera camera;
	
	protected Timer spawnTimer;
	
	public MonsterSpawnSystem(OrthographicCamera camera) {
		this.camera = camera;
	}
	
	@Override
	protected void initialize() {
		spawnTimer = new Timer(1, true) {
			@Override
			public void execute() {
				EntityFactory.createMonster(world, camera).addToWorld();
			}
		};
	}
	
	@Override
	protected void processSystem() {
		spawnTimer.update(world.getDelta());
	}
}