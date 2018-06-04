package com.vakuor.knightsandgoldmines.utilities;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

public class StagesPool {
    private Array<Stage> stageArray;
    public StagesPool(){
        stageArray = new Array<Stage>();
    }

    public void addStage(Stage stage){
        stageArray.add(stage);
    }

    public void act(float deltaTime){
        for(Stage stage:stageArray){
            stage.act(deltaTime);
        }
    }
    public void draw(){
        for(Stage stage:stageArray){
            stage.draw();
        }
    }
    public void doAll(float deltaTime){
        act(deltaTime);
        draw();
    }
}
