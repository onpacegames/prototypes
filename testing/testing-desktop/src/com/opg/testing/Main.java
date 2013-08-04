package com.opg.testing;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.opg.testing.Testing.Constants;

public class Main {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = Constants.Game.TITLE;
		cfg.useGL20 = true;
		cfg.width = Constants.Game.FRAME_WIDTH;
		cfg.height = Constants.Game.FRAME_HEIGHT;
		cfg.resizable = false;
		
		new LwjglApplication(new Testing(), cfg);
	}
}