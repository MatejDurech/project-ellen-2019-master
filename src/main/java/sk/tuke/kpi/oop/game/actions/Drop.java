package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;

public class Drop<A extends Keeper> extends AbstractAction<A> {

    @Override
    public void execute(float deltaTime) {
        if (!isDone() && getActor() != null) {
            Actor peek = getActor().getBackpack().peek();
            if (peek != null) {
                getActor().getScene().addActor(getActor().getBackpack().peek(), getActor().getPosX() + 8, getActor().getPosY() + 8);
                getActor().getBackpack().remove(getActor().getBackpack().peek());
                setDone(true);
            }
        }
        setDone(true);
    }
}

