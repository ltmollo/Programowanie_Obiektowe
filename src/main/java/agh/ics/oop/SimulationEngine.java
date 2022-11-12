package agh.ics.oop;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class SimulationEngine implements IEngine {

    private final MoveDirection[] directions;
    private final IWorldMap map;
    private final Vector2d[] positions;

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
        RectangularMap currentMap = (RectangularMap)map;
        int nbOfAnimals = currentMap.getAnimals().size();
        int indexOfAnimal = 0;
        List<Animal> animals = currentMap.getAnimals();
        for(MoveDirection direction : directions){
            Animal animal = animals.get(indexOfAnimal);
            animal.move(direction);
            indexOfAnimal++;
            indexOfAnimal = indexOfAnimal % nbOfAnimals;
        }
    }
}