package zvuv.zavakh.obstacle;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import zvuv.zavakh.obstacle.App;
import zvuv.zavakh.obstacle.config.GameConfig;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();

		config.setForegroundFPS(60);
		config.setTitle("obstacle");
		config.setWindowedMode((int) GameConfig.WIDTH, (int) GameConfig.HEIGHT);

		new Lwjgl3Application(new App(), config);
	}
}
