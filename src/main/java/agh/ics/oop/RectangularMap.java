package agh.ics.oop;
import java.util.ArrayList;
import java.util.List;

class RectangularMap extends AbstractWorldMap{

    protected final int width;
    protected final int height;

    public RectangularMap(int width, int height){
        super(width, height, 0, 0);
        this.width = width;
        this.height = height;
    }

    protected Vector2d checkBeginning(){
        return new Vector2d(0, 0);
    }

    protected  Vector2d checkEnding(){
        return new Vector2d(width, height);
    }


}
