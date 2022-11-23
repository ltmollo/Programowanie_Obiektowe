package agh.ics.oop;

import java.util.List;
import java.util.Scanner;

import static org.testng.AssertJUnit.assertEquals;

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


        MoveDirection[] directions2 = new OptionsParser().parse(arguments);
        IWorldMap map2 = new GrassField(5);
        Vector2d[] positions2 = { new Vector2d(2,2), new Vector2d(2, 3)};
        IEngine engine2 = new SimulationEngine(directions2, map2, positions2);
        engine2.run();


        System.out.println(map2.toString());
        System.out.println();
        System.out.println("System zakończył działanie");
       }
}

