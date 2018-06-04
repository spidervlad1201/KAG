package com.vakuor.knightsandgoldmines.utilities;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

@SuppressWarnings({"WeakerAccess", "FieldCanBeLocal"})
public class ExtendedWorld{

    private World world;
    private TileCustomProperties prop;
    private TiledMapTile tile = null;
    private float lx=0,ly=0;
    private TiledMapTileLayer.Cell lcell=null;

    public ExtendedWorld(World world){
        setWorld(world);
    }

    public void draw(TiledMap map, Array<Body> bodies){
        TiledMapTileLayer l = new TiledMapTileLayer(Integer.valueOf(map.getProperties().get("width").toString()),Integer.valueOf(map.getProperties().get("height").toString()),8,8);
        map.getLayers().remove(map.getLayers().getIndex("tiles"));
        l.setName("tiles");
        map.getLayers().add(l);

        for(Body bod:bodies) {

            if(bod.getFixtureList().size>0) {
                prop = (TileCustomProperties) bod.getFixtureList().get(0).getUserData();
                tile = map.getTileSets().getTileSet("tileset").getTile(165 + 1);
                if (prop != null) {
                    if (!prop.isIsv()) {
                        tile = map.getTileSets().getTileSet("tileset").getTile(1);
                    } else {
                        tile = prop.getTile();
                    }
                }
                lcell = new TiledMapTileLayer.Cell();
                lcell.setTile(tile);
                lx = 4 * bod.getPosition().x;
                ly = 4 * bod.getPosition().y;
                if (lx - (int) lx >= 0.5f) lx++;
                if (ly - (int) ly >= 0.5f) ly++;
                ((TiledMapTileLayer) map.getLayers().get("tiles")).setCell((int) lx, (int) ly, lcell);
                tile = null;
            }
        }
    }

    public World getWorld() {
        return world;
    }
    public void setWorld(World world) {
        this.world = world;
    }
}