package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.actions.Action;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;

public class Move<A extends Movable> implements Action<A> {
    private boolean done,times;
    private A actor;
    private float duration;
    private Direction direction;

    public Move(Direction direction, float duration) {
        super();
        this.direction = direction;
        this.duration = duration;
        done = false;
        times = true;

    }
    public Move(Direction direction) {
        super();
        this.direction = direction;
        done = false;
        times = true;

    }
    @Override
    public A getActor() {
        return actor;
    }
    @Override
    public void setActor(A actor) {
        this.actor = actor;
    }
    public boolean isDone(){
        return done;
    }

    @Override
    public void execute(float deltaTime) {
        if (!isDone()) {
            if (times) {
                actor.startedMoving(direction);
                times = false;
            }
            duration -= deltaTime;
            if (duration > 1e-5) {
                actor.setPosition(actor.getPosX() + direction.getDx() * actor.getSpeed(), actor.getPosY() + direction.getDy() * actor.getSpeed());
                wall();
            }else{
                stop();
            }
        }
    }

    @Override
    public void reset() {
        assert getActor() != null;
        getActor().stoppedMoving();
        done = false;
        times = true;
    }

    public void stop(){
        if(actor != null) {
            actor.stoppedMoving();
            done = true;
        }
    }

    public void wall() {
        if (actor.getScene().getMap().intersectsWithWall(actor)) {
            if (direction.getAngle() == 0) {
                actor.setPosition(actor.getPosX(), actor.getPosY() - actor.getSpeed());
            } else if (direction.getAngle() == 45) {
                actor.setPosition(actor.getPosX() - -(actor.getSpeed()), actor.getPosY() - (actor.getSpeed()));
            } else if (direction.getAngle() == 90) {
                actor.setPosition(actor.getPosX() - -(actor.getSpeed()), actor.getPosY());
            } else if (direction.getAngle() == -180) {
                actor.setPosition(actor.getPosX(), actor.getPosY() - -(actor.getSpeed()));
            }else if(direction.getAngle() == -135) {
                actor.setPosition(actor.getPosX() - (actor.getSpeed()), actor.getPosY() - -(actor.getSpeed()));
            }else if(direction.getAngle() == -90) {
                actor.setPosition(actor.getPosX() - actor.getSpeed(), actor.getPosY());
            }else if(direction.getAngle() == -45){
                actor.setPosition(actor.getPosX() -(actor.getSpeed()), actor.getPosY() -(actor.getSpeed())) ;
            } else{
                actor.setPosition(actor.getPosX() + actor.getSpeed(), actor.getPosY() + actor.getSpeed());
            }
            actor.collidedWithWall();
        }
    }
}

