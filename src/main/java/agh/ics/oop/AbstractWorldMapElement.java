package agh.ics.oop;

import javafx.scene.image.Image;

import java.io.FileInputStream;

abstract class AbstractWorldMapElement implements IMapElement{
    protected Vector2d position;

    public AbstractWorldMapElement(Vector2d position){
        this.position = position;
    }

    public Vector2d getPosition(){
        return this.position;
    }

    public String getLinkToImage(){
        String result;
        switch(this.toString()){
            case ("N") -> result = "src/main/resources/up.png";
            case ("E") -> result = "src/main/resources/right.png";
            case ("S") -> result = "src/main/resources/down.png";
            case ("W") -> result = "src/main/resources/left.png";
            case ("*") -> result = "src/main/resources/grass.png";
            default -> result = null;
        }
        return result;
    }
}
