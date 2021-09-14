package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.actions.PerpetualReactorHeating;

import java.util.HashSet;
import java.util.Set;


public class Reactor extends AbstractActor implements Switchable, Repairable {
    private int temperature;
    private int damage;
    private float speed = 0.1f;
    private Animation normalAnimation;
    private Animation brokenanimation;
    private Animation hotAnimation;
    private Animation extinguishedAnimation;
    private Animation onAnimation;
    private boolean turn, repair,extinguish;
    private Set<EnergyConsumer> devices;


    public Reactor() {
        temperature = 0;
        turn = false;
        damage = 0;
        // create animation object
        normalAnimation = new Animation("sprites/reactor.png");
        brokenanimation = new Animation("sprites/reactor_broken.png", 80, 80, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
        hotAnimation = new Animation("sprites/reactor_hot.png", 80, 80, speed, Animation.PlayMode.LOOP_PINGPONG);
        onAnimation = new Animation("sprites/reactor_on.png", 80, 80, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
        extinguishedAnimation = new Animation("sprites/reactor_extinguished.png");
        // set actor's animation to just created Animation object
        setAnimation(normalAnimation);
        devices = new HashSet<>();
    }

    public int getTemperature() {
        return temperature;
    }

    public int getDamage() {
        return damage;
    }

    private void update() {
        if (temperature >= 2000) {
            damage = (int) ((0.025 * temperature) - 50);
        }
    }

    public void increaseTemperature(int increase) {
        if (temperature < 6000 && isOn()) {
            if (increase < 0) {
                getTemperature();
            } else if (damage < 33) {
                temperature = temperature + increase;
            } else if (damage <= 66) {
                temperature = (int) Math.ceil(((increase * 1.5) + temperature));
            } else {
                temperature = (int) Math.ceil(((increase * 2) + temperature));
            }
            updateAnimation(temperature);
        }
        update();
        if(damage >= 100){
            damage = 100;
            turn = false;
            setAnimation(brokenanimation);
        }
    }


    public void decreaseTemperature(int increase) {
        if (temperature <= 6000 && isOn()) {
            if (increase < 0) {
                getTemperature();
            } else if (damage == 100) {
                turnOff();
            } else if (damage >= 50) {
                temperature = (int) Math.ceil( (temperature -  (increase * 0.5)));
            } else if (damage > 0) {
                temperature -= increase;
            }
        }
        updateAnimation(temperature);
    }


    private void updateAnimation(int temperature) {
        if(!isOn()){
          setAnimation(normalAnimation);
        } else if (temperature > 5999) {
            setAnimation(brokenanimation);
        } else if (temperature > 3999 && getTemperature() < 5999) {
            setAnimation(hotAnimation);

        } else if (temperature < 3999) {
            setAnimation(onAnimation);
        }

    }

    public boolean repair() {
        if (damage > 0 && damage < 100) {
            damage = damage - 50;
            repair = true;
        }
        int temperatureHelper = (int) ((damage + 50) / 0.025);
        if(damage < 0){
            damage = 0;
        }
        if(temperatureHelper < temperature){
            temperature = temperatureHelper;
        }
        updateAnimation(temperature);

        return repair;
    }

    public void turnOn() {
        if (temperature < 6000) {

            turn = true;
            updateAnimation(temperature);
        } else {
            turn = false;
        }
        for (EnergyConsumer energyConsumer : devices) {
            energyConsumer.setPowered(isOn());

        }

    }

    public void turnOff() {
        turn = false;
        for (EnergyConsumer energyConsumer : devices) {
            energyConsumer.setPowered(false);
        }
        if (temperature < 6000) {
            for (EnergyConsumer energyConsumer : devices) {
                energyConsumer.setPowered(isOn());
            }
            setAnimation(normalAnimation);
        } else {
            for (EnergyConsumer energyConsumer : devices) {
                energyConsumer.setPowered(isOn());
            }
        }

    }

    public boolean isOn() {
        return turn;

    }

    public void addDevice(EnergyConsumer energyConsumer) {
        if (energyConsumer != null) {
            devices.add(energyConsumer);
            energyConsumer.setPowered(isOn());
        }
    }

    public void removeDevice(EnergyConsumer energyConsumer) {
        energyConsumer.setPowered(false);
        devices.remove(energyConsumer);
    }

    public boolean extinguish() {
        if (extinguish)
        if (temperature > 6000) {
            temperature = 4000;
            turnOff();
            setAnimation(extinguishedAnimation);
            extinguish = true;
        } else {
            extinguish = false;
        }
        return extinguish;
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new PerpetualReactorHeating(1).scheduleFor(this);
    }

}
