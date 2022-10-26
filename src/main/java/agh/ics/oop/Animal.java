package agh.ics.oop;

public class Animal {
    private MapDirection orientation = MapDirection.NORTH;
    private Vector2d currentPosition = new Vector2d(2, 2);

    public MapDirection getOrientation(){
        return orientation;
    }

    public Vector2d getCurrentPosition(){
        return currentPosition;
    }

    public String toString(){
        String placement = "";
        placement = orientation.toString() + " " + currentPosition.toString();
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
                if(newPosition.follows(new Vector2d(0, 0)) && newPosition.precedes(new Vector2d(4, 4))) {
                    currentPosition = newPosition;
                }
                break;
            case BACKWARD :
                newPosition = currentPosition.subtract(orientation.toUnitVector());
                if(newPosition.follows(new Vector2d(0, 0)) && newPosition.precedes(new Vector2d(4, 4))) {
                    currentPosition = newPosition;
                }
                break;
        }
    }

//    public void move(MoveDirection direction, Board board){
//        Vector2d newPosition;
//        switch (direction){
//            case RIGHT :
//                orientation = orientation.next();
//                break;
//            case LEFT :
//                orientation = orientation.previous();
//                break;
//            case FORWARD :
//                newPosition = currentPosition.add(orientation.toUnitVector());
//                if(newPosition.follows(new Vector2d(0, 0)) && newPosition.precedes(new Vector2d(4, 4)) && board.checkSpot(newPosition)) {
//                    board.changeSpot(currentPosition, newPosition);
//                    currentPosition = newPosition;
//                }
//                break;
//
//            case BACKWARD :
//                newPosition = currentPosition.subtract(orientation.toUnitVector());
//                if(newPosition.follows(new Vector2d(0, 0)) && newPosition.precedes(new Vector2d(4, 4))&& board.checkSpot(newPosition)) {
//                    board.changeSpot(currentPosition, newPosition);
//                    currentPosition = newPosition;
//                }
//                break;
//        }
//    }
}
