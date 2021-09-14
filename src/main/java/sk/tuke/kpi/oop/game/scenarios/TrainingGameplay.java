package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.SceneListener;

public class TrainingGameplay implements SceneListener {


    public void setupPlay(@NotNull Scene scene) {
        /*Reactor reactor = new Reactor();
        Cooler cooler = new Cooler(reactor);
        Hammer hammer = new Hammer();
        scene.addActor(reactor, 145, 96);
        scene.addActor(cooler, 45, 45);
        scene.addActor(hammer, 100, 100);
        reactor.turnOn();
        new ActionSequence<>(
            new Wait<>(5),
            new Invoke<>(cooler::turnOn)
        ).scheduleFor(cooler);
        new When<>(
            () -> reactor.getTemperature() >= 3000,
            new Invoke<>(() -> reactor.repairWith(hammer))
        ).scheduleFor(reactor);

         */
    }
}
