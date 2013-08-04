package com.opg.testing.artemis;

import com.artemis.Entity;
import com.artemis.World;
import com.artemis.managers.GroupManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.opg.testing.Testing.Constants;
import com.opg.testing.artemis.components.Bounds;
import com.opg.testing.artemis.components.Health;
import com.opg.testing.artemis.components.Monster;
import com.opg.testing.artemis.components.Player;
import com.opg.testing.artemis.components.Position;
import com.opg.testing.artemis.components.Size;
import com.opg.testing.artemis.components.Sprite;
import com.opg.testing.artemis.components.Sprite.Layer;
import com.opg.testing.artemis.components.Velocity;
import com.opg.testing.core.Assets;

public class EntityFactory {
	public static Entity createPlayer(World world, float x, float y) {
		Entity e = world.createEntity();
		
		world.getManager(GroupManager.class).add(e, Constants.Groups.PLAYER);
		
		e.addComponent(new Position(x, y));
		
		Size size = new Size(Constants.Game.PLAYER_SIZE);
		e.addComponent(size);
		
		Sprite sprite = new Sprite(Assets.PARTICLE);
		sprite.r = 1;
		sprite.g = 1;
		sprite.b = 1;
		sprite.a = 1;
		sprite.scaleX = size.width;
		sprite.scaleY = size.height;
		sprite.rotation = 0;
		sprite.layer = Layer.ACTORS_1;
		e.addComponent(sprite);
		
		e.addComponent(new Bounds(size.width, size.height));
		
		e.addComponent(new Health(1));
		
		e.addComponent(new Player());
		
		return e;
	}
	
	// TODO pooling
	public static Entity createMonster(World world, OrthographicCamera camera) {
		Entity e = world.createEntity();
		
		world.getManager(GroupManager.class).add(e, Constants.Groups.MONSTER);
		
		// Which direction will the monster come from?
		int direction = MathUtils.random(3);
		
		// Calculate a randomized start and end point for the monster. The monster will start in 'direction' and
		// will cross the screen to the opposite side.
		
		Size size = new Size(MathUtils.random(Constants.Game.MONSTER_SIZE_MIN, Constants.Game.MONSTER_SIZE_MAX));
		e.addComponent(size);
		
		float halfSizeX = size.width / 2f;
		float halfSizeY = size.height / 2f;
		
		Position startPosition = new Position();
		Position endPosition = new Position();
		switch (direction) {
		// North:
		case 0:
			// TODO position.x is the same for north and south, what is the best way to do this? I don't like this repeated code, same for east and west below
			startPosition.x = MathUtils.random(halfSizeX, camera.viewportWidth - halfSizeX);
			startPosition.y = camera.viewportHeight + halfSizeY;
			
			endPosition.x = MathUtils.random(halfSizeX, camera.viewportWidth - halfSizeX);
			endPosition.y = -halfSizeY;
			break;
			
		// South:
		case 1:
			startPosition.x = MathUtils.random(halfSizeX, camera.viewportWidth - halfSizeX);
			startPosition.y = -halfSizeY;
			
			endPosition.x = MathUtils.random(halfSizeX, camera.viewportWidth - halfSizeX);
			endPosition.y = camera.viewportHeight + halfSizeY;
			break;
			
		// East:
		case 2:
			startPosition.x = camera.viewportWidth + halfSizeX;
			startPosition.y = MathUtils.random(halfSizeY, camera.viewportHeight - halfSizeY);
			
			endPosition.x = -halfSizeX;
			endPosition.y = MathUtils.random(halfSizeY, camera.viewportHeight - halfSizeY);
			break;
			
		// West:
		case 3:
			startPosition.x = -halfSizeX;
			startPosition.y = MathUtils.random(halfSizeY, camera.viewportHeight - halfSizeY);
			
			endPosition.x = camera.viewportWidth + halfSizeX;
			endPosition.y = MathUtils.random(halfSizeY, camera.viewportHeight - halfSizeY);
			break;
			
		default:
			break;
		}
		
		e.addComponent(startPosition);
		
		// Calculate a normalized vector for the direction of movement for the monster, then scale by it's randomized speed.
		float dx = endPosition.x - startPosition.x;
		float dy = endPosition.y - startPosition.y;
		
		float dlength = (float) Math.sqrt(dx * dx + dy * dy);
		dx /= dlength;
		dy /= dlength;
		
		float speed = MathUtils.random(Constants.Game.MONSTER_SPEED_MIN, Constants.Game.MONSTER_SPEED_MAX);
		
		e.addComponent(new Velocity(dx * speed, dy * speed));
		
		Sprite sprite = new Sprite(Assets.PARTICLE);
		sprite.r = 1;
		sprite.g = 0;
		sprite.b = 0;
		sprite.a = 1;
		sprite.scaleX = size.width;
		sprite.scaleY = size.height;
		sprite.rotation = 0;
		sprite.layer = Layer.ACTORS_3;
		e.addComponent(sprite);
		
		e.addComponent(new Bounds(size.width, size.height));
		
		e.addComponent(new Monster());
		
		return e;
	}
}