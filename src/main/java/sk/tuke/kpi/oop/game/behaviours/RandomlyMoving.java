package sk.tuke.kpi.oop.game.behaviours;

import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.actions.Move;

public class RandomlyMoving implements Behaviour<Movable> {
    private Movable actor;

    @Override
    public void setUp(Movable actor) {
        this.actor = actor;
        if(actor != null) {
            new Loop<>(
                new ActionSequence<>(
                    new Invoke<>(this::help),
                    new Wait<>(1)
                )
            ).scheduleFor(actor);
        }
    }
    private void help() {
        int random = (int) (Math.random() * ((8) + 1));
        Direction direction = Direction.values()[random];
        new Move<>(direction, 1).scheduleFor(actor);
    }
}

