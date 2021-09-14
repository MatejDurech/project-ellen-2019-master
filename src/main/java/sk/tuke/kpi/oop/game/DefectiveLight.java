package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.actions.Loop;


public class DefectiveLight extends Light implements Repairable {
        private boolean repair;
        public void defectLight () {
            int r = (int) (Math.random() * (20 - 10)) + 10;
            if (r == 15) {
                toggle();
            }
        }
    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new Loop<>(new Invoke<>(this::defectLight)).scheduleFor(this);
    }

    @Override
    public boolean repair() {
        repairHelper();
            return repair;
    }
    private void repairHelper() {
        new ActionSequence<>(
            new Invoke<>(this::turnOn),
            new Wait<>(10)
        ).scheduleFor(this);
    }

}
