package com.engine.utils;

public class Transform {

	public final Vector3 position;
	private float rotation;
	
	public Transform(Vector3 position, float rotation) {
		this.position = position;
		this.rotation = rotation;
	}
	
	public void setRotation(float angle) {
		this.rotation = angle;
	}
	
	public void rotate(float angle) {
		this.rotation += angle;
	}
	
	public void translate(Vector3 vec) {
		position.add(vec);
	}
	
	public float getRotation() {
		return rotation;
	}
	
}
