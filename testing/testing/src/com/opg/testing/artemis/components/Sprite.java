package com.opg.testing.artemis.components;

import com.artemis.Component;

// TODO add animation
public class Sprite extends Component {
	public enum Layer {
		DEFAULT,
		BACKGROUND,
		ACTORS_1,
		ACTORS_2,
		ACTORS_3,
		PARTICLES;

		public int getLayerId() {
			return ordinal();
		}
	}
	public String name;
	public float r, g, b, a;
	public float scaleX, scaleY;
	public float rotation;
	public Layer layer;
	
	public Sprite() {
		this("default");
	}
	
	public Sprite(String name) {
		this.name = name;
		
		r = 1;
		g = 1;
		b = 1;
		a = 1;
		
		scaleX = 1;
		scaleY = 1;
		
		rotation = 0;
		
		layer = Layer.DEFAULT;
	}
}