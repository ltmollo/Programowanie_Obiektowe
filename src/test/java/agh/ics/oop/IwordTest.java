package agh.ics.oop;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.testng.AssertJUnit.*;

public class IwordTest {
    String[] args = {"2", "1", "l"};
    MoveDirection[] directions = new OptionsParser().parse(args);
    IWorldMap map = new RectangularMap(10, 5);
    Vector2d[] positions = { new Vector2d(2,2), new Vector2d(2, 3)};
    IEngine engine = new SimulationEngine(directions, map, positions);

    @Test
    public void canMoveToTest(){
        engine.run();
        assertFalse(map.canMoveTo(new Vector2d(2, 2)));
        assertFalse(map.canMoveTo(new Vector2d(2, 3)));
    }

    @Test
    public void placeTest(){
        engine.run();
        Animal zwierzak = new Animal(map, new Vector2d(2, 2));
        Animal zwierzak1 = new Animal(map, new Vector2d(2, 5));
        assertFalse(map.place(zwierzak));
        assertTrue(map.place(zwierzak1));
    }

    @Test
    public void isOccupiedTest(){
        engine.run();
        assertTrue(map.isOccupied(new Vector2d(2, 2)));
        assertFalse(map.isOccupied(new Vector2d(2, 5)));
        assertTrue(map.isOccupied(new Vector2d(2, 3)));
    }

    @Test
    public void ObjectAtTest(){
        engine.run();

        RectangularMap currentMap = (RectangularMap)map;
        List<Animal> animals = currentMap.getAnimals();

        assertEquals(map.objectAt(new Vector2d(2, 5)),null);
        assertEquals(map.objectAt(new Vector2d(2, 2)), animals.get(0));
    }

    @Test
    public void Movement(){
        engine.run();
        String[] args2 = {"f", "f", "f"};
        MoveDirection[] directions2 = new OptionsParser().parse(args2);
        IWorldMap map2 = new RectangularMap(10, 5);
        Vector2d[] positions2 = { new Vector2d(2,2), new Vector2d(2, 3)};
        IEngine engine2 = new SimulationEngine(directions2, map2, positions2);
        engine2.run();

        RectangularMap currentMap2 = (RectangularMap)map2;
        List<Animal> animals = currentMap2.getAnimals();

        assertEquals(animals.get(0).getCurrentPosition(), new Vector2d(2,3));
        assertEquals(animals.get(1).getCurrentPosition(), new Vector2d(2,4));

        String[] args3 = {"f", "f", "f", "f"};
        MoveDirection[] directions3 = new OptionsParser().parse(args3);
        IWorldMap map3 = new RectangularMap(10, 5);
        Vector2d[] positions3 = { new Vector2d(2,2), new Vector2d(2, 3), new Vector2d(2,2)};
        IEngine engine3 = new SimulationEngine(directions3, map3, positions3);
        engine3.run();

        RectangularMap currentMap3 = (RectangularMap)map3;
        animals = currentMap3.getAnimals();

        assertEquals(animals.get(0).getCurrentPosition(), new Vector2d(2,3));
        assertEquals(animals.get(1).getCurrentPosition(), new Vector2d(2,5));

        String[] args4 = {"f", "f", "f", "f", "r", "l", "f", "b"};
        MoveDirection[] directions4 = new OptionsParser().parse(args4);
        IWorldMap map4 = new RectangularMap(10, 5);
        Vector2d[] positions4 = { new Vector2d(2,2), new Vector2d(2, 3), new Vector2d(2,2)};
        IEngine engine4 = new SimulationEngine(directions4, map4, positions4);
        engine4.run();

        RectangularMap currentMap4 = (RectangularMap)map4;
        animals = currentMap4.getAnimals();

        assertEquals(animals.get(0).getCurrentPosition(), new Vector2d(3,3));
        assertEquals(animals.get(1).getCurrentPosition(), new Vector2d(3,5));
        assertEquals(animals.get(0).getOrientation(), MapDirection.EAST);
        assertEquals(animals.get(1).getOrientation(), MapDirection.WEST);

    }



}
