package com.vakuor.knightsandgoldmines.utilities;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public class ExtendedBody extends Body {
    /**
     * Constructs a new body with the given address
     *
     * @param world the world
     * @param addr  the address
     */
    private Object externalBodyType = null;
    private Body body;

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    protected ExtendedBody(World world, long addr) {
        super(world, addr);
    }
}
