package zvuv.zavakh.obstacle.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class AssetDescriptors {

    private AssetDescriptors() {}

    public static final AssetDescriptor<TextureAtlas> ATLAS =
            new AssetDescriptor<>(AssetsPaths.ATLAS_PATH, TextureAtlas.class);

    public static final AssetDescriptor<TextureAtlas> UI_ATLAS =
            new AssetDescriptor<>(AssetsPaths.UI_ATLAS_PATH, TextureAtlas.class);
    public static final AssetDescriptor<BitmapFont> FONT =
            new AssetDescriptor<>(AssetsPaths.UI_FONT, BitmapFont.class);
}
