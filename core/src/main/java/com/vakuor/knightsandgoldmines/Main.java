package com.vakuor.knightsandgoldmines;

import com.badlogic.gdx.Game;
import com.vakuor.knightsandgoldmines.screens.Box2DMapObjectParserScreen;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    @Override
    public void create() {
        setScreen(new Box2DMapObjectParserScreen());
    }
}