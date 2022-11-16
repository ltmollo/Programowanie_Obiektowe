package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

class GrassField extends AbstractWorldMap {
    private final int nbOfTuft;
    private final List<Grass> tufts = new ArrayList<>();

    public GrassField(int tuft){
        super(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
        this.nbOfTuft = tuft;
        putTuft();
    }

    public List<Grass> getGrassFieldTufts(){
        return this.tufts;
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
                this.tufts.add(new Grass(tuftPosition));
                i++;
            }
        }
    }

    public boolean isOccupied(Vector2d position) {
        if(super.isOccupied(position)) {
            return true;
        }
        for( Grass tuft : this.tufts){
            if(tuft.getPosition().equals(position)){
                return true;
            }
        }
        return false;
    }

    public Object objectAt(Vector2d position) {
        if (super.objectAt(position) != null) {
            return super.objectAt(position);
        }
            for (Grass elem : this.tufts) {
                if (elem.getPosition().equals(position)) {
                    return elem;
            }
        }
        return null;
    }

    protected Vector2d checkBeginning(){
        int x = Integer.MAX_VALUE;
        int y = Integer.MAX_VALUE;
        for (Animal animal : this.animals){
            x = Math.min(animal.getPosition().x, x);
            y = Math.min(animal.getPosition().y, y);
        }
        for ( Grass elem : this.tufts){
            x = Math.min(elem.getPosition().x, x);
            y = Math.min(elem.getPosition().y, y);
        }

        for (Animal animal : this.animals){
            x = Math.min(animal.getPosition().x, x);
            y = Math.min(animal.getPosition().y, y);
        }
        return new Vector2d(x, y);
    }

     protected Vector2d checkEnding(){
         int x = Integer.MIN_VALUE;
         int y = Integer.MIN_VALUE;
         for (Animal animal : this.animals){
             x = Math.max(animal.getPosition().x, x);
             y = Math.max(animal.getPosition().y, y);
         }

        for ( Grass elem : this.tufts){
            x = Math.max(elem.getPosition().x, x);
            y = Math.max(elem.getPosition().y, y);
        }
        return new Vector2d(x, y);
    }
}
