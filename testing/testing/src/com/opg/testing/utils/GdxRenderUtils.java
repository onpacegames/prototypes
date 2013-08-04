package com.opg.testing.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GdxRenderUtils {
	public static final void drawCentered(SpriteBatch batch, TextureRegion region, float x, float y, float scaleX, float scaleY, float rotation) {
		batch.draw(
			region,
			x - region.getRegionWidth() / 2f * scaleX, y - region.getRegionHeight() / 2f * scaleY,
			0, 0,
			region.getRegionWidth(), region.getRegionHeight(),
			scaleX, scaleY,
			rotation);
	}
	public static final void drawCentered(SpriteBatch batch, TextureRegion region, float x, float y) {
		batch.draw(
			region,
			x - region.getRegionWidth() / 2, y - region.getRegionHeight() / 2
		);
	}

	public static final void drawCentered(SpriteBatch batch, TextureRegion region, float x, float y, float rotationInDegrees) {
		batch.draw(
			region,
			x - region.getRegionWidth() / 2, y - region.getRegionHeight() / 2,
			region.getRegionWidth() / 2, region.getRegionHeight() / 2,
			region.getRegionWidth(), region.getRegionHeight(),
			1, 1,
			rotationInDegrees
		);
	}

	/*public static final void drawCentered(SpriteBatch batch, TextureRegion region, float x, float y, float offsetX, float offsetY, float rotationInDegrees) {
		float posX = x - region.getRegionWidth() / 2;
		float posY = y - region.getRegionHeight() / 2;
		float originX = region.getRegionWidth() / 2 + offsetX;
		float originY = region.getRegionWidth() / 2 + offsetY;

		batch.draw(
			region,
			posX, posY,
			originX, originY,
			region.getRegionWidth(), region.getRegionHeight(),
			1, 1,
			rotationInDegrees
		);
	}*/

	public static final void drawCentered(SpriteBatch batch, TextureRegion region, float x, float y, float rotationInDegrees, float scale) {
		batch.draw(
			region,
			x - region.getRegionWidth() / 2, y - region.getRegionHeight() / 2,
			region.getRegionWidth() / 2, region.getRegionHeight() / 2,
			region.getRegionWidth(), region.getRegionHeight(),
			scale, scale,
			rotationInDegrees
		);
	}

	public static final void drawCentered(SpriteBatch batch, Texture texture, float x, float y) {
		batch.draw(
			texture,
			x - texture.getWidth() / 2, y - texture.getHeight() / 2
		);
	}

	public static final void drawCentered(SpriteBatch batch, Texture texture, float x, float y, float rotationInDegrees) {
		batch.draw(
			texture,
			x - texture.getWidth() / 2, y - texture.getHeight() / 2,
			texture.getWidth() / 2, texture.getHeight() / 2,
			texture.getWidth(), texture.getHeight(),
			1, 1,
			rotationInDegrees,
			0, 0,
			texture.getWidth(), texture.getHeight(),
			false, false
		);
	}
}