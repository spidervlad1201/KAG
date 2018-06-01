package com.vakuor.knightsandgoldmines.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class Box2DMapObjectParserScreen implements Screen {

    private World world;
    private Box2DDebugRenderer box2DRenderer;
    private OrthogonalTiledMapRenderer mapRenderer;
    private OrthographicCamera camera;

    @Override
    public void show() {

        world = new World(new Vector2(0,-9.81f),true);
        box2DRenderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera();

        TiledMap map = new TmxMapLoader().load("maps/Box2DMapObjectParserTutorials.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mapRenderer.setView(camera);
        mapRenderer.render();
        box2DRenderer.render(world,camera.combined);
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
