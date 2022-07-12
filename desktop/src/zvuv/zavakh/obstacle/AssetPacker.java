package zvuv.zavakh.obstacle;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class AssetPacker {

    private static final boolean DRAW_DEBUG_OUTLINE = false;
    private static final String RAW_FILES_PATH = "assets/ui-raw/ui";
    private static final String DEST_FILES_PATH = "assets/ui";
    private static final String PACK_FILE_NAME = "ui";

    public static void main(String[] args) {
        TexturePacker.Settings settings = new TexturePacker.Settings();

        settings.debug = DRAW_DEBUG_OUTLINE;
        settings.pot = false;

        TexturePacker.process(
                settings,
                RAW_FILES_PATH,
                DEST_FILES_PATH,
                PACK_FILE_NAME
        );
    }
}
