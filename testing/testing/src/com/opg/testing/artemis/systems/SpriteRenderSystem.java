package com.opg.testing.artemis.systems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.utils.Bag;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.ObjectMap;
import com.opg.testing.artemis.components.Position;
import com.opg.testing.artemis.components.Sprite;
import com.opg.testing.core.Assets;
import com.opg.testing.utils.GdxRenderUtils;

// TODO Add in animation capabilities
public class SpriteRenderSystem extends RenderSystem {
	@Mapper protected ComponentMapper<Sprite> sm;
	@Mapper protected ComponentMapper<Position> pm;
	
	protected ObjectMap<String, AtlasRegion> regions;

	protected Bag<AtlasRegion> regionsByEntity;
	protected List<Entity> sortedEntites;
	
	@SuppressWarnings("unchecked")
	public SpriteRenderSystem(OrthographicCamera camera) {
		super(Aspect.getAspectForAll(Sprite.class, Position.class), camera);
	}
	
	@Override
	protected void initialize() {
		super.initialize();
		
		regions = Assets.getAtlasRegionMap(Assets.SPRITE_ATLAS);
		
		regionsByEntity = new Bag<AtlasRegion>();

		sortedEntites = new ArrayList<Entity>();
	}

	@Override
	protected void processEntities(ImmutableBag<Entity> entities) {
		for (int i = 0; i < sortedEntites.size(); i++) {
			process(sortedEntites.get(i));
		}
	}
	
	protected void process(Entity e) {
		Position position = pm.get(e);
		Sprite sprite = sm.get(e);

		AtlasRegion spriteRegion = regionsByEntity.get(e.getId());
		batch.setColor(sprite.r, sprite.g, sprite.b, sprite.a);

		GdxRenderUtils.drawCentered(batch, spriteRegion, position.x, position.y, sprite.scaleX, sprite.scaleY, sprite.rotation);
	}
	
	@Override
	protected void inserted(Entity e) {
		Sprite sprite = sm.get(e);
		regionsByEntity.set(e.getId(), regions.get(sprite.name));

		sortedEntites.add(e);

		Collections.sort(sortedEntites, new SpriteByLayerComparator());
	}

	@Override
	protected void removed(Entity e) {
		regionsByEntity.set(e.getId(), null);
		sortedEntites.remove(e);
	}
	
	public class SpriteByLayerComparator implements Comparator<Entity> {
		@Override
		public int compare(Entity e1, Entity e2) {
			Sprite s1 = sm.get(e1);
			Sprite s2 = sm.get(e2);
			return s1.layer.compareTo(s2.layer);
		}
	}
}