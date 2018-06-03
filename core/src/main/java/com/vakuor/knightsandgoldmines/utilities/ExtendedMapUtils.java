package com.vakuor.knightsandgoldmines.utilities;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMapTile;

import net.dermetfan.gdx.maps.MapUtils;

public class ExtendedMapUtils extends MapUtils {

    public static <T> T findProperty(String key, T defaultValue, TiledMapTile tile, MapProperties... properties) {
        T value = defaultValue;
        for(MapProperties property : properties)
            value = getProperty(property, key, value);
        return value;
    }
}
