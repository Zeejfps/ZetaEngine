package snakegame.game;

import com.engine.core.WindowedGame;
import com.engine.graphics.Graphics;

public class TestGame extends WindowedGame {

	private Player player;
	
	public TestGame() {
		super(640, 480, 2, "Test Game");
	
		player = new Player();
		sceneGraph.add(player);
		
	}

	@Override
	protected void update() {
		
	}

	@Override
	protected void render(Graphics g) {
		g.fill(0x313b45);
	}

}
