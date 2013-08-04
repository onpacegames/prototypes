package com.opg.testing.artemis.components;

import com.artemis.Component;

public class Bounds extends Component {
	public float width, height;
	
	public Bounds(float size) {
		this(size, size);
	}
	
	public Bounds(float width, float height) {
		this.width = width;
		this.height = height;
	}
}