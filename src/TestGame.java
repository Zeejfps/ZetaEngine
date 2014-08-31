import java.awt.event.KeyEvent;

import com.zeta.engine.Game;
import com.zeta.engine.graphics.RenderContext;
import com.zeta.engine.graphics.Screen;
import com.zeta.engine.graphics.Texture;
import com.zeta.engine.graphics.Window;
import com.zeta.engine.input.Keyboard;

public class TestGame extends Game {
	
	private final Screen screen;
	private final Window window;
	private final Keyboard keyboard;
	private final RenderContext ctx;
	
	double x = 0.0, y = 0.0;
	double speed = 60;
	Texture test;
	
	public TestGame() {
	
		screen = new Screen(800, 600, 2);
		window = new Window(this, screen, "Zeta Engine v0.1");
		keyboard = new Keyboard(screen);
		
		ctx = screen.getRenderContext();
		
	}

	@Override
	protected void init() {
		window.show();
		test = Texture.load("res/test2.png");
		System.out.println(Integer.toHexString(test.getPixelData()[0]));
	}

	@Override
	protected void update(double dt) {
		if (keyboard.keyDown(KeyEvent.VK_D)) {
			x += speed * dt;
		} else if (keyboard.keyDown(KeyEvent.VK_A)) {
			x -= speed * dt;
		}
		
		if (keyboard.keyDown(KeyEvent.VK_S)) {
			y += speed * dt;
		} else if (keyboard.keyDown(KeyEvent.VK_W)) {
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
