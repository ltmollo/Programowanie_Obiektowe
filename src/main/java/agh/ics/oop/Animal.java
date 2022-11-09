package agh.ics.oop;

public class Animal {
    private MapDirection orientation = MapDirection.NORTH;
    private Vector2d currentPosition = new Vector2d(2, 2);

    public static IWorldMap map;

    public Animal(IWorldMap map){
        Animal.map = map;
    }

    public Animal(IWorldMap map, Vector2d initialPosition){
        Animal.map = map;
        currentPosition = initialPosition;
    }

    public MapDirection getOrientation(){
        return orientation;
    }

    public Vector2d getCurrentPosition(){
        return currentPosition;
    }

    public String toString(){
        String placement;
        switch (this.orientation){
            case NORTH -> placement = "N";
            case EAST -> placement = "E";
            case SOUTH -> placement = "S";
            case WEST -> placement = "W";
            default -> placement = null;
        }
        return placement;
    }
    public boolean isAt(Vector2d position){
        return currentPosition.equals(position);
    }

    public void move(MoveDirection direction){
        Vector2d newPosition;
        switch (direction){
            case RIGHT :
                orientation = orientation.next();
                break;
            case LEFT :
                orientation = orientation.previous();
                break;
            case FORWARD :
                newPosition = currentPosition.add(orientation.toUnitVector());
                if(map.canMoveTo(newPosition)) {
                    currentPosition = newPosition;
                }
                break;
            case BACKWARD :
                newPosition = currentPosition.subtract(orientation.toUnitVector());
                if(map.canMoveTo(newPosition)){
                    currentPosition = newPosition;
                }
                break;
        }
    }
}
