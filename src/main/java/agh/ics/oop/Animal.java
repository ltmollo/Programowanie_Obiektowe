package agh.ics.oop;

public class Animal extends AbstractWorldMapElement{
    private MapDirection orientation = MapDirection.NORTH;

    private final IWorldMap map;

    public Animal(IWorldMap map){
        super(new Vector2d(2, 2));
        this.map = map;
    }
    public Animal(IWorldMap map, Vector2d initialPosition){
        super(initialPosition);
        this.map = map;
    }

    public MapDirection getOrientation(){
        return orientation;
    }

    public Vector2d getPosition(){
        return position;
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
        return this.position.equals(position);
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
                newPosition = position.add(orientation.toUnitVector());
                if(map.canMoveTo(newPosition)) {
                    position = newPosition;
                }
                break;
            case BACKWARD :
                newPosition = position.subtract(orientation.toUnitVector());
                if(map.canMoveTo(newPosition)){
                    position = newPosition;
                }
                break;
        }
    }
}
