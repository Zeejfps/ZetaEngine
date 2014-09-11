package com.engine.utils;

public class Vector2 extends Vector<Vector2> {

	public Vector2() {
		super(2, Vector2.class);
	}
	
	public Vector2(float x, float y) {
		this();
		x(x); y(y);
	}
	
	public float x() {
		return components[X];
	}
	
	public float y() {
		return components[Y];
	}
	
	public void x(float value) {
		components[X] = value;
	}
	
	public void y(float value) {
		components[Y] = value;
	}
	
}
