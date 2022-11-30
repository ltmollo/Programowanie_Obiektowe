package agh.ics.oop;

import agh.ics.oop.gui.App;
import javafx.application.Application;

import java.util.List;
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
        Application.launch(App.class, arguments);


    }
}

