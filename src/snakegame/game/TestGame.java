package snakegame.game;

import com.engine.core.WindowedGame;
import com.engine.graphics.Graphics;

public class TestGame extends WindowedGame {

	private Player player;
	
	public TestGame() {
		super(800, 600, 2, "Test Game");
	
		player = new Player();
		sceneGraph.add(player);
		
	}

	@Override
	protected void update() {
		
	}

	@Override
	protected void render(Graphics g) {
		g.fill(0x313b45);
		g.drawString(0, 0, 0x00ff00, "Zerka Engine");
	}

}
