package agh.ics.oop;

public interface IPositionChangeObserver {
    void positionChanged (Vector2d oldPosition, Vector2d newPosition);
    //powinna polegać na tym, że ze słownika usuwana jest para: <stara pozycja, zwierzę>,
    // a dodawana jest para: <nowa pozycja, zwierzę>
}
