package com.vakuor.knightsandgoldmines.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.vakuor.knightsandgoldmines.utilities.TiledBox2DMapObjectParser;

public class Box2DMapObjectParserScreen implements Screen {

    private TiledMap map;
    private World world;
    private Box2DDebugRenderer box2DRenderer;
    private OrthogonalTiledMapRenderer mapRenderer;
    private OrthographicCamera camera;
    private Array<Body> bodies = new Array<Body>();
    private Array<Body> oldbodies = null;
    private TiledMapTile tile;
    private TiledMapTileLayer.Cell lcell = null;
    private float lx=0,ly=0,localx=0,localy=0;

    @Override
    public void show() {

        world = new World(new Vector2(0,-9.81f),true);
        box2DRenderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera();
        camera.zoom = 0.5f;

        map = new TmxMapLoader().load("maps/Box2DMapObjectParserTutorials.tmx");

        TiledBox2DMapObjectParser parser = new TiledBox2DMapObjectParser();
        parser.load(world,map);

        mapRenderer = new OrthogonalTiledMapRenderer(map,parser.getUnitScale());
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.7f, 0.7f, 1.0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        act(world,map);
        mapRenderer.setView(camera);
        mapRenderer.render();
        box2DRenderer.render(world,camera.combined);
    }

    public void act(World world, TiledMap map){
        world.step(Gdx.graphics.getDeltaTime(),8,3);
        world.getBodies(bodies);

        TiledMapTileLayer l = new TiledMapTileLayer(Integer.valueOf(map.getProperties().get("width").toString()),Integer.valueOf(map.getProperties().get("height").toString()),8,8);
        map.getLayers().remove(map.getLayers().getIndex("tiles"));
        l.setName("tiles");
        map.getLayers().add(l);

        for(Body bod:bodies) {
            tile = (TiledMapTile) bod.getFixtureList().get(0).getUserData();
            if (tile == null) {
                tile = map.getTileSets().getTileSet("tileset").getTile(165 + 1);
            }
//          System.out.println(tile.getId());
            lcell = new TiledMapTileLayer.Cell();
            lcell.setTile(tile);
            lx = 4 * bod.getPosition().x;
            ly = 4 * bod.getPosition().y;
            if (lx - (int) lx >= 0.5f) lx++;
            if (ly - (int) ly >= 0.5f) ly++;
            ((TiledMapTileLayer) map.getLayers().get("tiles")).setCell((int) lx, (int) ly, lcell);

        }
    }

    public void sr(boolean a){
        if(a){
            System.out.println(true);
        }
        else System.out.println(false);
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth=width/25;
        camera.viewportHeight=height/25;
        camera.update();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        world.dispose();
        mapRenderer.dispose();
    }

    @Override
    public void dispose() {

    }
}


/*
    @Override
    public void show() {

        world = new World(new Vector2(0,-9.81f),true);
        box2DRenderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera();
        builder=new MapBodyBuilder();

        TiledMap map = new TmxMapLoader().load("maps/Box2DMapObjectParserTutorials.tmx");
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get("tiles");
        MapLayer seclayer = map.getLayers().get("collisionLayer");
//        System.out.println(map.getTileSets().getTileSet("tileset").getTile(20).getTextureRegion().getRegionWidth());
//        if(seclayer.getObjects()!=null) System.out.println(seclayer.getObjects().getCount());
//        MapLayer collisionLayer = map.getLayers().get("collisionLayer");
//        if(map.getTileSets().getTileSet("tileset")!=null)System.out.println("1");
//        map.getTileSets().getTileSet("tileset").getTile(48);
//        TiledMapTileLayer.Cell cell = layer.getCell(0,0);
//        cell.setTile(map.getTileSets().getTileSet("tileset").getTile(48));
//        layer.setCell(0,0,cell);

//        Array<Body> bodies = MapBodyBuilder.buildShapes(map,8,world);
//        for(Body lbody : bodies){
//            MyBodyDef def = new MyBodyDef(lbody);
//            world.createBody(def);
//        }

        System.out.println(layer.getObjects().getCount());
        TiledBox2DMapObjectParser parser = new TiledBox2DMapObjectParser();
        parser.load(world,map);


//        if((MapBodyBuilder.buildShapes(map,8,world)).get(0)!=null) System.out.println("n");

        mapRenderer = new OrthogonalTiledMapRenderer(map,parser.getUnitScale());

    }
*/