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
//            Board board = new Board();
            Animal zwierzak = new Animal();

        MoveDirection[] directions = OptionsParser.parse(arguments);
        System.out.println(zwierzak.toString());
        for( MoveDirection arg : directions){
            zwierzak.move(arg);
//            zwierzak.move(arg, board);
            System.out.println(zwierzak.toString());
        }
            System.out.println("System zakończył działanie");
        }
    }

