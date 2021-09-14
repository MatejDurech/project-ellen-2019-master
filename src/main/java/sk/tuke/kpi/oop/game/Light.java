package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Light extends AbstractActor implements Switchable,EnergyConsumer {
    private boolean on, status;
    private Animation onAnimation = new Animation("sprites/light_on.png", 16, 16);
    private Animation offAnimation = new Animation("sprites/light_off.png", 16, 16);

    public Light() {
        on = false;
        setAnimation(offAnimation);

    }

    public void toggle() {
        if (on) {
            turnOff();
        } else if (!on) {
            turnOn();
        }
    }

    @Override
    public void setPowered(boolean status) {
        this.status = status;
        if(isOn() && status){
            setAnimation(onAnimation);
        }else{
            setAnimation(offAnimation);
        }
    }

    @Override
    public void turnOn() {
        if (!isOn()) {
            on = true;
            if (status) {
                setAnimation(onAnimation);
            }else{
                setAnimation(offAnimation);
            }
        }
    }

    @Override
    public void turnOff(){
        if(isOn()) {
            on = false;
            setAnimation(offAnimation);
        }
    }

    @Override
    public boolean isOn() {
        return on;
    }

}
