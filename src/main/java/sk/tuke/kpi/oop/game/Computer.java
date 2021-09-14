package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Computer extends AbstractActor implements EnergyConsumer {
    private Animation onAnimation = new Animation("sprites/computer.png", 80, 48, 0.2f, Animation.PlayMode.LOOP_PINGPONG);
    private Animation offAnimation = new Animation("sprites/computer.png", 80, 48, 0.0f);
    private boolean turn;
    public Computer() {
        turn = false;
        setAnimation(offAnimation);
    }

    public int add(int a, int b){
        if(turn){
            return a+b;
        }else{
            return 0;
        }
    }
    public float add(float a, float b){
        if(turn){
            return a+b;
        }else{
            return 0;
        }
    }
    public int sub(int a, int b){
        if(turn){
            return a-b;
        }else{
            return 0;
        }
    }
    public float sub(float a, float b){
        if(turn){
            return a-b;
        }else{
            return 0;
        }
    }
    @Override
    public void setPowered(boolean powered) {
        turn = powered;
        if (turn){
            setAnimation(onAnimation);
        }else{
            setAnimation(offAnimation);
        }
    }
}
