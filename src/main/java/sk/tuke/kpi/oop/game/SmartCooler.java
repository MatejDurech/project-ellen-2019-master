package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.actions.Loop;

public class SmartCooler extends Cooler {
    private  Reactor reactor;
    public SmartCooler(Reactor reactor) {
        super(reactor);
        this.reactor = reactor;
        turnOn();
    }

    public void start(){
        if(reactor.getTemperature() >= 2500){
            turnOn();
        }else if (reactor.getTemperature() < 1500){
            turnOff();
        }
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        if (reactor != null) {
            super.addedToScene(scene);
            new Loop<>(new Invoke<>(this::start)).scheduleFor(this);
        }
    }
}
