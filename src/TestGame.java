import java.awt.event.KeyEvent;

import com.zeta.engine.Input;
import com.zeta.engine.WindowedGame;
import com.zeta.engine.graphics.RenderContext;
import com.zeta.engine.graphics.Texture;

public class TestGame extends WindowedGame {
	
	public static final int WIDTH = 800, HEIGHT = 600, SCALE = 2;
	public static final String TITLE = "Zeta Engine v0.2";
	
	private final RenderContext ctx;
	
	double x = 0.0, y = 0.0;
	double speed = 60;
	Texture test;
	
	public TestGame() {
		super(WIDTH, HEIGHT, SCALE, TITLE);
		ctx = screen.getRenderContext();
	}

	@Override
	protected void init() {
		window.show();
		test = Texture.load("res/test2.png");
	}

	@Override
	protected void update(double dt) {
		if (Input.keyDown(KeyEvent.VK_D)) {
			x += speed * dt;
		} else if (Input.keyDown(KeyEvent.VK_A)) {
			x -= speed * dt;
		}
		
		if (Input.keyDown(KeyEvent.VK_S)) {
			y += speed * dt;
		} else if (Input.keyDown(KeyEvent.VK_W)) {
			y -= speed * dt;
		}
	}

	@Override
	protected void render() {
		screen.clear();
		ctx.drawTexture(40, 40, test);
		ctx.drawTexture(20, 10, test);
		ctx.drawTexture(200, 100, test);
		ctx.drawRect((int)x, (int)y, 20, 20, 0x00ff00);
		screen.swapBuffers();
	}

	public static void main(String[] args) {
		
		TestGame game = new TestGame();
		game.launch();
		
	}
	
}
