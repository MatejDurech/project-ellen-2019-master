package sk.tuke.kpi.oop.game;

public enum Direction {
    NORTH(0, 1),
    NORTHWEST(-1, 1),
    NORTHEAST(1, 1),
    WEST(-1, 0),
    EAST(1, 0),
    SOUTHWEST(-1, -1),
    SOUTHEAST(1, -1),
    SOUTH(0, -1),
    NONE(0, 0),
    ;
    private final int dx, dy;

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }
    private static int b = 0;

    public static Direction fromAngle(float angle){
        int a = (int) angle;
        b = (int) angle;
        update(a);

        if(b == 0){
            return  Direction.NORTH;
        }else if(b == 45){
            return Direction.NORTHWEST;
        }else if(b == 90){
            return Direction.WEST;
        }else if(b == 135){
            return Direction.SOUTHWEST;
        }else if(b == 180){
            return Direction.SOUTH;
        }else if(b == 225){
            return Direction.SOUTHEAST;
        }else if(b == 270){
            return Direction.EAST;
        }else if(b == 315){
            return Direction.NORTHEAST;
        }else{
            return Direction.NONE;
        }
    }
    private static void update(int x){
        if(x == -180){
            b = 180;
        }
        if(x == -90){
            b = 270;
        }
    }

    public float getAngle() {
        return (float) Math.toDegrees( Math.atan2( getDy(), getDx()))-90;
    }
    private int x = 0;
    private int y = 0;

    public Direction combine(Direction other) {
        x = this.dx + other.getDx();
        y = this.dy + other.getDy();
        if (this == Direction.NONE) {
            return other;
        }
        if (other == Direction.NONE) {
            return this;
        }
        Direction first = Direction.NONE;
        for(Direction second : Direction.values()){
            if(help1() == second.getDx() && help2() == second.getDy()){
                first = second;
            }
        }
        return first;
    }
    private int help1(){
        int help1 = 0;
        if(x >= 1){
            help1 = 1;
        }
        if(x <= -1){
            help1 = -1;
        }
        return help1;
    }

    private int help2(){
        int help2 = 0;
        if(y >= 1){
            help2 = 1;
        }
        if(y <= -1){
            help2 = -1;
        }
        return help2;
    }
}

