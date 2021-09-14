package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Cooler extends AbstractActor implements Switchable {
    private Animation onAnimation = new Animation("sprites/fan.png", 32, 32, 0.2f, Animation.PlayMode.LOOP_PINGPONG);
    private Animation offAnimation = new Animation("sprites/fan.png", 32, 32, 0f, Animation.PlayMode.LOOP_PINGPONG);
    private Reactor reactor;
    private boolean turn = false;

    public Cooler(Reactor reactor) {
        this.reactor = reactor;
        setAnimation(offAnimation);

    }

    private void updateAnimation(boolean turn) {
        if (turn) {
            setAnimation(onAnimation);
        } else {
            setAnimation(offAnimation);
        }
    }
    @Override
    public boolean isOn(){
        return turn;
    }

    @Override
    public void turnOn(){
        if(turn){
            turn = true;
        }else{
            turn = true;
        }
        updateAnimation(isOn());
    }
    @Override
    public void turnOff(){
        if(!turn){
            turn = false;
        }else{
            turn = false;
        }
        updateAnimation(isOn());
    }
    private void coolReactor(){
        if(isOn() && reactor != null) {
            reactor.decreaseTemperature(1);
        }
    }
    @Override
    public void addedToScene(@NotNull Scene scene) {
            super.addedToScene(scene);
            new Loop<>(new Invoke<>(this::coolReactor)).scheduleFor(this);
        }
}

