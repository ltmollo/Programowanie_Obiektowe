package agh.ics.oop;

import java.util.*;

abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    Map<Vector2d, Animal> animals = new HashMap<>();
    protected final Vector2d beginVector;
    protected final Vector2d endVector;
    private MapVisualizer toDraw = new MapVisualizer(this);


    public AbstractWorldMap (int maxWidth, int maxHeight, int minWidth, int minHeight){
        this.beginVector = new Vector2d(minWidth, minHeight);
        this.endVector = new Vector2d(maxWidth, maxHeight);
    }

    public List<Vector2d> getAnimalsPositions(){
        List<Vector2d> positions = new ArrayList<>();
        for (Vector2d vector : this.animals.keySet()){
            positions.add(new Vector2d(vector.x, vector.y));
        }
        return positions;
    }
    public boolean canMoveTo(Vector2d position) {
        if (position.follows(this.beginVector) && position.precedes(this.endVector)) {
            return !animals.containsKey(position);
        }
        return false;
    }

    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    public Object objectAt(Vector2d position) {
        return animals.get(position);
    }

    public boolean place(Animal animal) {
        if(!canMoveTo(animal.getPosition())) {return false;}
        this.animals.put(animal.getPosition(), animal);
        animal.addObserver(this);
        return true;
    }

    protected abstract  Vector2d checkBeginning();

    protected abstract Vector2d checkEnding();

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        Animal animal = this.animals.get(oldPosition);
        this.animals.remove(oldPosition);
        this.animals.put(newPosition, animal);

    }

    public String toString(){
        return toDraw.draw(checkBeginning(), checkEnding());
    }
}
