package com.engine.utils;

public class Transform {

	public final Vector3 position;
	public final Vector3 forward;
	private float rotation;
	
	public Transform(Vector3 position, Vector3 forward, float rotation) {
		this.position = position;
		this.forward = forward;
		this.rotation = rotation;
	}
	
	public void setRotation(float angle) {
		this.rotation = angle;
	}
	
	public void rotate(float angle) {
		float rad = (float) Math.toRadians(angle);
		float sinA = (float) Math.sin(rad);
		float cosA = (float) Math.cos(rad);
		
		float x = forward.x() * cosA - forward.y() * sinA;
		float y = forward.y() * cosA + forward.x() * sinA;
		
		forward.x(x);
		forward.y(y);
		
		this.rotation += angle;
	}
	
	public void translate(Vector3 vec) {
		position.add(vec);
	}
	
	public float getRotation() {
		return rotation;
	}
	
}
