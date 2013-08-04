package com.opg.testing.artemis.components;

import com.artemis.Component;

public class Size extends Component {
	public float width, height;
	
	public Size(float size) {
		this(size, size);
	}
	
	public Size(float width, float height) {
		this.width = width;
		this.height = height;
	}
}