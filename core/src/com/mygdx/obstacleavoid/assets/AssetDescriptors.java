package com.mygdx.obstacleavoid.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class AssetDescriptors {

    public static final AssetDescriptor<BitmapFont> FONT =
            new AssetDescriptor<>(AssetsPaths.UI_FONT, BitmapFont.class);
    public static final AssetDescriptor<Texture> PLAYER =
            new AssetDescriptor<>(AssetsPaths.PLAYER, Texture.class);
    public static final AssetDescriptor<Texture> OBSTACLE =
            new AssetDescriptor<>(AssetsPaths.OBSTACLE, Texture.class);
    public static final AssetDescriptor<Texture> BACKGROUND =
            new AssetDescriptor<>(AssetsPaths.BACKGROUND, Texture.class);

    private AssetDescriptors() {}
}
