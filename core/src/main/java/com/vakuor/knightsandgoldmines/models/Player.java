package com.vakuor.knightsandgoldmines.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

import static com.vakuor.knightsandgoldmines.screens.Box2DMapObjectParserScreen.getUnitScale;

@SuppressWarnings({"WeakerAccess", "unused", "FieldCanBeLocal"})
public class Player extends Actor{
    public final Vector2 position;
    public World world;
    public Body playerBody;
    private float unitScale;
    private float width=1/4f*1.8f;
    private float height=1/4f*2.8f;
    private float speed =4;
    public FixtureDef fdef;
    public BodyDef bdef;

    private TextureAtlas playerTextureAtlas = new TextureAtlas("visual/output/Archer/Archers2.atlas");
    private Array<TextureAtlas.AtlasRegion> standframes = playerTextureAtlas.findRegions("idle");
    Animation<TextureRegion> stand = new Animation<TextureRegion>(0, standframes);


    public Player(World world, Vector2 position) {
        this.position = position;
        this.world = world;
        unitScale = getUnitScale();
        defineSprite();
    }



    public void defineSprite() {
        bdef = new BodyDef();
        bdef.position.set(1, 4);
        bdef.type = BodyDef.BodyType.DynamicBody;
        fdef = new FixtureDef();
        fdef.filter.maskBits = 0x0004;
        PolygonShape shape = new PolygonShape();
//        shape.setAsBox(width/2,height/2);

        shape.setAsBox(width / 2, height / 2, new Vector2().set(width/2, height/2),0);
        fdef.shape = shape;
        playerBody = world.createBody(bdef);
        playerBody.createFixture(fdef);
        playerBody.setFixedRotation(true);
        //playerBody.setLinearDamping(1);


        shape.dispose();
    }

    @Override
    public void act(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            playerBody.setLinearVelocity(speed, playerBody.getLinearVelocity().y);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            playerBody.setLinearVelocity(-speed, playerBody.getLinearVelocity().y);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.Q)){
            playerBody.applyForceToCenter(0,500,true);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.E)){
            playerBody.applyForceToCenter(0,-500,true);
        }
        super.act(Gdx.graphics.getDeltaTime());
    }

    /////////////////////////////////////////////////////////
    //Getters and Setters
    /////////////////////////////////////////////////////////

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

}
