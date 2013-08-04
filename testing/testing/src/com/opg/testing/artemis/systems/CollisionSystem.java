package com.opg.testing.artemis.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Mapper;
import com.artemis.managers.GroupManager;
import com.artemis.utils.Bag;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.opg.testing.Testing.Constants;
import com.opg.testing.artemis.components.Bounds;
import com.opg.testing.artemis.components.Health;
import com.opg.testing.artemis.components.Position;
import com.opg.testing.artemis.components.Size;
import com.opg.testing.artemis.components.Sprite;
import com.opg.testing.screens.TestingGameOverScreen;

// TODO better way to handle these collisions?
public class CollisionSystem extends EntitySystem {
	@Mapper protected ComponentMapper<Position> pm;
	@Mapper protected ComponentMapper<Bounds> bm;
	@Mapper protected ComponentMapper<Health> hm;
	@Mapper protected ComponentMapper<Sprite> sm;
	@Mapper protected ComponentMapper<Size> szm;
	
	protected Game game;
	
	protected Bag<CollisionPair> collisionPairs;
	
	@SuppressWarnings("unchecked")
	public CollisionSystem(Game game) {
		super(Aspect.getAspectForAll(Position.class, Bounds.class, Size.class));
		
		this.game = game;
	}
	
	@Override
	protected void initialize() {
		collisionPairs = new Bag<CollisionPair>();
		
		// TODO move this to it's own class to make THIS class more generic
		collisionPairs.add(new CollisionPair(Constants.Groups.PLAYER, Constants.Groups.MONSTER, new CollisionHandler() {
			@Override
			public void handleCollision(Entity player, Entity monster) {
				Health playerHealth = hm.get(player);
				playerHealth.health -= 1;
				
				if (playerHealth.health <= 0) {
					player.deleteFromWorld();
					
					if (sm.has(monster)) {
						Sprite monsterSprite = sm.get(monster);
						monsterSprite.r = 0;
						monsterSprite.g = 0;
						monsterSprite.b = 1;
						monsterSprite.a = 1;
					}
					
					Task gameOverScreenSwitchTask = new Task() {
						@Override
						public void run() {
							// TODO better way to handle the passing back and forth of the width and height params?
							game.setScreen(new TestingGameOverScreen(game, Constants.Game.FRAME_WIDTH, Constants.Game.FRAME_HEIGHT));
						}
					};
					
					Timer gameOverScreenTimer = new Timer();
					gameOverScreenTimer.scheduleTask(gameOverScreenSwitchTask, 2);
				}
			}
		}));
	}

	@Override
	protected void processEntities(ImmutableBag<Entity> entities) {
		for (int i = 0; i < collisionPairs.size(); i++) {
			collisionPairs.get(i).checkForCollisions();
		}
	}

	@Override
	protected boolean checkProcessing() {
		return true;
	}

	protected class CollisionPair {
		protected ImmutableBag<Entity> groupEntitiesA;
		protected ImmutableBag<Entity> groupEntitiesB;
		protected CollisionHandler handler;
		
		public CollisionPair(String group1, String group2, CollisionHandler handler) {
			groupEntitiesA = world.getManager(GroupManager.class).getEntities(group1);
			groupEntitiesB = world.getManager(GroupManager.class).getEntities(group2);
			this.handler = handler;
		}
		
		public void checkForCollisions() {
			for (int a = 0; a < groupEntitiesA.size(); a++) {
				Entity entityA = groupEntitiesA.get(a);
				for (int b = 0; b < groupEntitiesB.size(); b++) {
					Entity entityB = groupEntitiesB.get(b);
					if (collisionExists(entityA, entityB)) {
						handler.handleCollision(entityA, entityB);
					}
				}
			}
		}
		
		// TODO put these outside the inner class?
		protected Rectangle r1 = new Rectangle();
		protected Rectangle r2 = new Rectangle();
		protected boolean collisionExists(Entity e1, Entity e2) {
			// TODO the disconnect between position size and bounds is a bit confusing/not well flushed out
			Position p1 = pm.get(e1);
			Position p2 = pm.get(e2);
			
			Bounds b1 = bm.get(e1);
			Bounds b2 = bm.get(e2);
			
			Size s1 = szm.get(e1);
			Size s2 = szm.get(e2);
			
			r1.set(p1.x - s1.width / 2, p1.y - s1.height / 2, b1.width, b1.height);
			r2.set(p2.x - s2.width / 2, p2.y - s2.height / 2, b2.width, b2.height);
			
			return Intersector.overlaps(r1, r2);
		}
	}
	
	protected interface CollisionHandler {
		public void handleCollision(Entity a, Entity b);
	}
}