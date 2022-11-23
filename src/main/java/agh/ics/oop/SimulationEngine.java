package agh.ics.oop;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class SimulationEngine implements IEngine {

    private final MoveDirection[] directions;
    private final IWorldMap map;
    private final Vector2d[] positions;
    private final List<Animal> animals = new ArrayList<>();

    public SimulationEngine(MoveDirection[] directions, IWorldMap map, Vector2d[] positions){
        this.directions = directions;
        this.map = map;
        this.positions = positions;
    }

    public void addAnimalOnMap(){
        for(Vector2d elem : positions){
            Animal animal = new Animal(this.map, elem);
            if(map.place(animal)){
                this.animals.add(animal);
            };
        }
    }

    public void run() {
        addAnimalOnMap();
        int nbOfAnimals = this.animals.size();
        int indexOfAnimal = 0;
        for(MoveDirection direction : directions){
            Animal animal = this.animals.get(indexOfAnimal);
            animal.move(direction);
            indexOfAnimal++;
            indexOfAnimal = indexOfAnimal % nbOfAnimals;
        }
    }
}