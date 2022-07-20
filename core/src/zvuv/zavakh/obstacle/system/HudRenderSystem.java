package zvuv.zavakh.obstacle.system;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import zvuv.zavakh.obstacle.common.GameManager;
import zvuv.zavakh.obstacle.config.GameConfig;

public class HudRenderSystem extends EntitySystem {

    private final Viewport viewport;
    private final SpriteBatch spriteBatch;
    private final BitmapFont bitmapFont;

    private final GlyphLayout layout = new GlyphLayout();

    public HudRenderSystem(Viewport viewport, SpriteBatch spriteBatch, BitmapFont bitmapFont) {
        this.viewport = viewport;
        this.spriteBatch = spriteBatch;
        this.bitmapFont = bitmapFont;
    }

    @Override
    public void update(float deltaTime) {
        viewport.apply();
        spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
        spriteBatch.begin();
        draw();
        spriteBatch.end();
    }

    private void draw() {
        String livesString = "LIVES: " + GameManager.getInstance().getLives();
        layout.setText(bitmapFont, livesString);
        bitmapFont.draw(
                spriteBatch,
                livesString,
                20,
                GameConfig.HUD_HEIGHT - layout.height
        );

        String scoreString = "SCORE: " + GameManager.getInstance().getScore();
        layout.setText(bitmapFont, scoreString);
        bitmapFont.draw(
                spriteBatch,
                scoreString,
                GameConfig.HUD_WIDTH - layout.width - 20,
                GameConfig.HUD_HEIGHT - layout.height
        );
    }
}
