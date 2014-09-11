package com.engine.utils;

public class Vector3 extends Vector<Vector3> {

	public Vector3() {
		super(3, Vector3.class);
	}
	
	public Vector3(float x, float y, float z) {
		this();
		x(x); y(y); z(z);
	}
	
	public float x() {
		return components[X];
	}
	
	public float y() {
		return components[Y];
	}
	
	public float z() {
		return components[Z];
	}
	
	public void x(float value) {
		components[X] = value;
	}
	
	public void y(float value) {
		components[Y] = value;
	}
	
	public void z(float value) {
		components[Z] = value;
	}
	
	public Vector3 cross(Vector3 vec) {
		
		float x = y()*vec.z() - z()*vec.y();
		float y = z()*vec.x() - x()*vec.z();
		float z = x()*vec.y() - y()*vec.x();
		
		return new Vector3(x, y, z);
	}

}
