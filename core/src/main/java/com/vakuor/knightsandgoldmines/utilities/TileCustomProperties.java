package com.vakuor.knightsandgoldmines.utilities;

import com.badlogic.gdx.maps.tiled.TiledMapTile;

public class TileCustomProperties {

    private TiledMapTile tile=null;
    private boolean isv;
    public TileCustomProperties(){

    }

    public TiledMapTile getTile() {
        return tile;
    }

    public void setTile(TiledMapTile tile) {
        this.tile = tile;
    }

    public boolean isIsv() {
        return isv;
    }

    public void setIsv(boolean isv) {
        this.isv = isv;
    }
}
