package com.opg.testing.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ObjectMap;

// TODO look at generated bitmap fonts
// TODO clean this up - assertions, checking, etc.
public class Assets {
	// Textures:
	public static final String HUD_FONT_TEXTURE = "hud font texture";
	
	// Fonts:
	public static final String HUD_FONT = "hud font";
	
	// Atlases:
	public static final String SPRITE_ATLAS = "sprite atlas";
	
	// Atlas Regions:
	public static final String PARTICLE = "particle";
	
	// TODO ok to use ObjectMap here?
	private static ObjectMap<String, Texture> textureAssets = new ObjectMap<String, Texture>();
	private static ObjectMap<String, BitmapFont> fontAssets = new ObjectMap<String, BitmapFont>();
	private static ObjectMap<String, TextureAtlas> atlasAssets = new ObjectMap<String, TextureAtlas>();
	private static ObjectMap<String, ObjectMap<String, AtlasRegion>> atlasRegionAssets = new ObjectMap<String, ObjectMap<String, AtlasRegion>>();
	
	public static Texture getTexture(String name) {
		return textureAssets.get(name);
	}
	
	public static BitmapFont getFont(String name) {
		return fontAssets.get(name);
	}
	
	public static TextureAtlas getAtlas(String name) {
		return atlasAssets.get(name);
	}
	
	public static ObjectMap<String, AtlasRegion> getAtlasRegionMap(String atlasName) {
		return atlasRegionAssets.get(atlasName);
	}
	
	public static AtlasRegion getAtlasRegion(String atlasName, String regionName) {
		return atlasRegionAssets.get(atlasName).get(regionName);
	}
	
	public static void loadAssets() {
		loadHudFont();
		loadSpriteAtlas();
	}
	
	// TODO use a better font?
	private static void loadHudFont() {
		// TODO should we store the font texture as well?
		// TODO what happens if we dispose the stuff here?
		Texture texture = new Texture(Gdx.files.internal("fonts/normal_0.png"));
		texture.setFilter(TextureFilter.Linear, TextureFilter.MipMapLinearLinear);
		TextureRegion fontRegion = new TextureRegion(texture);
		BitmapFont font = new BitmapFont(Gdx.files.internal("fonts/normal.fnt"), fontRegion, false);
		font.setUseIntegerPositions(false);
		
		textureAssets.put(HUD_FONT_TEXTURE, texture);
		fontAssets.put(HUD_FONT, font);
	}
	
	private static void loadSpriteAtlas() {
		TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("textures/textures.atlas"), Gdx.files.internal("textures"));
		
		ObjectMap<String, AtlasRegion> atlasRegionMap = new ObjectMap<String, AtlasRegion>();
		for (AtlasRegion region : atlas.getRegions()) {
			atlasRegionMap.put(region.name, region);
		}
		// TODO dispose the old one if it exists?
		atlasRegionAssets.put(SPRITE_ATLAS, atlasRegionMap);
		
		atlasAssets.put(SPRITE_ATLAS, atlas);
	}
	
	public static void dispose() {
		for (Texture texture : textureAssets.values()) {
			texture.dispose();
		}
		textureAssets.clear();
		
		for (BitmapFont font : fontAssets.values()) {
			font.dispose();
		}
		fontAssets.clear();
		
		for (TextureAtlas atlas : atlasAssets.values()) {
			atlas.dispose();
		}
		atlasAssets.clear();
		
		// TODO what happens if I dispose the atlas regions before/after the atlas?
	}
}