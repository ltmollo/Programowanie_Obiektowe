package agh.ics.oop;

import java.util.*;

public class GrassField extends AbstractWorldMap {
    private final int nbOfTuft;
    protected Map<Vector2d, Grass> tufts = new HashMap<>();

    public GrassField(int tuft){
        super(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
        this.nbOfTuft = tuft;
        putTuft();
    }

    public List<Vector2d> getGrassFieldTuftsPositions(){
        List<Vector2d> positions = new ArrayList<>();
        for(Vector2d vector : this.tufts.keySet()) {
            positions.add(new Vector2d(vector.x, vector.y));
        }
        return positions;
    }

    private void putTuft(){
        int x;
        int y;
        double sqrt = Math.sqrt(this.nbOfTuft*10);
        int i = 0;
        Vector2d tuftPosition;
        while( i < this.nbOfTuft){
            x = (int)(Math.random()*sqrt);
            y = (int)(Math.random()*sqrt);
            tuftPosition = new Vector2d(x, y);
            if(!isOccupied(tuftPosition)){
                this.tufts.put(tuftPosition, new Grass(tuftPosition));
                i++;
                mapBound.addElementToMap(tuftPosition);
            }
        }
        this.mapBound.setMapBoundary(this.tufts);
    }

    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    public Object objectAt(Vector2d position) {
        if (super.objectAt(position) != null) {
            return super.objectAt(position);
        }
        return tufts.get(position);
    }

    protected Vector2d checkBeginning(){
        int x = Integer.MAX_VALUE;
        int y = Integer.MAX_VALUE;
        for (Vector2d vector : this.animals.keySet()){
            x = Math.min(vector.x, x);
            y = Math.min(vector.y, y);
        }
        for ( Vector2d vector : this.tufts.keySet()){
            x = Math.min(vector.x, x);
            y = Math.min(vector.y, y);
        }
        return new Vector2d(x, y);
    }

     protected Vector2d checkEnding(){
         int x = Integer.MIN_VALUE;
         int y = Integer.MIN_VALUE;
         for (Vector2d vector : this.animals.keySet()){
             x = Math.max(vector.x, x);
             y = Math.max(vector.y, y);
         }

        for ( Vector2d vector : this.tufts.keySet()){
            x = Math.max(vector.x, x);
            y = Math.max(vector.y, y);
        }
        return new Vector2d(x, y);
    }
}
