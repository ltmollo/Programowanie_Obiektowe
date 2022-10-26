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
                case "f" -> directions[index] = MoveDirection.FORWARD;
                case "forward" -> directions[index] = MoveDirection.FORWARD;
                case "b" -> directions[index] = MoveDirection.BACKWARD;
                case "backward" -> directions[index] = MoveDirection.BACKWARD;
                case "l" -> directions[index] = MoveDirection.LEFT;
                case "left" -> directions[index] = MoveDirection.LEFT;
                case "r" -> directions[index] = MoveDirection.RIGHT;
                case "right" -> directions[index] = MoveDirection.RIGHT;
                default -> index--;
            }
        }
        return directions;
    }
}
