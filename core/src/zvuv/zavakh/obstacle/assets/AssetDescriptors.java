package zvuv.zavakh.obstacle.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class AssetDescriptors {

    private AssetDescriptors() {}

    public static final AssetDescriptor<TextureAtlas> ATLAS =
            new AssetDescriptor<>(AssetsPaths.ATLAS_PATH, TextureAtlas.class);

    public static final AssetDescriptor<BitmapFont> FONT =
            new AssetDescriptor<>(AssetsPaths.UI_FONT, BitmapFont.class);

    public static final AssetDescriptor<Skin> UI_SKIN =
            new AssetDescriptor<>(AssetsPaths.UI_SKIN, Skin.class);

    public static final AssetDescriptor<Sound> SOUND_HIT =
            new AssetDescriptor<>(AssetsPaths.SOUND_HIT, Sound.class);
}
