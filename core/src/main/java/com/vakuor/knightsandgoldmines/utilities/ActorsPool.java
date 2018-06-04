package com.vakuor.knightsandgoldmines.utilities;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

public class ActorsPool {
    public void addActors(Stage stage, Array<Actor> actorArray){
        for (Actor actor: actorArray){
            stage.getRoot().addActor(actor);
        }
    }
    public void addActor(Stage stage, Actor actor){
        stage.getRoot().addActor(actor);
    }
}
