package agh.ics.oop;
import java.util.List;
import java.util.Vector;

public class SimulationEngine implements IEngine {

    private MoveDirection[] directions;
    private IWorldMap map;
    private Vector2d[] positions;

    public SimulationEngine(MoveDirection[] directions, IWorldMap map, Vector2d[] positions){
        this.directions = directions;
        this.map = map;
        this.positions = positions;
    }

    public void addAnimalOnMap(){
        for(Vector2d elem : positions){
            Animal animal = new Animal(this.map, elem);
            map.place(animal);
        }
    }

    public void run() {
        addAnimalOnMap();
        int nbOfAnimals = RectangularMap.animals.size();
        int indexOfAnimal = 0;
        for(MoveDirection direction : directions){
            Animal animal = RectangularMap.animals.get(indexOfAnimal);
            animal.move(direction);
            indexOfAnimal++;
            indexOfAnimal = indexOfAnimal % nbOfAnimals;
        }
    }
}
