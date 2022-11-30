package agh.ics.oop;

import java.util.*;

public class MapBoundary implements IPositionChangeObserver{
    private Map<Vector2d, Grass> grass = new HashMap<>();
    // Warto wziąć trawę LowerLeft, UpperRight albo trzymać krotki (Vect, "zwierzak"), (Vect, "trawa")
    private final TreeSet<Vector2d> orderByX = new TreeSet<>(new CompareX());
    private final TreeSet<Vector2d> orderByY = new TreeSet<>(new CompareY());

    protected void addElementToMap(Vector2d vector){
        this.orderByX.add(vector);
        this.orderByY.add(vector);
    }

    protected void setMapBoundary(Map<Vector2d, Grass> grass){
        this.grass = grass;
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        if(!this.grass.containsKey(oldPosition)) {
            this.orderByX.remove(oldPosition);
            this.orderByY.remove(oldPosition);
        }
        addElementToMap(newPosition);
    }

    public Vector2d checkBeginning(){
        Vector2d vectorLowestX = this.orderByX.first();
        Vector2d vectorLowestY = this.orderByY.first();
        Vector2d beginning = vectorLowestX.lowerLeft(vectorLowestY);
        return beginning;
    }

    public Vector2d checkEnding(){
        Vector2d vectorHighestX = this.orderByX.last();
        Vector2d vectorHighestY = this.orderByY.last();
        Vector2d ending = vectorHighestX.upperRight(vectorHighestY);
        return ending;
    }
}
