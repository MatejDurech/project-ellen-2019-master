package sk.tuke.kpi.oop.game.characters;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.behaviours.Behaviour;

public class Alien extends AbstractActor implements Movable, Alive, Enemy {
    private Health health;
    private int speed;
    private Behaviour<? super Alien> behaviour;

    public Alien(){
        Animation alien = new Animation("sprites/alien.png", 32, 32, 0.1f);
        setAnimation(alien);
        health = new Health(50);
        speed = 1;
    }
    public Alien(int healthValue, Behaviour<? super Alien> behaviour ){
        this.behaviour = behaviour;
        Animation alien = new Animation("sprites/alien.png", 32, 32, 0.1f);
        setAnimation(alien);
        health = new Health(healthValue);
        speed = 1;
    }

    @Override
    public int getSpeed() {
        return speed;
    }


    @Override
    public Health getHealth() {
        return health;
    }
    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new Loop<>(
            new ActionSequence<>(
                new Invoke<>(this::touch),
                new Wait<>(1)
            )
        ).scheduleFor(this);
        if(behaviour != null) {
            this.behaviour.setUp(this);
        }
    }



    private void touch(){
        Alive ripley = getScene().getFirstActorByType(Alive.class);
        for (Actor actor : getScene().getActors()) {
            if (actor.intersects(this) && actor instanceof Alive && !(actor instanceof  Enemy)){
                ripley.getHealth().drain(5);
            }

        }
    }

}

