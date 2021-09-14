package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class PowerSwitch extends AbstractActor {

    private Switchable abstractActor;
    public PowerSwitch(Switchable abstractActor) {
        this.abstractActor = abstractActor;
        // create animation object
        Animation normalAnimation = new Animation("sprites/switch.png");
        // set actor's animation to just created Animation object
        setAnimation(normalAnimation);
    }

    public Switchable getDevice() {
        return this.abstractActor;
    }
    public void switchOn(){
        if(abstractActor !=  null) {
            abstractActor.turnOn();
        }
    }
    public void switchOff(){
        if(abstractActor !=  null) {
            abstractActor.turnOff();
        }
    }

}
