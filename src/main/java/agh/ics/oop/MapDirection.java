package agh.ics.oop;

public enum MapDirection {
    NORTH,
    SOUTH,
    EAST,
    WEST;

    public String toString(){
        String moving;
        switch (this){
            case NORTH -> moving = "Północ";
            case EAST -> moving = "Wschód";
            case SOUTH -> moving = "Południe";
            case WEST -> moving = "Zachód";
            default -> moving = null;       // will never happen
            }
        return moving;
    }

    public MapDirection next(){
        MapDirection nextDirection;
        switch (this){
            case NORTH -> nextDirection = EAST;
            case EAST -> nextDirection = SOUTH;
            case SOUTH -> nextDirection = WEST;
            case WEST -> nextDirection = NORTH;
            default -> nextDirection = null;   // will never happen
        }
        return nextDirection;
    }

    public MapDirection previous(){
        MapDirection previousDirection;
        switch (this){
            case NORTH -> previousDirection = WEST;
            case EAST -> previousDirection = NORTH;
            case SOUTH -> previousDirection = EAST;
            case WEST -> previousDirection = SOUTH;
            default -> previousDirection = null;   // will never happen
        }
        return previousDirection;
    }

    public Vector2d toUnitVector(){
        Vector2d vector2d;
        switch (this){
            case NORTH -> vector2d = new Vector2d(0,1);
            case EAST -> vector2d = new Vector2d(1, 0);
            case SOUTH -> vector2d = new Vector2d(0 ,-1);
            case WEST -> vector2d = new Vector2d(-1, 0);
            default -> vector2d = null;  // will never happen
        }
        return vector2d;
    }
}
