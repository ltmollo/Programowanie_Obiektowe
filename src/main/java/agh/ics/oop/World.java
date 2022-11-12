package agh.ics.oop;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

public class World {
    public static void main(String[] args) {

        System.out.println("System wystartował‚");
        String[] arguments;
        if (args.length == 0) {
            Scanner scanner = new Scanner(System.in);
            String elements = scanner.nextLine();
            arguments = elements.split(" ");
        } else {
            arguments = args;
        }

        MoveDirection[] directions = new OptionsParser().parse(arguments);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(2, 3)};
        IEngine engine = new SimulationEngine(directions, map, positions);
        Animal animal = new Animal(map);
        engine.run();
        System.out.println(map.toString());
        System.out.println("System zakończył działanie");
       }
}

