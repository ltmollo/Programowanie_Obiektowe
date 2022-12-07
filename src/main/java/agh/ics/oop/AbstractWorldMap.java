package agh.ics.oop;

import java.util.*;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    protected Map<Vector2d, Animal> animals = new HashMap<>();
    public final Vector2d beginVector;
    public final Vector2d endVector;
    private final MapVisualizer toDraw = new MapVisualizer(this);

    protected MapBoundary mapBound = new MapBoundary();

    public Vector2d getLowerLeft(){
        return mapBound.checkBeginning();
    }

    public Vector2d getUpperRight(){
        return mapBound.checkEnding();
    }

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
        if(!canMoveTo(animal.getPosition())) {
            throw new IllegalArgumentException("Pole " + animal.getPosition().toString() + " jest już zajęte.");
        }
        this.animals.put(animal.getPosition(), animal);
        mapBound.addElementToMap(animal.getPosition());
        animal.addObserver(this);
        animal.addObserver(mapBound);
        return true;
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        Animal animal = this.animals.get(oldPosition);
        this.animals.remove(oldPosition);
        this.animals.put(newPosition, animal);
    }

    public String toString(){
        return toDraw.draw(mapBound.checkBeginning(), mapBound.checkEnding());
    }
}
