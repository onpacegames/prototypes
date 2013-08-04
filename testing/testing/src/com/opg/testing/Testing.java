package com.opg.testing;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.opg.testing.core.Assets;
import com.opg.testing.screens.TestingTitleScreen;

public class Testing extends Game {
	@Override
	public void create() {
		Gdx.input.setCursorCatched(true);
		
		// TODO should this go here or somewhere else?
		// TODO manage assets on a screen-by-screen basis?
		Assets.loadAssets();
		
		setScreen(new TestingTitleScreen(this, Constants.Game.FRAME_WIDTH, Constants.Game.FRAME_HEIGHT));
	}
	
	@Override
	public void dispose() {
		// TODO Really no need for this until we manage assets on a screen-by-screen basis.
		Assets.dispose();
	}
	
	public static class Constants {
		public static class Game {
			public static final String TITLE = "Testing";
			
			// TODO handle different screen sizes better
			public static final float ASPECT_RATIO = 10 / 16f;
			public static final int FRAME_WIDTH = 1280;
			public static final int FRAME_HEIGHT = (int) (FRAME_WIDTH * ASPECT_RATIO);
			
			public static final float PLAYER_SIZE = 100f;
			
			public static final float MONSTER_SIZE_MIN = 25f;
			public static final float MONSTER_SIZE_MAX = 175f;
			
			public static final float MONSTER_SPEED_MIN = 25f;
			public static final float MONSTER_SPEED_MAX = 100f;
			
			public static final boolean ALLOW_PLAYER_TELEPORTING = false;
			public static final float TELEPORT_BUFFER_DISTANCE = PLAYER_SIZE / 3f;
		}
		
		public static class Groups {
			public static final String PLAYER = "player";
			public static final String MONSTER = "monster";
		}
	}
}