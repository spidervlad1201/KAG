package com.vakuor.knightsandgoldmines.utilities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;

public class MyBodyDef extends BodyDef {

    public MyBodyDef(Body body){
        type=body.getType();
        active=body.isActive();
        allowSleep=body.isSleepingAllowed();
        angle=body.getAngle();
        angularDamping=body.getAngularDamping();
        angularVelocity=body.getAngularVelocity();
        awake=body.isAwake();
        bullet=body.isBullet();
        fixedRotation=body.isFixedRotation();
        gravityScale=body.getGravityScale();
        linearDamping=body.getLinearDamping();
        linearVelocity=body.getLinearVelocity();
        position=body.getPosition();
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Vector2 getLinearVelocity() {
        return linearVelocity;
    }

    public void setLinearVelocity(Vector2 linearVelocity) {
        this.linearVelocity = linearVelocity;
    }

    public Vector2 linearVelocity;
    public Vector2 position = new Vector2();

}
