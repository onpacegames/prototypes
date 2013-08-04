package com.opg.testing.artemis.components;

import com.artemis.Component;

public class Health extends Component {
	public float health, maxHealth;
	
	public Health(float health) {
		this(health, health);
	}
	
	public Health(float health, float maxHealth) {
		this.health = health;
		this.maxHealth = maxHealth;
	}
}