package sk.tuke.kpi.oop.game.actions;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.items.Usable;

public class Use<X extends Actor> extends AbstractAction<X>{
    private Usable<X> usableActor;

    public Use(Usable<X> actor) {
        this.usableActor = actor;
    }


    @Override
    public void execute(float deltaTime) {
        if(!isDone()) {
            usableActor.useWith(getActor());
            setDone(true);
        }
    }
    public Disposable scheduleForIntersectingWith(Actor mediatingActor) {
        Scene scene = mediatingActor.getScene();
        if (scene == null) return null;
        Class<X> usingActorClass = usableActor.getUsingActorClass();  // `usable` je spominana clenska premenna

        for (Actor actor : scene) {
            if (mediatingActor.intersects(actor) && usingActorClass.isInstance(actor)) {
                return this.scheduleFor(usingActorClass.cast(actor));  // naplanovanie akcie v pripade, ze sa nasiel vhodny akter
            }
        }
        return null;
    }
}

