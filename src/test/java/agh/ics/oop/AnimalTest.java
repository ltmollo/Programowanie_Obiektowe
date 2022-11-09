package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.testng.AssertJUnit.*;

public class AnimalTest {
    IWorldMap map = new RectangularMap(4, 4);
    Vector2d position = new Vector2d(2, 2);
    @Test
    public void orientationTest(){
        Animal zwierzak = new Animal(map ,position);
        assertEquals(zwierzak.getOrientation(), MapDirection.NORTH);
        zwierzak.move(MoveDirection.RIGHT);
        assertEquals(zwierzak.getOrientation(), MapDirection.EAST);
        zwierzak.move(MoveDirection.RIGHT);
        assertEquals(zwierzak.getOrientation(), MapDirection.SOUTH);
        zwierzak.move(MoveDirection.RIGHT);
        assertEquals(zwierzak.getOrientation(), MapDirection.WEST);
        zwierzak.move(MoveDirection.RIGHT);
        assertEquals(zwierzak.getOrientation(), MapDirection.NORTH);

        zwierzak.move(MoveDirection.LEFT);
        assertEquals(zwierzak.getOrientation(), MapDirection.WEST);
        zwierzak.move(MoveDirection.LEFT);
        assertEquals(zwierzak.getOrientation(), MapDirection.SOUTH);
        zwierzak.move(MoveDirection.LEFT);
        assertEquals(zwierzak.getOrientation(), MapDirection.EAST);
        zwierzak.move(MoveDirection.LEFT);
        assertEquals(zwierzak.getOrientation(), MapDirection.NORTH);
    }

    @Test
    public void positionTest(){
        Animal zwierzak = new Animal(map ,position);
        assertEquals(zwierzak.getCurrentPosition(), new Vector2d(2,2));

        zwierzak.move(MoveDirection.FORWARD);                           // North
        assertEquals(zwierzak.getCurrentPosition(), new Vector2d(2,3));

        zwierzak.move(MoveDirection.BACKWARD);                          // North
        assertEquals(zwierzak.getCurrentPosition(), new Vector2d(2,2));

        zwierzak.move(MoveDirection.RIGHT);                             //East
        zwierzak.move(MoveDirection.FORWARD);
        assertEquals(zwierzak.getCurrentPosition(), new Vector2d(3,2));

        zwierzak.move(MoveDirection.BACKWARD);                          //East
        assertEquals(zwierzak.getCurrentPosition(), new Vector2d(2,2));

        zwierzak.move(MoveDirection.RIGHT);                             //South
        zwierzak.move(MoveDirection.BACKWARD);
        assertEquals(zwierzak.getCurrentPosition(), new Vector2d(2,3));

        zwierzak.move(MoveDirection.FORWARD);                           //South
        assertEquals(zwierzak.getCurrentPosition(), new Vector2d(2,2));

        zwierzak.move(MoveDirection.RIGHT);                             //West
        zwierzak.move(MoveDirection.BACKWARD);
        assertEquals(zwierzak.getCurrentPosition(), new Vector2d(3,2));

        zwierzak.move(MoveDirection.FORWARD);                           //West
        assertEquals(zwierzak.getCurrentPosition(), new Vector2d(2,2));
    }

    @Test
    public void checkBorders(){
        Animal zwierzak1 = new Animal(map ,position);
        zwierzak1.move(MoveDirection.FORWARD); //(2, 3)
        zwierzak1.move(MoveDirection.FORWARD); //(2, 4)
        zwierzak1.move(MoveDirection.FORWARD); //(2, 4)?
        assertTrue(zwierzak1.getCurrentPosition().x <= 4 && zwierzak1.getCurrentPosition().y <= 4);

        Animal zwierzak2 = new Animal(map ,position);
        zwierzak2.move(MoveDirection.RIGHT);
        zwierzak2.move(MoveDirection.FORWARD); //(3, 2)
        zwierzak2.move(MoveDirection.FORWARD); //(4, 2)
        zwierzak2.move(MoveDirection.FORWARD); //(4, 2)?
        assertTrue(zwierzak2.getCurrentPosition().x <= 4 && zwierzak2.getCurrentPosition().y <= 4);

        Animal zwierzak3 = new Animal(map ,position);
        zwierzak3.move(MoveDirection.RIGHT);
        zwierzak3.move(MoveDirection.RIGHT);
        zwierzak3.move(MoveDirection.FORWARD); //(2, 1)
        zwierzak3.move(MoveDirection.FORWARD); //(2, 0)
        zwierzak3.move(MoveDirection.FORWARD); //(2, 0)?
        assertTrue(zwierzak3.getCurrentPosition().x <= 4 && zwierzak3.getCurrentPosition().y <= 4);

        Animal zwierzak4 = new Animal(map ,position);
        zwierzak3.move(MoveDirection.LEFT);
        zwierzak4.move(MoveDirection.FORWARD); //(1, 2)
        zwierzak4.move(MoveDirection.FORWARD); //(0, 2)
        zwierzak4.move(MoveDirection.FORWARD); //(0, 2)?
        assertTrue(zwierzak4.getCurrentPosition().x <= 4 && zwierzak4.getCurrentPosition().y <= 4);


        Animal zwierzak5 = new Animal(map ,position);
        zwierzak5.move(MoveDirection.LEFT);
        zwierzak5.move(MoveDirection.FORWARD); //(1, 2)
        zwierzak5.move(MoveDirection.FORWARD); //(0, 2)
        zwierzak5.move(MoveDirection.FORWARD); //(0, 2)?
        zwierzak5.move(MoveDirection.LEFT);
        zwierzak5.move(MoveDirection.FORWARD); //(0, 1)
        zwierzak5.move(MoveDirection.FORWARD); //(0, 0)
        zwierzak5.move(MoveDirection.FORWARD); //(0, 0)?
        assertTrue(zwierzak4.getCurrentPosition().x <= 4 && zwierzak4.getCurrentPosition().y <= 4);
        assertEquals(zwierzak5.getCurrentPosition(), new Vector2d(0,0));

        Animal zwierzak6 = new Animal(map ,position);
        zwierzak6.move(MoveDirection.LEFT);
        zwierzak6.move(MoveDirection.FORWARD); //(1, 2)
        zwierzak6.move(MoveDirection.FORWARD); //(0, 2)
        zwierzak6.move(MoveDirection.FORWARD); //(0, 2)?
        zwierzak6.move(MoveDirection.RIGHT);
        zwierzak6.move(MoveDirection.FORWARD); //(0, 3)
        zwierzak6.move(MoveDirection.FORWARD); //(0, 4)
        zwierzak6.move(MoveDirection.FORWARD); //(0, 4)?
        assertTrue(zwierzak6.getCurrentPosition().x <= 4 && zwierzak6.getCurrentPosition().y <= 4);
        assertEquals(zwierzak6.getCurrentPosition(), new Vector2d(0,4));

        Animal zwierzak7 = new Animal(map ,position);
        zwierzak7.move(MoveDirection.RIGHT);
        zwierzak7.move(MoveDirection.FORWARD); //(3, 2)
        zwierzak7.move(MoveDirection.FORWARD); //(4, 2)
        zwierzak7.move(MoveDirection.FORWARD); //(4, 2)?
        zwierzak7.move(MoveDirection.LEFT);
        zwierzak7.move(MoveDirection.FORWARD); //(4, 3)
        zwierzak7.move(MoveDirection.FORWARD); //(4, 4)
        zwierzak7.move(MoveDirection.FORWARD); //(4, 4)?
        assertTrue(zwierzak7.getCurrentPosition().x <= 4 && zwierzak7.getCurrentPosition().y <= 4);
        assertEquals(zwierzak7.getCurrentPosition(), new Vector2d(4,4));

        Animal zwierzak8 = new Animal(map ,position);
        zwierzak8.move(MoveDirection.RIGHT);
        zwierzak8.move(MoveDirection.FORWARD); //(3, 2)
        zwierzak8.move(MoveDirection.FORWARD); //(4, 2)
        zwierzak8.move(MoveDirection.FORWARD); //(4, 2)?
        zwierzak8.move(MoveDirection.RIGHT);
        zwierzak8.move(MoveDirection.FORWARD); //(4, 1)
        zwierzak8.move(MoveDirection.FORWARD); //(4, 0)
        zwierzak8.move(MoveDirection.FORWARD); //(4, 0)?
        assertTrue(zwierzak7.getCurrentPosition().x <= 4 && zwierzak7.getCurrentPosition().y <= 4);
        assertEquals(zwierzak8.getCurrentPosition(), new Vector2d(4,0));
    }

    @Test
    public void OptionsParserTest(){
        String[] arg1 = {"f"};
        MoveDirection[] res1 = {MoveDirection.FORWARD};
        assertArrayEquals(OptionsParser.parse(arg1), res1);

        String[] arg2 = {"b"};
        MoveDirection[] res2 = {MoveDirection.BACKWARD};
        assertArrayEquals(OptionsParser.parse(arg2), res2);

        String[] arg3 = {"l"};
        MoveDirection[] res3 = {MoveDirection.LEFT};
        assertArrayEquals(OptionsParser.parse(arg3), res3);

        String[] arg4 = {"r"};
        MoveDirection[] res4 = {MoveDirection.RIGHT};
        assertArrayEquals(OptionsParser.parse(arg4), res4);

        String[] arg5 = {"f", "forward", "b", "backward", "l", "left", "r", "right"};
        MoveDirection[] res5 = {MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.BACKWARD, MoveDirection.LEFT, MoveDirection.LEFT, MoveDirection.RIGHT, MoveDirection.RIGHT};
        assertArrayEquals(OptionsParser.parse(arg5), res5);

        String[] arg6 = {"f", "kot", "forward", "b", "backward", "pies", "l", "x", "left", "r", "z", "right"};
        MoveDirection[] res6 = {MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.BACKWARD, MoveDirection.LEFT, MoveDirection.LEFT, MoveDirection.RIGHT, MoveDirection.RIGHT};
        assertArrayEquals(OptionsParser.parse(arg6), res6);

    }
}
