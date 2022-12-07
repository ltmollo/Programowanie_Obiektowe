package agh.ics.oop;
import agh.ics.oop.gui.App;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class SimulationEngine implements IEngine, Runnable {

    private final App observer;
    private MoveDirection[] directions;
    private final IWorldMap map;
    private final Vector2d[] positions;
    private final List<Animal> animals = new ArrayList<>();

    private final int moveDelay;

    public SimulationEngine(IWorldMap map, Vector2d[] positions, App app, int moveDelay){
        this.map = map;
        this.positions = positions;
        this.observer = app;
        this.moveDelay = moveDelay;
    }

    public void setDirections(String[] args){
        this.directions = new OptionsParser().parse(args);
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
        Platform.runLater(this.observer::updateMap);
        try {
            Thread.sleep(moveDelay);
        int nbOfAnimals = this.animals.size();
        int indexOfAnimal = 0;
        for(MoveDirection direction : directions){
            Animal animal = this.animals.get(indexOfAnimal);
            animal.move(direction);
            indexOfAnimal++;
            indexOfAnimal = indexOfAnimal % nbOfAnimals;
            Platform.runLater(this.observer::updateMap);
            Thread.sleep(moveDelay);
            }
        } catch (InterruptedException e) {
                throw new RuntimeException(e + "Przerwano symulację");
            }
    }
}