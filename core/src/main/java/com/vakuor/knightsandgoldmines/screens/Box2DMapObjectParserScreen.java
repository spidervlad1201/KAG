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
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.vakuor.knightsandgoldmines.models.Player;
import com.vakuor.knightsandgoldmines.utilities.ActorsPool;
import com.vakuor.knightsandgoldmines.utilities.ExtendedWorld;
import com.vakuor.knightsandgoldmines.utilities.StagesPool;
import com.vakuor.knightsandgoldmines.utilities.TiledBox2DMapObjectParser;

@SuppressWarnings("FieldCanBeLocal")
public class Box2DMapObjectParserScreen implements Screen {

    private TiledMap map;
    private World world;
    private ActorsPool pool;
    private StagesPool stagesPool;
    private ExtendedWorld exworld;
    private Box2DDebugRenderer box2DRenderer;
    private OrthogonalTiledMapRenderer mapRenderer;
    private OrthographicCamera camera;
    private Player player;
    private Array<Body> bodies = new Array<Body>();
    private Array<Body> oldbodies = null;
    private TiledMapTile tile;
    private TiledMapTileLayer.Cell lcell = null;
    private float lx=0,ly=0,localx=0,localy=0;
    private static float unitScale;
    private boolean debug=true;



    @Override
    public void show() {
        pool = new ActorsPool();
        stagesPool = new StagesPool();
        exworld = new ExtendedWorld(new World(new Vector2(0,-9.81f),true));
        //world = new World(new Vector2(0,-9.81f),true);
        box2DRenderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera();
        camera.zoom = 0.5f;

        map = new TmxMapLoader().load("maps/Box2DMapObjectParserTutorials.tmx");
        TiledBox2DMapObjectParser parser = new TiledBox2DMapObjectParser();
        parser.load(exworld.getWorld(),map);
        player = new Player(exworld.getWorld(),new Vector2(0,0));

        exworld.getWorld().createBody(player.bdef);
        unitScale = parser.getUnitScale();
        mapRenderer = new OrthogonalTiledMapRenderer(map,parser.getUnitScale());

        Stage stage = new Stage();
        Stage uistage = new Stage();
        pool.addActor(stage,player);
        stagesPool.addStage(stage);
        stagesPool.addStage(uistage);

    }

    @Override
    public void render(float delta) {
        //Gdx.gl.glClearColor(0.7f, 0.7f, 1.0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        act(exworld,map);

    }

    public void act(ExtendedWorld world, TiledMap map){
        stagesPool.act(Gdx.graphics.getDeltaTime());
        world.getWorld().step(Gdx.graphics.getDeltaTime(),8,3);
        world.getWorld().getBodies(bodies);
        world.draw(map,bodies);
        stagesPool.draw();
        mapRenderer.setView(camera);
        mapRenderer.render();
        if(debug) box2DRenderer.render(exworld.getWorld(),camera.combined);
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
//        exworld.getWorld().dispose();
//        mapRenderer.dispose();
    }
    public static float getUnitScale() {
        return unitScale;
    }

    @Override
    public void dispose() {
        map.dispose();
        exworld.getWorld().dispose();
        box2DRenderer.dispose();
        mapRenderer.dispose();
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