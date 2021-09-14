package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.items.Collectible;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Take<A extends Keeper> extends AbstractAction<A> {
    private List<Actor> list = new ArrayList<>();

    public Take() {
    }


    @Override
    public void execute(float deltaTime) {
        if (!isDone() && getActor() != null) {
            list.addAll(getActor().getScene().getActors());
            help();
        }
        setDone(true);
    }

    private void help(){
        for (int x = 0; x < list.size(); x++) {
            if (!(list.get(x) instanceof Collectible)) {
                list.remove(x);
            }
        }
        for (int x = 0; x < list.size(); x++) {
            if (list.get(x).intersects(getActor()) && list.get(x) instanceof Collectible) {
                try {
                    getActor().getBackpack().add((Collectible) list.get(x));
                    getActor().getScene().removeActor((list.get(x)));
                    setDone(true);

                } catch (IllegalStateException exception) {
                   Objects.requireNonNull(getActor().getScene()).getOverlay().drawText(exception.getMessage(), 0, 0).showFor(1);
                   setDone(true);
                }


            }
        }
    }
}

