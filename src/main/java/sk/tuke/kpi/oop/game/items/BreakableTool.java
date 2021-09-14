package sk.tuke.kpi.oop.game.items;

import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;

public abstract class BreakableTool<X extends Actor> extends AbstractActor implements Usable<X>{
    private int remainingUses;
    public BreakableTool(int remainingUses){
        this.remainingUses = remainingUses;
    }

    public int getRemainingUses() {
        return remainingUses;
    }

    public void useWith(X actor){
        remainingUses--;
        if(remainingUses == 0) {
            @Nullable Scene scene = getScene();
            assert scene != null;
            scene.removeActor(this);
        }
    }
    protected void setRemainingUses(int num){
        this.remainingUses = num;
    }
}
