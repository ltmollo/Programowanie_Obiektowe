package agh.ics.oop;

public class Grass {
    private Vector2d position;

    public Grass(Vector2d givenPosition){
        this.position = givenPosition;
    }

    public Vector2d getPostion(){
        return this.position;
    }

    public String toString(){
        return "*";
    }
}
