package com.opg.testing;

import java.io.File;

import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2.Settings;

public class TexturePacker {
	public static void main(String[] args) {
		pack();
	}
	
	public static void pack(){
		File workingDirectory = new File(System.getProperty("user.dir"));
		
		File projectRoot = workingDirectory.getName().equals("bin") ? workingDirectory.getParentFile() : workingDirectory;
		String androidRoot = projectRoot.getParentFile().getPath() + "/testing-android";
		
		Settings settings = new Settings();
		settings.paddingX = 2;
		settings.paddingY = 2;
		settings.stripWhitespaceX = true;
		settings.stripWhitespaceY = true;
		settings.minHeight = 1024;
		settings.minWidth = 1024;
		settings.filterMin = TextureFilter.Linear;
		settings.filterMag = TextureFilter.Linear;
		
		TexturePacker2.process(
			settings,
			androidRoot + "/assets/textures-unpacked",
			androidRoot + "/assets/textures",
			"textures");
	}
}