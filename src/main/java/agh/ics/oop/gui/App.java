package agh.ics.oop.gui;

import agh.ics.oop.*;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.util.List;
import java.util.Vector;

public class App extends Application {
    private Label label;
    private GridPane grid = new GridPane();
    private IWorldMap map;

    private String drawObject(Vector2d currentPosition) {
        String result = null;
        if (this.map.isOccupied(currentPosition)) {
            Object object = this.map.objectAt(currentPosition);
            if (object != null) {
                result = object.toString();
            } else {
                result = " ";
            }
        } else {
            result = " ";
        }
        return result;
    }

    private void drawMap(Stage primaryStage){
        grid.setGridLinesVisible(true);
        GrassField myMap = (GrassField) map;

        int haveToiterateY = myMap.getUpperRight().y - myMap.getLowerLeft().y;
        int haveToiterateX = myMap.getUpperRight().x - myMap.getLowerLeft().x;

        for (int i = 0; i <= haveToiterateY; i++) {
            Integer newInt = myMap.getUpperRight().y-i;
            label = new Label(newInt.toString());
            grid.getColumnConstraints().add(new ColumnConstraints(20));
            grid.getRowConstraints().add(new RowConstraints(20));
            grid.add(label, 0, i+1);

            GridPane.setHalignment(label, HPos.CENTER);
            for (int k = 0; k < haveToiterateX+1; k++) {
                if (i == 0) {
                    newInt = myMap.getLowerLeft().x + k;
                    label = new Label(newInt.toString());
                    grid.add(label, k+1, 0);
                    GridPane.setHalignment(label, HPos.CENTER);
                }
                String result = drawObject(new Vector2d(k+myMap.getLowerLeft().x , i+myMap.getLowerLeft().y));
                label = new Label(result);
                grid.add(label, k+1, haveToiterateY-i+1);
                GridPane.setHalignment(label, HPos.CENTER);
            }
        }

        label = new Label("x/y");
        grid.getColumnConstraints().add(new ColumnConstraints(20));
        grid.getRowConstraints().add(new RowConstraints(20));
        grid.add(label, 0, 0);
        GridPane.setHalignment(label, HPos.CENTER);
        Scene scene = new Scene(grid, (haveToiterateX+2)*20, (haveToiterateY+2)*20);
        primaryStage.setScene(scene);
        primaryStage.show();

        System.out.println(map.toString());
        System.out.println();
        System.out.println("System zakończył działanie");
    }

    public void start(Stage primaryStage) {
        try {
            List<String> arguments = getParameters().getRaw();
            String[] args = arguments.toArray(new String[0]);
            MoveDirection[] directions2 = new OptionsParser().parse(args);
            AbstractWorldMap map = new GrassField(5);
            this.map = map;
            Vector2d[] positions2 = {new Vector2d(5, 2), new Vector2d(2, 7)};
            IEngine engine2 = new SimulationEngine(directions2, map, positions2);
            engine2.run();
            drawMap(primaryStage);


        } catch (IllegalArgumentException exception) {
            // kod obsługi wyjątku
            System.out.print(exception.getMessage());
        }

    }
}
