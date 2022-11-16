package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

abstract class AbstractWorldMap implements IWorldMap {
    protected List<Animal> animals = new ArrayList<>();
    protected final Vector2d beginVector;
    protected final Vector2d endVector;
    private MapVisualizer toDraw = new MapVisualizer(this);


    public AbstractWorldMap (int maxWidth, int maxHeight, int minWidth, int minHeight){
        this.beginVector = new Vector2d(minWidth, minHeight);
        this.endVector = new Vector2d(maxWidth, maxHeight);
    }

    public List<Vector2d> getAnimalsPositions(){
        List<Vector2d> positions = new ArrayList<>();
        for( Animal animal : this.animals){
            positions.add(new Vector2d(animal.getPosition().x, animal.getPosition().y));
        }
        return positions;
    }
    public boolean canMoveTo(Vector2d position) {
        if (position.follows(this.beginVector) && position.precedes(this.endVector)) {
            for (Animal elem : animals) {
                if (elem.getPosition().equals(position)) {return false;}
            }
            return true;
        }
        return false;
    }

    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    public Object objectAt(Vector2d position) {
        for (Animal elem : this.animals) {
            if (elem.getPosition().equals(position)) {return elem;}
        }
        return null;
    }

    public boolean place(Animal animal) {
        if(!canMoveTo(animal.getPosition())) {return false;}
        this.animals.add(animal);
        return true;
    }

    protected abstract  Vector2d checkBeginning();

    protected abstract Vector2d checkEnding();

    public String toString(){
        return toDraw.draw(checkBeginning(), checkEnding());
    }
}
