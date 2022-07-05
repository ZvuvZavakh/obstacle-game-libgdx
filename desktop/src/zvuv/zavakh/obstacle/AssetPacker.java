package zvuv.zavakh.obstacle;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class AssetPacker {

    private static final boolean DRAW_DEBUG_OUTLINE = false;
    private static final String ASSETS_PATH = "assets/sprites";

    public static void main(String[] args) {
        TexturePacker.Settings settings = new TexturePacker.Settings();

        settings.debug = DRAW_DEBUG_OUTLINE;
        settings.pot = false;

        TexturePacker.process(
                settings,
                ASSETS_PATH,
                ASSETS_PATH,
                "gameplay"
        );
    }
}
