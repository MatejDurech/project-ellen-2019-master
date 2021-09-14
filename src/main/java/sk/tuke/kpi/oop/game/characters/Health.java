package sk.tuke.kpi.oop.game.characters;

import java.util.*;

public class Health{
    private int value;
    private int maxValue;
    private List<ExhaustionEffect> list;
    public Health(int value, int maxEnergy){
        this.value = value;
        this.maxValue = maxEnergy;
        list = new ArrayList<>();
    }

    public Health(int value){
        this(value, value);
    }
    public int getValue() {
        return value;
    }

    public void refill(int amount){
        if(getValue() <= 0) return;
        if(amount + value < maxValue){
            value += amount;
        }else {
            value = maxValue;
        }
    }

    public void restore(){
        if(getValue() <= 0) return;
        value = maxValue;
    }

    public void drain(int amount) {
        if(getValue() <= 0) return;
            if (value - amount <= 0) {
                exhaust();
            }else{
                value -= amount;
            }

    }

    public void exhaust() {
        if (getValue() <= 0) return;
        for (ExhaustionEffect actor : list) {
            actor.apply();
        }
        value = 0;
    }
    @FunctionalInterface
    public interface ExhaustionEffect {
        void apply();
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void onExhaustion(ExhaustionEffect effect){
        list.add(effect);
    }

}
