package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.items.Hammer;
import sk.tuke.kpi.oop.game.items.Usable;

import java.util.Objects;

public class Locker extends AbstractActor implements Usable<Actor> {
    private boolean first = true;
    public Locker(){
        Animation offAnimation = new Animation("sprites/locker.png", 16, 16, 0.0f);
        setAnimation(offAnimation);
    }

    @Override
    public void useWith(Actor actor) {
        if(first){
            Hammer hammer = new Hammer();
            Objects.requireNonNull(this.getScene()).addActor(hammer, this.getPosX() + 20, this.getPosY());
            first = false;
        }
    }

    @Override
    public Class<Actor> getUsingActorClass() {
        return Actor.class;
    }
}

