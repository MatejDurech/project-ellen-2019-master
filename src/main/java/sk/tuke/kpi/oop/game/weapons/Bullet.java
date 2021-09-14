package sk.tuke.kpi.oop.game.weapons;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.characters.Alive;
import sk.tuke.kpi.oop.game.characters.Armed;
import sk.tuke.kpi.oop.game.characters.Ripley;

public class Bullet extends AbstractActor implements Fireable {
    public Bullet() {
        Animation bullet = new Animation("sprites/bullet.png", 16, 16);
        setAnimation(bullet);
    }

    @Override
    public int getSpeed() {
        return 4;
    }

    @Override
    public void startedMoving(Direction direction) {
        Ripley ripley = getScene().getFirstActorByType(Ripley.class);
        this.getAnimation().setRotation(ripley.getAnimation().getRotation());
    }

    @Override
    public void collidedWithWall() {
        if (getScene() != null) {
            this.getScene().removeActor(this);
        }
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new Loop<Actor>(
            new ActionSequence<Actor>(
                new Invoke<Actor>(this::touch)
            )
        ).scheduleOn(scene);
    }
    private void touch(){
        for (Actor actor : getScene().getActors()) {
            if (actor.intersects(this) && !(actor instanceof Armed) && actor instanceof Alive){
                ((Alive) actor).getHealth().drain(10);
                this.getScene().removeActor(this);
            }

        }
    }
}

