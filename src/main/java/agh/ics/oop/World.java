package agh.ics.oop;

import java.util.Arrays;
import java.util.Scanner;


public class World {
    public static void main(String[] args) {
        System.out.println("System wystartował");
        String[] table;

        if(args.length == 0){
            Scanner scanner = new Scanner(System.in);
            String elements = scanner.nextLine();
            table = elements.split(" ");
        }

        else{
            table = args;
        }

        int a = table.length;
        Direction[] tableArguments = new Direction[a];

        for (int i = 0; i < a; i++) {
            switch (table[i]) {
                case "f" -> tableArguments[i] = Direction.FORWARD;
                case "b" -> tableArguments[i] = Direction.BACKWARD;
                case "l" -> tableArguments[i] = Direction.LEFT;
                case "r" -> tableArguments[i] = Direction.RIGHT;
            }
        }

        run(tableArguments);
        System.out.println("System zakończył działanie");
    }
        public static void run(Direction[] tableArguments){
            System.out.println("Start");
            Arrays.stream(tableArguments).forEach(j -> {
                if (j == Direction.FORWARD) {
                    System.out.println("Zwierzak idzie do przodu");
                } else if (j == Direction.BACKWARD) {
                    System.out.println("Zwierzak idzie do tyłu");
                } else if (j == Direction.RIGHT) {
                    System.out.println("Zwierzak skręca w prawo");
                } else if (j == Direction.LEFT) {
                    System.out.println("Zwierzak skręca w lewo");
                }
            });
            System.out.println("Stop");
    }
}


