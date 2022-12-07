package agh.ics.oop.gui;

import agh.ics.oop.*;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class App extends Application{
    private GridPane grid = new GridPane();
    private IWorldMap map;
    private Stage primaryStage;

    private VBox drawObject(Vector2d currentPosition){
        VBox result = null;
        if (this.map.isOccupied(currentPosition)) {
            Object object = this.map.objectAt(currentPosition);
            if (object != null) {
                GuiElementBox newElem = new GuiElementBox((IMapElement) object);
                result = newElem.getBox();

            } else {
                result = new VBox(new Label(""));
            }
        } else {
            result = new VBox(new Label(""));
        }
        return result;
    }

    private void drawMap(){
        grid.setGridLinesVisible(true);

        GrassField myMap = (GrassField) map;
        int haveToiterateY = myMap.getUpperRight().y - myMap.getLowerLeft().y;
        int haveToiterateX = myMap.getUpperRight().x - myMap.getLowerLeft().x;

        Label label;
        for (int i = 0; i <= haveToiterateY; i++) {
            Integer newInt = myMap.getUpperRight().y-i;
            label = new Label(newInt.toString());

            grid.getRowConstraints().add(new RowConstraints(45));
            grid.add(label, 0, i+1);

            GridPane.setHalignment(label, HPos.CENTER);
            for (int k = 0; k < haveToiterateX+1; k++) {

                if (i == 0) {
                    newInt = myMap.getLowerLeft().x + k;
                    label = new Label(newInt.toString());
                    grid.add(label, k+1, 0);
                    grid.getColumnConstraints().add(new ColumnConstraints(45));
                    GridPane.setHalignment(label, HPos.CENTER);
                }
                VBox result = drawObject(new Vector2d(k+myMap.getLowerLeft().x , i+myMap.getLowerLeft().y));
                grid.add(result, k+1, haveToiterateY-i+1);
                GridPane.setHalignment(result, HPos.CENTER);
            }
        }

        label = new Label("x/y");
        grid.getColumnConstraints().add(new ColumnConstraints(45));
        grid.getRowConstraints().add(new RowConstraints(45));
        grid.add(label, 0, 0);
        GridPane.setHalignment(label, HPos.CENTER);
        Scene scene = new Scene(grid, (haveToiterateX + 2) * 45.5, (haveToiterateY + 2) * 45.5);
        primaryStage.setScene(scene);
        System.out.println(map.toString());
        System.out.println();
    }

    public void updateMap(){
        grid.getChildren().clear();
        this.grid = new GridPane();
        drawMap();
    }

    private void startGame(SimulationEngine engine, String text){
        String[] array = text.split(" ");
        engine.setDirections(array);
        Thread threadEngine = new Thread(engine);
        threadEngine.start();
    }

    public void start(Stage primaryStage) {
        try {
            AbstractWorldMap map = new GrassField(15);
            this.map = map;
            this.primaryStage = primaryStage;
            Vector2d[] positions2 = {new Vector2d(5, 2), new Vector2d(2, 7), new Vector2d(4, 7), new Vector2d(6, 11)};
            SimulationEngine engine = new SimulationEngine(map, positions2, this, 800);
            Button button = new Button("Start");
            TextField text = new TextField("Enter directions");
            HBox hbox = new HBox(text, button);
            button.setOnAction(actionEvent -> startGame( engine, text.getText()));

            Scene scene = new Scene(hbox, 400, 400);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IllegalArgumentException exception) {
            // kod obsługi wyjątku
            System.out.println(exception.getMessage());
        }
        catch (RuntimeException e){
            System.out.println(e.getMessage());
        }

    }
}
