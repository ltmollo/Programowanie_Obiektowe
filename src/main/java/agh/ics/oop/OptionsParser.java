package agh.ics.oop;

public class OptionsParser {

    public static MoveDirection[] parse(String[] args){
        int n = args.length;
        int correctArgs = 0;
        for (String arg : args) {
            if (arg.equals("f") || arg.equals("b") || arg.equals("l") || arg.equals("r") || arg.equals("forward") || arg.equals("backward") || arg.equals("left") || arg.equals("right")){
                correctArgs++;
            }
        }

        int index = -1;
        MoveDirection[] directions = new MoveDirection[correctArgs];
        for (String arg: args){
            index++;
            switch (arg) {
                case "f", "forward" -> directions[index] = MoveDirection.FORWARD;
                case "b", "backward" -> directions[index] = MoveDirection.BACKWARD;
                case "l", "left" -> directions[index] = MoveDirection.LEFT;
                case "r", "right" -> directions[index] = MoveDirection.RIGHT;
                default -> throw new IllegalArgumentException(arg + " is not legal move specification");
            }
        }
        return directions;
    }
}
