package sk.tuke.kpi.oop.game.controllers;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.actions.Move;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MovableController implements KeyboardListener {
    private Movable actor;
    private Set<Direction> set = new HashSet<>();
    private Move<Movable> move;

    private Map<Input.Key, Direction> keyDirectionMap = Map.ofEntries(
        Map.entry(Input.Key.UP, Direction.NORTH),
        Map.entry(Input.Key.DOWN, Direction.SOUTH),
        Map.entry(Input.Key.LEFT, Direction.WEST),
        Map.entry(Input.Key.RIGHT, Direction.EAST)
    );


    public MovableController(Movable actor) {
        this.actor = actor;
    }

    @Override
    public void keyPressed(@NotNull Input.Key key) {
        if (keyDirectionMap.containsKey(key)) {
            if (move != null) {
                keyReleased(key);
            }
            set.add(keyDirectionMap.get(key));
            help();
        }
    }

    @Override
    public void keyReleased(@NotNull Input.Key key) {
        if (move != null && !move.isDone()) {
            move.stop();
        }
        set.remove(keyDirectionMap.get(key));
        help();
    }


    private void help(){
        Direction first = Direction.NONE;
        for (Direction second: set){
            if (second != null){
                first = first.combine(second);
            }
            if(!set.isEmpty() && move != null ){
                move.stop();
            }
            if (first != Direction.NONE){
                move = new Move<>(first, 999999999);
                move.scheduleFor(actor);
            }

        }

    }

}
