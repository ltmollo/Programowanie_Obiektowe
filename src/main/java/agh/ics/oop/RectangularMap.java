package agh.ics.oop;
import java.util.ArrayList;
import java.util.List;

class RectangularMap implements IWorldMap {

    public int width;
    public int height;
    public static List<Animal> animals;
    public RectangularMap(int width, int height){
        this.width = width;
        this.height = height;
        this.animals = new ArrayList<>();
    }

    public boolean canMoveTo(Vector2d position) {
        if (position.follows(new Vector2d(0, 0)) && position.precedes(new Vector2d(width, height))) {
            for (Animal elem : animals) {
                if (elem.getCurrentPosition().equals(position)) {return false;}
            }
            return true;
        }
        return false;
    }

    public boolean place(Animal animal) {
        if(!canMoveTo(animal.getCurrentPosition())) {return false;}
        animals.add(animal);
        return true;
    }

    public boolean isOccupied(Vector2d position) {
        for (Animal elem : animals){
            if(elem.isAt(position)) {return true;}
        }
        return false;
    }

    public Object objectAt(Vector2d position) {
        for (Animal elem : animals) {
            if (elem.getCurrentPosition().equals(position)) {return elem;}
        }
        return null;
    }

    public void movement(Animal animal, MoveDirection direction){
        animal.move(direction);
    }

    public String toString(){
        MapVisualizer toDraw = new MapVisualizer(this);
        return toDraw.draw(new Vector2d(0, 0), new Vector2d(width, height));
    }
}
