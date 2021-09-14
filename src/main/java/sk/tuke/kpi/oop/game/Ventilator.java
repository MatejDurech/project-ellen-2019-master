package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.messages.Topic;

public class Ventilator extends AbstractActor implements Repairable {
    private boolean repair = false;
    private Animation onAnimation = new Animation("sprites/ventilator.png", 32, 32, 0.2f, Animation.PlayMode.LOOP_PINGPONG);
    private Animation offAnimation = new Animation("sprites/ventilator.png", 32, 32, 0f, Animation.PlayMode.LOOP_PINGPONG);
    public static final Topic<Ventilator> VENTILATOR_REPAIR = Topic.create("ventilator repair", Ventilator.class);

    public Ventilator(){
        setAnimation(offAnimation);
    }

    @Override
    public boolean repair() {
        if(!repair){
            repair = true;
            setAnimation(onAnimation);
            getScene().getMessageBus().publish(VENTILATOR_REPAIR, this);
        }
        return repair;
    }

    public boolean isRepair() {
        return repair;
    }
}

