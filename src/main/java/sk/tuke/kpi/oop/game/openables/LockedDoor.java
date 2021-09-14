package sk.tuke.kpi.oop.game.openables;

import sk.tuke.kpi.gamelib.Actor;

public class LockedDoor extends Door {
    private boolean lock;

    public LockedDoor(String name, Orientation orientation){
        super(name, orientation);
        lock = true;
    }
    @Override
    public void useWith(Actor actor) {
        if(!isLocked()){
            if(isOpen()){
                close();
            }else{
                open();
            }
        }
    }
    public void lock(){
        this.close();
        lock = true;
    }
    public void unlock(){
        this.open();
        lock = false;
    }

    public boolean isLocked(){
        return lock;
    }
}
