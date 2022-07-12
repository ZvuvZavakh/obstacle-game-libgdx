package zvuv.zavakh.obstacle;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Logger;
import zvuv.zavakh.obstacle.screen.LoadingScreen;

public class App extends Game {

	private AssetManager assetManager;
	private SpriteBatch spriteBatch;

	@Override
	public void create() {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);

		assetManager = new AssetManager();
		assetManager.getLogger().setLevel(Logger.DEBUG);

		spriteBatch = new SpriteBatch();

		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		setScreen(new LoadingScreen(this));
	}

	public AssetManager getAssetManager() {
		return assetManager;
	}

	@Override
	public void dispose() {
		assetManager.dispose();
		spriteBatch.dispose();
	}

	public SpriteBatch getSpriteBatch() {
		return spriteBatch;
	}
}
