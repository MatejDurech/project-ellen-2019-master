package sk.tuke.kpi.oop.game.items;

import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.characters.Alive;

public class Energy extends AbstractActor implements Usable<Alive>{
    public Energy() {
        Animation normalAnimation = new Animation("sprites/energy.png", 16, 16);
        setAnimation(normalAnimation);
    }

    @Override
    public void useWith(Alive actor) {
            if (actor != null) {
                actor.getHealth().restore();
                @Nullable Scene scene = getScene();
                assert scene != null;
                scene.removeActor(this);
            }
    }

    @Override
    public Class<Alive> getUsingActorClass() {
        return Alive.class;
    }
}

