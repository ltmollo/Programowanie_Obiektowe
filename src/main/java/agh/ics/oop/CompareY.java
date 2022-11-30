package agh.ics.oop;

import java.util.Comparator;

public class CompareY implements Comparator<Vector2d> {

    public int compare(Vector2d vec1, Vector2d vec2) {
        if(vec1.equals(vec2)){
            return 0;
        }
        if(vec1.y < vec2.y || vec1.y == vec2.y && vec1.x < vec2.x){
            return -1;
        }
        return 1;
    }
}
