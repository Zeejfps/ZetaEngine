package envasilyev.zetaengine.comps;


import envasilyev.zetaengine.math.Vec2f;

public class Transform {

	public final Vec2f position;
	public final Vec2f forward;
	private float rotation;
	
	public Transform(Vec2f position, Vec2f forward, float rotation) {
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
		
		float x = forward.x * cosA - forward.y * sinA;
		float y = forward.y * cosA + forward.x * sinA;
		
		forward.x = x;
		forward.y = y;
		
		this.rotation += angle;
	}
	
	public void translate(Vec2f vec) {
		position.add(vec);
	}
	
	public float getRotation() {
		return rotation;
	}
	
}
