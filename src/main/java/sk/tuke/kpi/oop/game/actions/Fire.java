package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.characters.Armed;
import sk.tuke.kpi.oop.game.weapons.Fireable;

public class Fire<A extends Armed> extends AbstractAction<A> {

    @Override
    public void execute(float deltaTime) {
        if(getActor() != null) {
            Fireable fireable = getActor().getFirearm().fire();
            if (!isDone() && fireable != null && getActor() != null && getActor().getFirearm().getAmmo() > 0 && getActor().getScene() != null) {
                getActor().getScene().addActor(fireable, getActor().getPosX() + 8, getActor().getPosY() + 8);
                new Move<Fireable>(Direction.fromAngle(getActor().getAnimation().getRotation()), 999999).scheduleFor(fireable);
                }
            }
        setDone(true);
    }
}
